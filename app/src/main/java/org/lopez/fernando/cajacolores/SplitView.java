package org.lopez.fernando.cajacolores;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SplitView extends AppCompatActivity {

    // private static final int COLORES[] = {R.color.aguamarina, R.color.cian, R.color.dukeBlue, R.color.UCLABlue, R.color.verdeEsmeralda, R.color.black, R.color.white};
    public static final String SPLIT_VIEW_DIVIDIR = "SplitView_dividir";
//    private int indexColor = 0;
    private static Random r = new Random();
    private int cuenta;
    private double DISTANCIA_MINIMA;

    // https://github.com/flcarballeda/CajaColores.git
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_view);
        cuenta = 0;
        DISTANCIA_MINIMA = getDistancia(Color.rgb(0, 0, 0),
                Color.rgb( 255, 255, 255)) / 4;
    }

    private int generaColor( int colorOriginal) {
        int nuevoColor = -1;
        do {
            int red = r.nextInt( 255);
            int green = r.nextInt( 255);
            int blue = r.nextInt( 255);
            nuevoColor = Color.rgb( red, green, blue);
        } while (nuevoColor == -1 && (getDistancia(colorOriginal, nuevoColor) < DISTANCIA_MINIMA));
        return nuevoColor;
    }

    // Calcula la distancia en el espacio tridimensional considerando los colores como coordenadas.
    private double getDistancia( int colorOriginal, int colorPropuesto) {
        // Extrae las componentes RGB de cada color
        float r1 = Color.red( colorOriginal);
        float r2 = Color.red( colorPropuesto);
        float g1 = Color.green( colorOriginal);
        float g2 = Color.green( colorPropuesto);
        float b1 = Color.blue( colorOriginal);
        float b2 = Color.blue( colorPropuesto);

        double redDis = (r1 -r2);
        double greenDis = (g1 - g2);
        double blueDis = (b1 -b2);
        return Math.sqrt( Math.pow( redDis, 2) + Math.pow( greenDis, 2) + Math.pow( blueDis, 2));
    }
//    private void salir() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            this.finishAffinity();
//        } else {
//            this.finish();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.version_original: {
                Log.d(SPLIT_VIEW_DIVIDIR, "Menú Original.");
                // Lanzar la versión del Juego Original
                Intent intent = new Intent(this, MainActivity.class);
                this.finish();
                startActivity(intent);
            }
            break;
            case R.id.version_dividir: {
                Log.d(SPLIT_VIEW_DIVIDIR, "Menú Dividir.");
                // Lanzar la versión del Juego Dividir.
                Intent intent = new Intent(this, SplitView.class);
                this.finish();
                startActivity(intent);
            }
            break;
            case R.id.configuracion: {
                Log.d(SPLIT_VIEW_DIVIDIR, "Menú Configuración.");
                // Lanzar la configuración del juego.
            }
            break;
            default: {
                Log.d(SPLIT_VIEW_DIVIDIR, String.format("Se ha recibido un ID desconocido: '%1$d'.", item.getItemId()));
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    //
    public void dividir(View view) {
        LinearLayout padre = (LinearLayout) view;
        int size = Math.min(view.getWidth(), view.getHeight());
        if (48 < size && 50 > cuenta) {
            addHijos(padre);
            cuenta++;
            Toast toast = Toast.makeText(this, String.format("Quedan '%1$d' cuadros", (50 - cuenta)), Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if (49 < cuenta) {
                Toast toast = Toast.makeText(this, "Juego terminado.\nYa hay 50 cuadros", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        recorreVista();
    }

    // Añade dos hijos al LinearLayout que recibe.
    private void addHijos(LinearLayout padre) {
        int numHijos = r.nextInt( 2) + 2; // El numero de hijos está entre 2 y 4
        for( int i = 0; i < numHijos; i++) {
            LinearLayout hijo1 = newHijo(padre.getOrientation());
            hijo1.setBackgroundColor( generaColor( ((ColorDrawable) padre.getBackground()).getColor()));
            padre.addView(hijo1);
        }
    }

    // Crea un hijo para añadir cambiando la orientación.
    private LinearLayout newHijo(int orientacion) {
        LinearLayout hijo1 = new LinearLayout(this);
        hijo1.setId(newId());
        if (orientacion == LinearLayout.VERTICAL) {
            Log.d(SPLIT_VIEW_DIVIDIR, "Vertical");
//        android:layout_width="match_parent"
//        android:layout_height="0dp"
//        android:layout_weight="1"
            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1F);

//        android:orientation="horizontal"
            hijo1.setOrientation(LinearLayout.HORIZONTAL);
            hijo1.setLayoutParams(parametros);
        } else {
            Log.d(SPLIT_VIEW_DIVIDIR, "Horizontal");
//            android:layout_width="0dp"
//            android:layout_height="match_parent"
//            android:layout_weight="1"
            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1F);

//        android:orientation="vertical"
            hijo1.setOrientation(LinearLayout.VERTICAL);
            hijo1.setLayoutParams(parametros);
        }
        hijo1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dividir(v);
            }
        });
        hijo1.setVisibility(View.VISIBLE);
        return hijo1;
    }

    private void recorreVista() {
        LinearLayout padre = (LinearLayout) findViewById(R.id.root);
        // Create an empty queues for simultaneous traversals
        ArrayList<LinearLayout> recorridos = new ArrayList<>();
        ArrayList<LinearLayout> pendientes = new ArrayList<>();

        // Enqueue Roots of trees in respective queues
        pendientes.add(padre);
        boolean adds;
        do { // ¿ O log O ?
            adds = false;
            LinearLayout current = null;
            // Busca el siguiente nodo pendiente de recorrer
            while (null == current && pendientes.size() > 0) {
                current = pendientes.remove( 0);
                if( recorridos.contains( current)) {
                   current = null;
                } else {
                    for (int i = 0; i< current.getChildCount(); i++) {
                        LinearLayout vistahija = (LinearLayout)current.getChildAt(i);
                        if( !recorridos.contains( vistahija) &&
                             !pendientes.contains( vistahija)) {
                            pendientes.add( (LinearLayout) vistahija);
                            adds = true;
                        }
                    }
                    recorridos.add( current);
                    current = null;
                }
            }
        } while (adds);
        Log.d( SPLIT_VIEW_DIVIDIR, Integer.toString( recorridos.size()));
    }

    /**
     * Es el ID calculado
     * Va generando números aleatorios. Comprueba que el valor generado
     * no coincide con un ID existente y si no existe devuelvo el nuevo
     * valor.
     *
     * @return int un entero que se puede utilizar como ID.
     */
    private int newId() {
        int resultado = -1;
        do {
            resultado = r.nextInt(Integer.MAX_VALUE);
        } while (findViewById(resultado) != null);
        return resultado;
    }
}
