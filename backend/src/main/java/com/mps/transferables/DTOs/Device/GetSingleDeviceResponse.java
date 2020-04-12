package com.mps.transferables.DTOs.Device;

import com.mps.models.Device;
import com.mps.transferables.DTOs.Measurement.GetMeasurementResponse;

import java.util.List;
import java.util.stream.Collectors;

public class GetSingleDeviceResponse {

    private int deviceId;

    private String model;

    private String description;

    private List<GetMeasurementResponse> measurements;

    private boolean isPublic;

    private String secret;

    private double latitude;

    private double longitude;

    private String ownerUsername;

    public GetSingleDeviceResponse(int deviceId, String model, String description, List<GetMeasurementResponse> measurements, boolean isPublic, String secret, double latitude, double longitude, String ownerUsername) {
        this.deviceId = deviceId;
        this.model = model;
        this.description = description;
        this.measurements = measurements;
        this.isPublic = isPublic;
        this.secret = secret;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ownerUsername = ownerUsername;
    }

    public GetSingleDeviceResponse(Device device) {
        this.description = device.getDescription();
        this.model = device.getModel();
        this.deviceId = device.getId();
        this.isPublic = device.isPublic();
        this.secret = device.getSecretKey();
        this.ownerUsername = device.getDeviceOwner().getUsername();
        this.latitude = device.getLatitude();
        this.longitude = device.getLongitude();
        this.measurements =
                device.getMeasurements()
                        .stream()
                        .map(GetMeasurementResponse::new)
                        .collect(Collectors.toList());
    }

    public GetSingleDeviceResponse() {
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

    public List<GetMeasurementResponse> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<GetMeasurementResponse> measurements) {
        this.measurements = measurements;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
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
