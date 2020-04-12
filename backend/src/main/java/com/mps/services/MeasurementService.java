package com.mps.services;

import com.mps.models.Measurement;
import com.mps.transferables.DTOs.Measurement.GetMeasurementResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeasurementService {

    Measurement saveCount(GetMeasurementResponse measurement);

    Measurement saveCount(int deviceId, String deviceSecret, int count);

    void saveCounts(List<GetMeasurementResponse> counts);
}
