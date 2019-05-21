package org.lopez.fernando.cajacolores;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

public class GetSquareActivity extends AppCompatActivity {

    public static final String INTENT_PARAMETER_SQUARES = "IntentParameterSquares";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_square);
        NumberPicker numbers = findViewById(R.id.numberSquares);
        final Context context = this;
        numbers.setMinValue(30);
        numbers.setMaxValue(100);
        numbers.setValue(50);
        numbers.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Intent intent = new Intent(context, SplitView.class);
                intent.putExtra(INTENT_PARAMETER_SQUARES, newVal);
                context.startActivity(intent);
            }
        });
    }
}
