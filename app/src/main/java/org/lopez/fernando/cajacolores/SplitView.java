package org.lopez.fernando.cajacolores;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class SplitView extends AppCompatActivity {

    private static final int COLORES[] = { R.color.aguamarina, R.color.cian, R.color.dukeBlue, R.color.UCLABlue, R.color.verdeEsmeralda, R.color.black, R.color.white};
    private int indexColor = 0;
    private static Random r = new Random();

    // https://github.com/flcarballeda/CajaColores.git
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_view);
        Toast toast = Toast.makeText( this, "Pulsa para dividir la zona.", Toast.LENGTH_LONG);
        toast.show();

    }

    private void salir() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        } else {
            this.finish();
        }
    }

    public void dividir(View view) {
        LinearLayout padre = (LinearLayout)view;
        int size = Math.min( view.getWidth(), view.getHeight());
        if (48 > size) {
            salir();
            return;
        }
//        LinearLayout padre = (LinearLayout)findViewById( view.getId());
        LinearLayout hijo1 = new LinearLayout(this);
        hijo1.setId( newId());
        LinearLayout hijo2 = new LinearLayout(this);
        hijo2.setId( newId());
        if ( padre.getOrientation() == LinearLayout.VERTICAL) {
            Log.d("SplitView_dividir", "Vertical");
//        android:layout_width="match_parent"
//        android:layout_height="0dp"
//        android:layout_weight="1"
            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 0, 1F);

//        android:orientation="horizontal"
            hijo1.setOrientation( LinearLayout.HORIZONTAL);
            hijo1.setLayoutParams( parametros);

            hijo2.setOrientation( LinearLayout.HORIZONTAL);
            hijo2.setLayoutParams(parametros);
        } else {
            Log.d("SplitView_dividir", "Horizontal");
//            android:layout_width="0dp"
//            android:layout_height="match_parent"
//            android:layout_weight="1"
            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.MATCH_PARENT, 1F);

//        android:orientation="vertical"
            hijo1.setOrientation( LinearLayout.VERTICAL);
            hijo1.setLayoutParams( parametros);

            hijo2.setOrientation( LinearLayout.VERTICAL);
            hijo2.setLayoutParams( parametros);
        }
        hijo1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dividir(v);
            }
        });
        hijo2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dividir(v);
            }
        });
        hijo1.setBackgroundColor( ((ColorDrawable) padre.getBackground()).getColor());
        hijo2.setBackgroundColor( getResources().getColor( COLORES[ indexColor]));
        hijo1.setVisibility( View.VISIBLE);
        hijo2.setVisibility( View.VISIBLE);
        indexColor++;
        if( indexColor == COLORES.length) {
            indexColor = 0;
        }
        padre.addView( hijo1);
        padre.addView( hijo2);
    }

    /**
     * Es el ID calculado
     * Va generando números aleatorios. Comprueba que el valor generado
     * no coincide con un ID existente y si no existe devuelvo el nuevo
     * valor.
     *
     * @return
     */
    private int newId() {
        int resultado = -1;
        do {
            resultado = r.nextInt(Integer.MAX_VALUE);
        } while( findViewById( resultado) != null);
        return resultado;
    }
}
