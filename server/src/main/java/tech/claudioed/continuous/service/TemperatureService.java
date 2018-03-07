package tech.claudioed.continuous.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tech.claudioed.continuous.domain.Temperature;
import tech.claudioed.continuous.repository.DeviceRepository;
import tech.claudioed.continuous.repository.TemperatureRepository;
import tech.claudioed.continuous.resources.data.TemperatureRequest;

@Service
public class TemperatureService {

    private final TemperatureRepository temperatureRepository;

    private final DeviceRepository deviceRepository;

    public TemperatureService(TemperatureRepository temperatureRepository, DeviceRepository deviceRepository) {
        this.temperatureRepository = temperatureRepository;
      this.deviceRepository = deviceRepository;
    }

    public Mono<Temperature> register(TemperatureRequest request){
      return this.deviceRepository.findById(request.getDeviceId())
          .flatMap(device -> this.temperatureRepository.save(Temperature.builder().at(LocalDateTime.now()).device(device).value(request.getValue()).build()));
    }

}
