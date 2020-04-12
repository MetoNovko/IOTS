package com.mps.transferables.DTOs.Device;

import com.mps.models.Device;

public class CreateDeviceResponse {

    private int id;

    private String secret;

    private String model;

    private String description;

    private double longitude;

    private double latitude;

    public CreateDeviceResponse(int id, String secret, String model, String description, double longitude, double latitude) {
        this.id = id;
        this.secret = secret;
        this.model = model;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public CreateDeviceResponse(Device device) {
        this.id = device.getId();
        this.description = device.getDescription();
        this.secret = device.getSecretKey();
        this.model = device.getModel();
        this.latitude = device.getLatitude();
        this.longitude = device.getLongitude();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
