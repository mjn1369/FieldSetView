package libs.mjn.testfieldsetview;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((EditText) findViewById(R.id.et_password)).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "yekan.ttf"));
        ((RadioButton) findViewById(R.id.rb_male)).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "yekan.ttf"));
        ((RadioButton) findViewById(R.id.rb_female)).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "yekan.ttf"));
    }
}
