package com.mps.transferables.DTOs.Device;

import com.mps.models.Device;

public class EditDeviceResponse {

    private int deviceId;

    private String description;

    private String model;

    private boolean isPublic;

    private double latitude;

    private double longitude;

    private String ownerUsername;

    public EditDeviceResponse(int deviceId, String description, String model, boolean isPublic, double latitude, double longitude, String ownerUsername) {
        this.deviceId = deviceId;
        this.description = description;
        this.model = model;
        this.isPublic = isPublic;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ownerUsername = ownerUsername;
    }

    public EditDeviceResponse() {
    }

    public EditDeviceResponse(Device device) {
        this.deviceId = device.getId();
        this.description = device.getDescription();
        this.isPublic = device.isPublic();
        this.model = device.getModel();
        this.ownerUsername = device.getDeviceOwner().getUsername();
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
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
