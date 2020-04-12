package com.mps.transferables.DTOs.Device;

public class EditDeviceRequest {

    private int deviceId;

    private String description;

    private String model;

    private boolean isPublic;

    private double latitude;

    private double longitude;

    public EditDeviceRequest(int deviceId, String description, String model, boolean isPublic, double latitude, double longitude) {
        this.deviceId = deviceId;
        this.description = description;
        this.model = model;
        this.isPublic = isPublic;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public EditDeviceRequest() {
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
