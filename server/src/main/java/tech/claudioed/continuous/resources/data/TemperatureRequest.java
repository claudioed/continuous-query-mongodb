package tech.claudioed.continuous.resources.data;

import lombok.Data;

@Data
public class TemperatureRequest {

  private String deviceId;

  private Double value;

}
