package tech.claudioed.continuous.resources;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import tech.claudioed.continuous.domain.Temperature;
import tech.claudioed.continuous.resources.data.TemperatureRequest;
import tech.claudioed.continuous.service.TemperatureService;

@RestController
@RequestMapping("/api/temperatures")
public class TemperatureResource {

  private final TemperatureService temperatureService;

  public TemperatureResource(TemperatureService temperatureService) {
    this.temperatureService = temperatureService;
  }

  @PostMapping
  public Mono<ResponseEntity<Temperature>> register(@RequestBody TemperatureRequest temperatureRequest, UriComponentsBuilder uriBuilder) {
    return this.temperatureService.register(temperatureRequest).map(temperature -> {
      URI location = uriBuilder.path("/temperatures/{id}")
          .buildAndExpand(temperature.getId())
          .toUri();
      return ResponseEntity.created(location).build();
    });
  }

}
