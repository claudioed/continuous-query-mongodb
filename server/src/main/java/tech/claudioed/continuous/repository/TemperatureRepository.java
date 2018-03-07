package tech.claudioed.continuous.repository;

import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import tech.claudioed.continuous.domain.Temperature;

public interface TemperatureRepository extends ReactiveCrudRepository<Temperature, String> {

  @Tailable
  Flux<Temperature> findByDeviceId(String deviceId);

  @Tailable
  Flux<Temperature> findByDeviceIdAndValueGreaterThan(String deviceId,Double value);

  @Tailable
  Flux<Temperature> findByDeviceIdAndValueLessThan(String deviceId,Double value);

}
