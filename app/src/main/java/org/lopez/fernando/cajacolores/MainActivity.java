package org.lopez.fernando.cajacolores;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private int negro;
    private int veces;
    private long inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        negro = getResources().getColor(R.color.black);
        inicio = System.currentTimeMillis();
    }

    public void cambiarColor(View view) {
        LinearLayout linear = (LinearLayout) view;
        int color = ((ColorDrawable) linear.getBackground()).getColor();
        if (this.negro == color) {
            return;
        }
        linear.setBackgroundColor(negro);
        this.veces += 1;
        if (this.veces == 6) {
            long ahora = System.currentTimeMillis();
            Log.d("MYAPP", Long.toString(ahora - inicio));
            Intent intent = new Intent(this, SplitView.class);
            startActivity(intent);

            salir();
        }
    }

    private void salir() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        } else {
            this.finish();
        }
    }
}
