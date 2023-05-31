package com.example.sensors;

public class SensorData {
    private String type;
    private String vendor;

    public SensorData(String type, String vendor) {
        this.type = type;
        this.vendor = vendor;
    }

    public String getTypeSensor() {
        return type;
    }

    public void setTypeSensor(String type) {
        this.type = type;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
