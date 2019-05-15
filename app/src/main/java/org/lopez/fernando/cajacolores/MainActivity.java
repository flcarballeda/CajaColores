package org.lopez.fernando.cajacolores;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private int negro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        negro = getResources().getColor(R.color.black);
    }
    public void cambiarColor(View view) {
        LinearLayout linear = (LinearLayout)view;
        linear.setBackgroundColor(negro);
    }
}
