package com.mps.transferables.DTOs.Device;

public class CreateDeviceRequest {

    private String model;

    private String description;

    private boolean isPublic;

    private double longitude;

    private double latitude;

    public CreateDeviceRequest(String model, String description, boolean isPublic, double longitude, double latitude) {
        this.model = model;
        this.description = description;
        this.isPublic = isPublic;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public double getLongitude() {
        return this.longitude;
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
