package com.mps.repositories;

import com.mps.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    List<Device> findAllByDeviceOwnerUsername(String username);
}
