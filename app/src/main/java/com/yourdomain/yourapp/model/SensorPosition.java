package com.yourdomain.yourapp.model;

public class SensorPosition {
    private String sensorId;
    private double latitude;
    private double longitude;

    public SensorPosition(String sensorId, double latitude, double longitude) {
        this.sensorId = sensorId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getSensorId() {
        return sensorId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
