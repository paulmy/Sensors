package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DetailSensor extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    List<Sensor> sensors;
    TextView name, value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        name = findViewById(R.id.sensor_name);
        value = findViewById(R.id.sensor_value);
        String type = getIntent().getStringExtra("type");
        name.setText(type);
        Log.d("TEST", type);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        String type_sensor = "Sensor.TYPE_" + type;

        sensor = sensorManager.getDefaultSensor(getTypeSensors(type_sensor));
        Log.d("TEST", type_sensor);

        if (sensor == null) {
            name.setText(getResources().getText(R.string.sensor_not_found));
        } else {

            sensorManager.registerListener(mySensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    private int getTypeSensors(String type){
        switch (type){
            case "Sensor.TYPE_LIGHT":return Sensor.TYPE_LIGHT;
            case "Sensor.TYPE_GYROSCOPE":return Sensor.TYPE_GYROSCOPE;
            case "Sensor.TYPE_ACCELEROMETER":return Sensor.TYPE_ACCELEROMETER;
            case "Sensor.TYPE_MAGNETIC_FIELD":return Sensor.TYPE_MAGNETIC_FIELD;
            case "Sensor.TYPE_AMBIENT_TEMPERATURE":return Sensor.TYPE_AMBIENT_TEMPERATURE;
            case "Sensor.TYPE_GRAVITY":return Sensor.TYPE_GRAVITY;
            case "Sensor.TYPE_PROXIMITY":return Sensor.TYPE_PROXIMITY;
            case "Sensor.TYPE_ORIENTATION":return Sensor.TYPE_ORIENTATION;
        }
        return 0;
    }
    private final SensorEventListener mySensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
           value.setText(String.valueOf(event.values[0]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

}
