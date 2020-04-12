package com.mps.services.impls;

import com.mps.models.Device;
import com.mps.models.Measurement;
import com.mps.repositories.DeviceRepository;
import com.mps.repositories.MeasurementRepository;
import com.mps.services.MeasurementService;
import com.mps.transferables.DTOs.Measurement.GetMeasurementResponse;
import com.mps.transferables.exceptions.DeviceNotFoundException;
import com.mps.transferables.exceptions.WrongDeviceSecretException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final DeviceRepository deviceRepository;


    public MeasurementServiceImpl(MeasurementRepository measurementRepository, DeviceRepository deviceRepository) {
        this.measurementRepository = measurementRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Measurement saveCount(GetMeasurementResponse measurement) {
        final Measurement m =
                new Measurement(
                        measurement.getTimestamp() == 0 ? Instant.now().toEpochMilli() : measurement.getTimestamp(),
                        measurement.getCount(),
                        this.deviceRepository.findById(measurement.getDeviceId())
                                .orElseThrow(DeviceNotFoundException::new)
                );

        return this.measurementRepository.save(m);
    }

    @Override
    public Measurement saveCount(int deviceId, String deviceSecret, int count) {
        final Device device = this.deviceRepository.findById(deviceId)
                .orElseThrow(DeviceNotFoundException::new);

        if (!device.getSecretKey().equals(deviceSecret))
            throw new WrongDeviceSecretException();

        return this.measurementRepository.save(
                new Measurement(Instant.now().toEpochMilli(), count, device));
    }

    @Override
    public void saveCounts(List<GetMeasurementResponse> counts) {
        counts.forEach(this::saveCount);
    }

}
