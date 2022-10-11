package com.moutamid.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NumberPicker hourPicker, minPicker, secPicker;
    int hour, min, sec;
    Button start;
    TextView oneH, tenM, tenS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hourPicker = findViewById(R.id.numberHour);
        minPicker = findViewById(R.id.numberMin);
        secPicker = findViewById(R.id.numberSec);
        start = findViewById(R.id.startTimer);
        oneH = findViewById(R.id.oneHour);
        tenM = findViewById(R.id.tenMin);
        tenS = findViewById(R.id.tenSec);

        hourPicker.setMinValue(00);
        hourPicker.setMaxValue(99);

        minPicker.setMinValue(00);
        minPicker.setMaxValue(59);

        secPicker.setMinValue(00);
        secPicker.setMaxValue(59);

        hourPicker.setOnValueChangedListener((numberPicker, i, i1) -> hour = numberPicker.getValue());

        minPicker.setOnValueChangedListener((numberPicker, i, i1) -> min = numberPicker.getValue());

        secPicker.setOnValueChangedListener((numberPicker, i, i1) -> sec = numberPicker.getValue());

        start.setOnClickListener(v -> {
            hour = hourPicker.getValue();
            min = minPicker.getValue();
            sec = secPicker.getValue();
            if (sec == 0){
                Toast.makeText(this, "Please Set Time", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(MainActivity.this, TimerActivity.class);
                i.putExtra("hour", String.valueOf(hour));
                i.putExtra("min", String.valueOf(min));
                i.putExtra("sec", String.valueOf(sec));
                startActivity(i);
            }
        });

        oneH.setOnClickListener(v -> {
            hourPicker.setValue(1);
            minPicker.setValue(0);
            secPicker.setValue(0);
        });

        tenM.setOnClickListener(v -> {
            hourPicker.setValue(0);
            minPicker.setValue(10);
            secPicker.setValue(0);
        });

        tenS.setOnClickListener(v -> {
            hourPicker.setValue(0);
            minPicker.setValue(0);
            secPicker.setValue(10);
        });

    }
}