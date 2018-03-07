package tech.claudioed.continuous.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import tech.claudioed.continuous.domain.Device;

public interface DeviceRepository extends ReactiveCrudRepository<Device,String>{ }
