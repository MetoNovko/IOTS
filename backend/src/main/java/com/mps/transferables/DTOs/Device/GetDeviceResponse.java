package com.mps.transferables.DTOs.Device;

import com.mps.models.Device;

public class GetDeviceResponse {

    private int deviceId;

    private String model;

    private String description;

    private boolean isPublic;

    private double latitude;

    private double longitude;

    private String ownerUsername;

    public GetDeviceResponse(int deviceId, String model, String description, double latitude, double longitude, String ownerUsername) {
        this.deviceId = deviceId;
        this.model = model;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ownerUsername = ownerUsername;
    }

    public GetDeviceResponse() {
    }

    public GetDeviceResponse(Device device) {
        this.description = device.getDescription();
        this.deviceId = device.getId();
        this.model = device.getModel();
        this.ownerUsername = device.getDeviceOwner().getUsername();
        this.isPublic = device.isPublic();
        this.latitude = device.getLatitude();
        this.longitude = device.getLongitude();
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
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

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
