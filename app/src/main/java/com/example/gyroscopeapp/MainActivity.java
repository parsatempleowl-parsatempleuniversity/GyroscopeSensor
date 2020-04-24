package com.example.gyroscopeapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.Context;
import android.widget.TextView;
import android.view.View;
import android.graphics.Color;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {
    TextView textZ;
    SensorManager sensorManager;
    Sensor sensor;
    View view;
    long lastUpdate;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.textZ);
        view.setBackgroundColor(Color.GREEN);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        lastUpdate = System.currentTimeMillis();

        textZ = findViewById(R.id.textZ);

    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(gyroListener);
    }

    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroListener);
    }

    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) { }

        public void onSensorChanged(SensorEvent event) {
            float z = event.values[2];

            textZ.setText("Z : " + (int)z + " rad/s");

            if ((int)z < -3) {
                view.setBackgroundColor(Color.GREEN);
            }
            if ((int)z > 3) {
                view.setBackgroundColor(Color.RED);
            }
        }

    };
}