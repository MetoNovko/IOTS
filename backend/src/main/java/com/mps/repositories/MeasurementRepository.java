package com.mps.repositories;

import com.mps.models.Device;
import com.mps.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    List<Measurement> findAllByDevice(Device device);

    List<Measurement> findAllByTimestampBetween(long start, long end);

    List<Measurement> findAllByCountLessThan(int count);

    Measurement findTopByDeviceOrderByIdDesc(Device device);

}
