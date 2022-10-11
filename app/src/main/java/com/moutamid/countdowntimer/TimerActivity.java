package com.moutamid.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {
    String hour, min, sec;
    TextView hourTV, minTV, secTV;
    int duration;
    Button cancel, pause;
    boolean state = true;
    Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        hour = getIntent().getStringExtra("hour");
        min = getIntent().getStringExtra("min");
        sec = getIntent().getStringExtra("sec");

        hourTV = findViewById(R.id.hourTV);
        minTV = findViewById(R.id.minTV);
        secTV = findViewById(R.id.secTV);
        cancel = findViewById(R.id.cancel);
        pause = findViewById(R.id.pause);

        duration = (Integer.parseInt(hour) * 60 * 60) + (Integer.parseInt(min) * 60) + Integer.parseInt(sec);

        CountDownTimer timer = new CountDownTimer(duration * 1000, 1000){

            @Override
            public void onTick(long l) {
                runOnUiThread(() -> {
                    String time = String.format(Locale.getDefault(),"%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(l),
                            TimeUnit.MILLISECONDS.toMinutes(l) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(l)),
                            TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                    final String[] hourMinSec = time.split(":");
                    hourTV.setText(hourMinSec[0]);
                    minTV.setText(hourMinSec[1]);
                    secTV.setText(hourMinSec[2]);
                });
            }

            @Override
            public void onFinish() {
                ringtone.play();
            }

        };

        timer.start();

        pause.setOnClickListener(v -> {
            if (state){
                timer.cancel();
                pause.setText("Resume");
                state = false;
            } else {
                int hour = Integer.parseInt(hourTV.getText().toString());
                int min = Integer.parseInt(minTV.getText().toString());
                int sec = Integer.parseInt(secTV.getText().toString());
                Toast.makeText(this, ""+sec, Toast.LENGTH_SHORT).show();
                duration = (hour * 60 * 60) + (min * 60) + sec;
                timer.start();
                pause.setText("Pause");
                state = true;
            }
        });

        cancel.setOnClickListener(v -> {
            ringtone.stop();
            timer.cancel();
            startActivity(new Intent(TimerActivity.this, MainActivity.class));
            finish();
        });
    }
}