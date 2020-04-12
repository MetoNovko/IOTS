package com.mps.services.impls;

import com.mps.models.Device;
import com.mps.models.Measurement;
import com.mps.models.User;
import com.mps.repositories.DeviceRepository;
import com.mps.repositories.MeasurementRepository;
import com.mps.services.DeviceService;
import com.mps.services.UserService;
import com.mps.transferables.DTOs.Device.CreateDeviceRequest;
import com.mps.transferables.DTOs.Device.EditDeviceRequest;
import com.mps.transferables.exceptions.DeviceNotFoundException;
import com.mps.transferables.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserService userService;
    private final MeasurementRepository measurementRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, UserService userService, MeasurementRepository measurementRepository) {
        this.deviceRepository = deviceRepository;
        this.userService = userService;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public Device getDevice(int id) {
        return this.deviceRepository.findById(id)
                .orElseThrow(DeviceNotFoundException::new);
    }

    @Override
    public List<Device> getDevices(String ownerUsername) {
        final User owner = this.userService.getUser(ownerUsername).orElseThrow(UserNotFoundException::new);
        return this.getDevices(owner);
    }

    @Override
    public List<Device> getDevices(User owner) {
        return this.deviceRepository.findAllByDeviceOwnerUsername(owner.getUsername());
    }

    @Override
    public Device addDevice(String ownerUsername, CreateDeviceRequest deviceRequest) {
        return this.deviceRepository.save(new Device(UUID.randomUUID().toString(),
                deviceRequest.getModel(),
                deviceRequest.getDescription(),
                deviceRequest.isPublic(),
                deviceRequest.getLongitude(),
                deviceRequest.getLatitude(),
                this.userService.getUser(ownerUsername).orElseThrow(UserNotFoundException::new),
                new HashSet<>()));
    }

    @Override
    public Device editDevice(EditDeviceRequest editDeviceRequest) {
        final Device device =
                this.deviceRepository.findById(editDeviceRequest.getDeviceId()).orElseThrow(DeviceNotFoundException::new);

        return this.editDevice(device, editDeviceRequest);
    }

    @Override
    public Device editDevice(Device device, EditDeviceRequest editDeviceRequest) {
        device.setDescription(editDeviceRequest.getDescription());
        device.setPublic(editDeviceRequest.isPublic());
        device.setModel(editDeviceRequest.getModel());
        device.setLatitude(editDeviceRequest.getLatitude());
        device.setLongitude(editDeviceRequest.getLongitude());

        return this.deviceRepository.save(device);
    }

    @Override
    public List<Measurement> getMeasurements(Device device) {
        return this.measurementRepository.findAllByDevice(device);
    }

    @Override
    public List<Measurement> getMeasurements(int deviceId) {
        final Device device = this.deviceRepository.findById(deviceId)
                .orElseThrow(DeviceNotFoundException::new);
        return this.getMeasurements(device);
    }

    @Override
    public void deleteDevice(Device device) {
        this.deviceRepository.delete(device);
    }

    @Override
    public void deleteDevice(int deviceId) {
        final Device device = this.deviceRepository.findById(deviceId).orElseThrow(DeviceNotFoundException::new);
        this.deleteDevice(device);
    }

    @Override
    public Optional<Measurement> getLastCount(Device device) {
        return Optional.ofNullable(this.measurementRepository.findTopByDeviceOrderByIdDesc(device));
    }

    @Override
    public Optional<Measurement> getLastCount(int deviceId) {
        final Device device = this.deviceRepository.findById(deviceId).orElseThrow(DeviceNotFoundException::new);
        return this.getLastCount(device);
    }
}
