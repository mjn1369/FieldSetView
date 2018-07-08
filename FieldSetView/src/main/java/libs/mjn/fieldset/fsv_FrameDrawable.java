package libs.mjn.fieldset;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by mJafarinejad on 7/1/2018.
 */
class fsv_FrameDrawable extends GradientDrawable {
    private int border_color = Color.BLACK, border_width = 1, border_radius = 10;
    private float border_alpha = 1f;

    public fsv_FrameDrawable() {
        setShape(GradientDrawable.RECTANGLE);
        setParams();
    }

    public int getBorder_color() {
        return border_color;
    }

    public void setBorder_color(int border_color) {
        this.border_color = border_color;
        setParams();
    }

    public int getBorder_width() {
        return border_width;
    }

    public void setBorder_width(int border_width) {
        this.border_width = border_width;
        setParams();
    }

    public int getBorder_radius() {
        return border_radius;
    }

    public void setBorder_radius(int border_radius) {
        this.border_radius = border_radius;
        setParams();
    }

    public float getBorder_alpha() {
        return border_alpha;
    }

    public void setBorder_alpha(float border_alpha) {
        this.border_alpha = border_alpha;
        setParams();
    }

    void setParams() {
        setCornerRadii(new float[]{border_radius, border_radius,
                border_radius, border_radius,
                border_radius, border_radius,
                border_radius, border_radius});
        setStroke(border_width, border_color);
        border_alpha = border_alpha < 0 ? 0 : (border_alpha > 1 ? 1 : border_alpha);
        setAlpha((int) (border_alpha * 255));
        invalidateSelf();
    }
}
