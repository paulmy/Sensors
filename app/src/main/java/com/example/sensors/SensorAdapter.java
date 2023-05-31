package com.example.sensors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {
    interface OnSensorClickListener{
        void onSensorClick(SensorData sensorData, int position);
    }

    private final OnSensorClickListener onClickListener;
    private final LayoutInflater inflater;
    private final List<SensorData> sensorsData;

    SensorAdapter(Context context, List<SensorData> sensorData,OnSensorClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.sensorsData = sensorData;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public SensorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SensorAdapter.ViewHolder holder, int position) {
        SensorData sensor = sensorsData.get(position);
        holder.nameView.setText(sensor.getTypeSensor());
        holder.vendorView.setText(sensor.getVendor());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onSensorClick(sensor, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sensorsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, vendorView;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.name);
            vendorView = view.findViewById(R.id.vendor);
        }
    }
}

