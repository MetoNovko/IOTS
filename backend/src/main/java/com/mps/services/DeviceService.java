package com.mps.services;

import com.mps.models.Device;
import com.mps.models.Measurement;
import com.mps.models.User;
import com.mps.transferables.DTOs.Device.CreateDeviceRequest;
import com.mps.transferables.DTOs.Device.EditDeviceRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DeviceService {

    @PostAuthorize("returnObject.public || returnObject.deviceOwner.username == authentication.principal.username")
    Device getDevice(int id);

    List<Device> getDevices(String ownerUsername);

    @PostFilter("filterObject.public || #owner.username == authentication.principal.username")
    List<Device> getDevices(User owner);

    Device addDevice(String ownerUsername, CreateDeviceRequest deviceRequest);

    Device editDevice(EditDeviceRequest editDeviceRequest);

    @PreAuthorize("authentication.principal.username == #device.deviceOwner.username")
    Device editDevice(Device device, EditDeviceRequest editDeviceRequest);

    @PreAuthorize("#device.isPublic || #device.deviceOwner.username == authentication.principal.username")
    List<Measurement> getMeasurements(Device device);

    List<Measurement> getMeasurements(int deviceId);

    @PreAuthorize("authentication.principal.username == #device.deviceOwner.username")
    void deleteDevice(Device device);

    void deleteDevice(int deviceId);

    @PreAuthorize("#device.isPublic || #device.deviceOwner == authentication.principal.username")
    Optional<Measurement> getLastCount(Device device);

    Optional<Measurement> getLastCount(int deviceId);
}
