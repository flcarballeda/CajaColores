package org.lopez.fernando.cajacolores;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String MYAPP = "MYAPP";
    private int negro;
    private int veces;
    private long inicio;
    private long acumulado;
    private boolean corriendo;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        negro = getResources().getColor(R.color.black);
        inicio = -1l;
        acumulado = 0l;
        corriendo = true;
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            nombre = bd.getString(Constantes.NOM_RECORD_CAJACOLORES, null);
        }
        LinearLayout opacar = (LinearLayout) findViewById(R.id.opacarColores);
        opacar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.play_pause: {
                if (corriendo) {
                    if (-1 != inicio) {
                        long ahora = System.currentTimeMillis();
                        Log.d(MYAPP, "Parar - Acumulado." + Long.toString(acumulado));
                        acumulado += (ahora - inicio);
                        Log.d(MYAPP, "Parar - Acumulado." + Long.toString(acumulado));
                    }
                    corriendo = false;
                    item.setIcon(R.drawable.ic_play_circle_outline_black_24dp);
                    LinearLayout opacar = (LinearLayout) findViewById(R.id.opacarColores);
                    opacar.setVisibility(View.VISIBLE);
                } else {
                    inicio = System.currentTimeMillis();
                    corriendo = true;
                    Log.d(MYAPP, "Seguir - Acumulado." + Long.toString(acumulado));
                    item.setIcon(R.drawable.ic_pause_circle_outline_black_24dp);
                    LinearLayout opacar = (LinearLayout) findViewById(R.id.opacarColores);
                    opacar.setVisibility(View.INVISIBLE);
                }
            }
            break;
            case R.id.version_original: {
                Log.d(MYAPP, "Menú Original.");
                // Lanzar la versión del Juego Original
                Intent intent = new Intent(this, MainActivity.class);
                this.finish();
                startActivity(intent);
            }
            break;
            case R.id.version_dividir: {
                Log.d(MYAPP, "Menú Dividir.");
                // Lanzar la versión del Juego Dividir.
                Intent intent = new Intent(this, GetSquareActivity.class);
                this.finish();
                startActivity(intent);
            }
            break;
            case R.id.configuracion: {
                Log.d(MYAPP, "Menú Configuración.");
                // Lanzar la configuración del juego.
            }
            break;
            default: {
                Log.d(MYAPP, String.format("Se ha recibido un ID desconocido: '%1$d'.", item.getItemId()));
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void cambiarColor(View view) {
        if (!corriendo) {
            return;
        }
        LinearLayout linear = (LinearLayout) view;
        int color = ((ColorDrawable) linear.getBackground()).getColor();
        if (this.negro == color) {
            if (this.veces == 6) {
                Toast toast = Toast.makeText(this, "Juego Terminado.", Toast.LENGTH_LONG);
                toast.show();
            }
            return;
        }
        linear.setBackgroundColor(negro);
        if (-1 == inicio) {
            inicio = System.currentTimeMillis();
        }
        this.veces += 1;
        if (this.veces == 6) {
            long ahora = System.currentTimeMillis();
            acumulado += (ahora - inicio);
            Log.d(MYAPP, "Acumulado." + Long.toString(acumulado));
            String mensaje = String.format(getResources().getString(R.string.messageLongTime), ((acumulado) / 1000f));
            Log.d(MYAPP, mensaje);
            Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
            toast.show();
            UserPreferences up = new UserPreferences(this);
            UserPreferences.Datos datos = up.getCajacoloresRecord();
            if (null != datos) {
                if (datos.getTime() <= acumulado) {
                    return;
                }
            }
            up.setCajacoloresRecord(nombre, acumulado);
            soundScore();
        }
    }

    private void soundScore() {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.record);
        mp.setLooping(false);
        mp.setVolume(100, 100);
        mp.start();
    }

    private void salir() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        } else {
            this.finish();
        }
    }
}
