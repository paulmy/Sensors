package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ArrayList<SensorData> sensorsData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialData();



        SensorAdapter.OnSensorClickListener sensorClickListener = new SensorAdapter.OnSensorClickListener() {
            @Override
            public void onSensorClick(SensorData sensor, int position) {
                Intent intent = new Intent(MainActivity.this, DetailSensor.class);
                intent.putExtra("type",sensor.getTypeSensor());
                startActivity(intent);

            }
        };
        SensorAdapter adapter = new SensorAdapter(this, sensorsData, sensorClickListener);
        RecyclerView recyclerView = findViewById(R.id.container_rec);
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() {
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (int i = 0; i < deviceSensors.size(); i++) {
            String [] type = deviceSensors.get(i).getStringType().split("\\.");
            sensorsData.add(new SensorData((type[type.length-1]).toUpperCase(), deviceSensors.get(i).getVendor()));
        }

    }
}