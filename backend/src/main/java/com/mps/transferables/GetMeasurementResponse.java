package com.mps.transferables;

import com.mps.models.Measurement;

public class GetMeasurementResponse {

    private long id;

    private long timestamp;

    private int count;

    public GetMeasurementResponse(long id, long timestamp, int count) {
        this.id = id;
        this.timestamp = timestamp;
        this.count = count;
    }

    public GetMeasurementResponse() {
    }

    public GetMeasurementResponse(Measurement measurement) {
        this.id = measurement.getId();
        this.timestamp = measurement.getTimestamp();
        this.count = measurement.getCount();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
