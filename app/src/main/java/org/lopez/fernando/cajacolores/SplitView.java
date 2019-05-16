package org.lopez.fernando.cajacolores;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class SplitView extends AppCompatActivity {

    private static int colores[] = { R.color.aguamarina, R.color.cian, R.color.dukeBlue, R.color.UCLABlue, R.color.verdeEsmeralda, R.color.black, R.color.white};
    private int indexColor = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_view);
    }
    public void dividir(View view) {
        LinearLayout padre = (LinearLayout)view;
        LinearLayout hijo1 = new LinearLayout(this);
        LinearLayout hijo2 = new LinearLayout(this);
        if ( padre.getOrientation() == LinearLayout.VERTICAL) {
            Log.d("SplitView_dividir", "Vertical");
//            android:layout_width="0dp"
//            android:layout_height="match_parent"
            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
//            android:layout_weight="1"
            parametros.weight = 1;

            hijo1.setOrientation( LinearLayout.HORIZONTAL);
            hijo1.setLayoutParams( parametros);

            hijo2.setOrientation( LinearLayout.HORIZONTAL);
            hijo2.setLayoutParams(parametros);
        } else {
            Log.d("SplitView_dividir", "Horizontal");
//            android:layout_width="match_parent"
//            android:layout_height="0dp"
            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 0);
//            android:layout_weight="1"
            parametros.weight = 1;

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
        hijo2.setBackgroundColor( getResources().getColor( colores[ indexColor]));
        indexColor++;
        if( indexColor == colores.length) {
            indexColor = 0;
        }
        padre.addView( hijo1);
        padre.addView( hijo2);
//        padre.invalidate();
        padre.requestLayout();
    }
}
