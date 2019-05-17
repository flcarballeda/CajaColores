package org.lopez.fernando.cajacolores;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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
        inicio = -1l;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void cambiarColor(View view) {
        LinearLayout linear = (LinearLayout) view;
        int color = ((ColorDrawable) linear.getBackground()).getColor();
        if (this.negro == color) {
            return;
        }
        linear.setBackgroundColor(negro);
        if (-1 == inicio) {
            inicio = System.currentTimeMillis();
        }
        this.veces += 1;
        if (this.veces == 6) {
            long ahora = System.currentTimeMillis();
            String mensaje = String.format( "Ha tardado %1$.3f segundos.", ((ahora - inicio) / 1000f));
            Log.d("MYAPP", mensaje);
            Toast toast = Toast.makeText( this, mensaje, Toast.LENGTH_LONG);
            toast.show();

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
