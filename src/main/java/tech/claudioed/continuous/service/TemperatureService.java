package tech.claudioed.continuous.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import tech.claudioed.continuous.domain.Temperature;
import tech.claudioed.continuous.repository.TemperatureRepository;

@Service
public class TemperatureService {

    private final TemperatureRepository temperatureRepository;

    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public Flux<Temperature> findByDeviceId(String deviceId){
        return this.temperatureRepository.findByDeviceId(deviceId);
    }


}
