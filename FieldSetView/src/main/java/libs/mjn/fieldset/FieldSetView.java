package libs.mjn.fieldset;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import libs.mjn.fieldsetview.R;

/**
 * Created by mJafarinejad on 7/1/2018.
 */
public class FieldSetView extends FrameLayout {
    private final int DEFAULT_BORDER_WIDTH = 2;
    private final int DEFAULT_BORDER_RADIUS = 0;
    private final String DEFAULT_BORDER_COLOR = "#212121";
    private final String DEFAULT_LEGEND_COLOR = "#212121";
    private final int DEFAULT_LEGEND_MARGIN = 16;
    private final int DEFAULT_LEGEND_PADDING = 12;
    private final int DEFAULT_ICON_MARGIN = 8;

    private enum ENUM_LEGEND_POSITION {LEFT, RIGHT, CENTER}

    private ENUM_LEGEND_POSITION legendPosition = ENUM_LEGEND_POSITION.CENTER;

    private enum ENUM_LEGEND_DIRECTION {LTR, RTL}

    private ENUM_LEGEND_DIRECTION legendDirection = ENUM_LEGEND_DIRECTION.LTR;
    private TextView mLegend;
    private ImageView mIcon;
    private ViewGroup mFrame;
    private ViewGroup mContainer;
    private ViewGroup mLegendContainer;
    private fsv_FrameDrawable mBackground;
    private int legendMarginLeft, legendMarginRight;
    private int legendPadding, legendPaddingLeft, legendPaddingRight;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private boolean listenToResize;

    public FieldSetView(Context context) {
        super(context);
        init(null);
    }

    public FieldSetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FieldSetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FieldSetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        inflate(getContext(), R.layout.fsv_fieldsetview, this);
        mFrame = (RelativeLayout) findViewById(R.id.fieldsetview_frame);
        mContainer = (FrameLayout) findViewById(R.id.fieldsetview_container);
        mLegend = (TextView) findViewById(R.id.fieldsetview_legend);
        mIcon = (ImageView) findViewById(R.id.fieldsetview_legendIcon);
        mLegendContainer = (ViewGroup) findViewById(R.id.fieldsetview_legendContainer);
        mBackground = new fsv_FrameDrawable();
        if (set == null)
            return;
        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.FieldSetView);
        //Border Color
        mBackground.setBorder_color(ta.getColor(R.styleable.FieldSetView_fsv_borderColor, Color.parseColor(DEFAULT_BORDER_COLOR)));
        //Border Width
        mBackground.setBorder_width((int) ta.getDimension(R.styleable.FieldSetView_fsv_borderWidth, DEFAULT_BORDER_WIDTH));
        //Border Radius
        mBackground.setBorder_radius((int) ta.getDimension(R.styleable.FieldSetView_fsv_borderRadius, DEFAULT_BORDER_RADIUS));
        //Border Alpha
        mBackground.setBorder_alpha(ta.getFloat(R.styleable.FieldSetView_fsv_borderAlpha, 1f));
        //Legend Margins and Paddings
        legendMarginLeft = (int) ta.getDimension(R.styleable.FieldSetView_fsv_legendMarginLeft, DEFAULT_LEGEND_MARGIN);
        legendMarginRight = (int) ta.getDimension(R.styleable.FieldSetView_fsv_legendMarginRight, DEFAULT_LEGEND_MARGIN);
        legendPadding = (int) ta.getDimension(R.styleable.FieldSetView_fsv_legendPadding, -1369f);
        legendPaddingLeft = (int) ta.getDimension(R.styleable.FieldSetView_fsv_legendPaddingLeft, DEFAULT_LEGEND_PADDING);
        legendPaddingRight = (int) ta.getDimension(R.styleable.FieldSetView_fsv_legendPaddingRight, DEFAULT_LEGEND_PADDING);
        if (legendPadding != -1369f) {
            legendPaddingLeft = legendPadding;
            legendPaddingRight = legendPadding;
        }
        //Legend text
        mLegend.setText(ta.getText(R.styleable.FieldSetView_fsv_legend));
        //Legend text color
        mLegend.setTextColor(ta.getColor(R.styleable.FieldSetView_fsv_legendColor, Color.parseColor(DEFAULT_LEGEND_COLOR)));
        //Legend font
        String fontName = ta.getString(R.styleable.FieldSetView_fsv_legendFont);
        try {
            Typeface customFont = Typeface.createFromAsset(getContext().getAssets(), fontName);
            mLegend.setTypeface(customFont);
        } catch (Exception e) {
        }
        //Legend text size
        int titleSize = ta.getDimensionPixelSize(R.styleable.FieldSetView_fsv_legendSize, 24);
        if (titleSize > 0) {
            mLegend.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        }
        //Legend position
        if (ta.hasValue(R.styleable.FieldSetView_fsv_legendPosition)) {
            switch (ta.getInt(R.styleable.FieldSetView_fsv_legendPosition, 3)) {
                case 1: //left
                    legendPosition = ENUM_LEGEND_POSITION.LEFT;
                    break;
                case 2: //right
                    legendPosition = ENUM_LEGEND_POSITION.RIGHT;
                    break;
                case 3: //center
                    legendPosition = ENUM_LEGEND_POSITION.CENTER;
                    break;
            }
        }
        //Legend direction
        switch (ta.getInt(R.styleable.FieldSetView_fsv_legendDirection, 1)) {
            case 1: //ltr
                legendDirection = ENUM_LEGEND_DIRECTION.LTR;
                if(hasLegendText()) {
                    View view = mLegendContainer.getChildAt(0);
                    ((LinearLayout.LayoutParams) view.getLayoutParams()).setMargins(0, 0, DEFAULT_ICON_MARGIN, 0);
                }
                break;
            case 2: //rtl
                legendDirection = ENUM_LEGEND_DIRECTION.RTL;
                if(hasLegendText()) {
                    View view = mLegendContainer.getChildAt(0);
                    mLegendContainer.removeViewAt(0);
                    mLegendContainer.addView(view);
                    ((LinearLayout.LayoutParams) view.getLayoutParams()).setMargins(DEFAULT_ICON_MARGIN, 0, 0, 0);
                }
                break;
        }
        //Icon
        Drawable icon_drawable = ta.getDrawable(R.styleable.FieldSetView_fsv_legendIcon);
        if (icon_drawable == null) {
            mIcon.setVisibility(GONE);
        } else {
            mIcon.setVisibility(VISIBLE);
            mIcon.setImageDrawable(icon_drawable);
            //Icon Tint
            if (ta.hasValue(R.styleable.FieldSetView_fsv_legendIconTint))
                mIcon.setColorFilter(ta.getColor(R.styleable.FieldSetView_fsv_legendIconTint, Color.TRANSPARENT), PorterDuff.Mode.SRC_IN);
            else
                mIcon.setColorFilter(mLegend.getCurrentTextColor(), PorterDuff.Mode.SRC_IN);
        }
        mLegendContainer.post(new Runnable() {
            @Override
            public void run() {
                //Setup border and icon dimensions relative to legend's text
                // set the icon's sizes equal to text's height * 0.9
                if (mIcon.getVisibility() == VISIBLE) {
                    mIcon.getLayoutParams().width = (int) (mLegend.getMeasuredHeight() * 0.9);
                    mIcon.getLayoutParams().height = (int) (mLegend.getMeasuredHeight() * 0.9);
                }
                // if border's width is >= text's height, set the border's width to half of text's height
                if (mLegendContainer.getMeasuredHeight() <= mBackground.getBorder_width()) {
                    mBackground.setBorder_width((int) (mLegendContainer.getMeasuredHeight() * 0.5));
                    setContainerMargins(mBackground.getBorder_width());
                }
                // set margins and appropriate gravity
                if (legendPosition == ENUM_LEGEND_POSITION.RIGHT) {
                    ((LayoutParams) mLegendContainer.getLayoutParams()).gravity = Gravity.TOP | Gravity.RIGHT;
                    int margin = mBackground.getBorder_width() >= mBackground.getBorder_radius() ? mBackground.getBorder_width() + mBackground.getBorder_radius() : mBackground.getBorder_radius();
                    ((LayoutParams) mLegendContainer.getLayoutParams()).setMargins(0, 0, margin + legendMarginRight, 0);
                } else if (legendPosition == ENUM_LEGEND_POSITION.LEFT) {
                    ((LayoutParams) mLegendContainer.getLayoutParams()).gravity = Gravity.TOP | Gravity.LEFT;
                    int margin = mBackground.getBorder_width() >= mBackground.getBorder_radius() ? mBackground.getBorder_width() + mBackground.getBorder_radius() : mBackground.getBorder_radius();
                    ((LayoutParams) mLegendContainer.getLayoutParams()).setMargins(legendMarginLeft + margin, 0, 0, 0);
                } else {
                    ((LayoutParams) mLegendContainer.getLayoutParams()).gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
                }
                // set the top border inline with legend
                LayoutParams frameParams = (LayoutParams) mFrame.getLayoutParams();
                frameParams.setMargins(0, (mLegendContainer.getMeasuredHeight() - mBackground.getBorder_width()) / 2, 0, 0);
                // draw the border
                updateFrame();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (listenToResize)
            updateFrame();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // move child views into container
        for (int i = 1; i < getChildCount(); i++) {
            View v = getChildAt(i);
            removeViewAt(i);
            mContainer.addView(v);
        }
        // set padding to container
        mContainer.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setPadding(0, 0, 0, 0);
    }

    // set margins to container so it fits inside frame and below the legend
    private void setContainerMargins(int margin) {
        ((RelativeLayout.LayoutParams) mContainer.getLayoutParams()).setMargins(
                margin,
                margin + (mLegend.getMeasuredHeight() - mBackground.getBorder_width()) / 2,
                margin,
                margin + (mLegend.getMeasuredHeight() - mBackground.getBorder_width()) / 2);
    }

    //set frame's background and erase behind the legend
    private void updateFrame() {
        post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT <= 23)
                    mBackground.invalidateSelf();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    mFrame.setBackgroundDrawable(mBackground);
                } else {
                    mFrame.setBackground(mBackground);
                }
                if (Build.VERSION.SDK_INT <= 23)
                    mBackground.invalidateSelf();
                setContainerMargins(mBackground.getBorder_width());
                mFrame.post(new Runnable() {
                    @Override
                    public void run() {
                        bitmap = Bitmap.createBitmap(mFrame.getWidth(), mFrame.getHeight(), Bitmap.Config.ARGB_8888);
                        bitmapCanvas = new Canvas(bitmap);
                        mFrame.getBackground().draw(bitmapCanvas);
                        if (hasLegendText() || hasLegendIcon()) {
                            eraseBitmap(bitmapCanvas, mLegendContainer);
                        }
                        mFrame.setBackgroundDrawable(new BitmapDrawable(bitmap));
                        listenToResize = true;
                    }
                });
            }
        });
    }

    private void eraseBitmap(Canvas canvas, View view) {
        if (view.getLeft() - legendPaddingLeft < mBackground.getBorder_width())
            legendPaddingLeft = view.getLeft() - mBackground.getBorder_width();
        if (view.getRight() + legendPaddingRight > mFrame.getRight() - mBackground.getBorder_width())
            legendPaddingRight = mFrame.getRight() - mBackground.getBorder_width() - view.getRight();
        Paint eraserPaint = new Paint();
        eraserPaint.setAlpha(0);
        eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        eraserPaint.setAntiAlias(true);
        Rect rect = new Rect(view.getLeft() - legendPaddingLeft, view.getTop(), view.getRight() + legendPaddingRight, mBackground.getBorder_width());
        canvas.drawRect(rect, eraserPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (bitmap != null)
            bitmap.recycle();
    }

    private boolean hasLegendText(){
        return mLegend.getText().toString().trim().length()>0;
    }

    private boolean hasLegendIcon(){
        return mIcon.getVisibility()!=GONE;
    }

    public void setLegend(String text) {
        mLegend.setText(text);
        updateFrame();
    }

    public String getLegend() {
        return mLegend.getText().toString();
    }
}
