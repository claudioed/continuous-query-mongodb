package tech.claudioed.continuous.client.domain;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ContinuousClientApplication implements CommandLineRunner {

  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(ContinuousClientApplication.class);
    app.run(args);

  }

  @Override
  public void run(String... args) throws Exception {

    final WebClient deviceClient = WebClient.create("http://localhost:8080/api/devices");
    final WebClient temperatureClient = WebClient.create("http://localhost:8080/api/temperatures");

    Flux.range(1,5).flatMap(id ->{
      final Device device = Device.builder().id(String.valueOf(id)).name("Device " + id)
          .location("Place " + id).setup(
              Parameters.builder().min(BigDecimal.valueOf(id))
                  .max(BigDecimal.valueOf(id).add(BigDecimal.TEN)).build()).build();
      return deviceClient.post().body(BodyInserters.fromObject(device)).exchange();


    }).subscribe();

    Flux.interval(Duration.ofSeconds(1)).flatMap(value ->{
      final int device = 1 + (int)(Math.random() * ((5 - 1) + 1));
      final int temp = device + (int)(Math.random() * (((device + 10) - device) + 1));

      final TemperatureRequest temperatureRequest = TemperatureRequest.builder().deviceId(String.valueOf(device))
          .value(BigDecimal.valueOf(temp)).build();
      return temperatureClient.post().body(BodyInserters.fromObject(temperatureRequest)).exchange();


    }).subscribe();

    Flux.interval(Duration.ofSeconds(3)).flatMap(value ->{
      final int device = 1 + (int)(Math.random() * ((5 - 1) + 1));
      final int temp = device + (int)(Math.random() * (((device + 10) - device) + 1)) + 10;

      final TemperatureRequest temperatureRequest = TemperatureRequest.builder().deviceId(String.valueOf(device))
          .value(BigDecimal.valueOf(temp)).build();
      return temperatureClient.post().body(BodyInserters.fromObject(temperatureRequest)).exchange();


    }).subscribe();

    Flux.interval(Duration.ofSeconds(6)).flatMap(value ->{
      final int device = 1 + (int)(Math.random() * ((5 - 1) + 1));
      final int temp = device + (int)(Math.random() * (((device + 10) - device) + 1)) - 50;

      final TemperatureRequest temperatureRequest = TemperatureRequest.builder().deviceId(String.valueOf(device))
          .value(BigDecimal.valueOf(temp)).build();
      return temperatureClient.post().body(BodyInserters.fromObject(temperatureRequest)).exchange();


    }).subscribe();

  }
}
