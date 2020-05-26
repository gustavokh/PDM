package com.example.aula9;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Exercicio extends AppCompatActivity implements SensorEventListener {
    private LinearLayout layout;
    private SensorManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio);

        layout = (LinearLayout) findViewById(R.id.fundoExercicio);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            if ( (z > 1.2 && z < 1.8) || (z < -1.2 && z > -1.8)) {
                layout.setBackgroundColor(Color.GREEN);
            }
            else if ( (z > 0.0 && z < 0.2) || (z < -3.14 && z > -3.44)) {
                layout.setBackgroundColor(Color.BLUE);
            }
            if ( (x > 1.27 && x < 1.8) ) {
                finish();
            }
            else if ( (x < -1.27 && x > -1.87) ) {
                layout.setBackgroundColor(Color.RED);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        manager.unregisterListener(this);
    }
}
