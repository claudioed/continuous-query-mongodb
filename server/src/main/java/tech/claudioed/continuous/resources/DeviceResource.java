package tech.claudioed.continuous.resources;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.claudioed.continuous.domain.Device;
import tech.claudioed.continuous.domain.Temperature;
import tech.claudioed.continuous.service.DeviceService;

@RestController
@RequestMapping("/api/devices")
public class DeviceResource {

  private final DeviceService deviceService;

  public DeviceResource(DeviceService deviceService) {
    this.deviceService = deviceService;
  }

  @PostMapping
  public Mono<Device> newDevice(@RequestBody Device device){
    return this.deviceService.newDevice(device);
  }

  @GetMapping(value = "/{id}/max",produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
  public Flux<Temperature> reachMax(@PathVariable("id") String id){
    return this.deviceService.reachMax(id);
  }

  @GetMapping(value = "/{id}/min",produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
  public Flux<Temperature> reachMin(@PathVariable("id") String id){
    return this.deviceService.reachMin(id);
  }

  @GetMapping(value = "/{id}/stream",produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
  public Flux<Temperature> stream(@PathVariable("id") String id){
    return this.deviceService.streamFromDevice(id);
  }

}
