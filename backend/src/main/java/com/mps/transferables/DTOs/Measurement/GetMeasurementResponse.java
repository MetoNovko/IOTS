package com.mps.transferables.DTOs.Measurement;

import com.mps.models.Measurement;

public class GetMeasurementResponse {

    private int deviceId;

    private long timestamp;

    private int count;

    public GetMeasurementResponse(int deviceId, long timestamp, int count) {
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.count = count;
    }

    public GetMeasurementResponse() {

    }

    public GetMeasurementResponse(Measurement measurement) {
        this.deviceId = measurement.getDevice().getId();
        this.timestamp = measurement.getTimestamp();
        this.count = measurement.getCount();
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
