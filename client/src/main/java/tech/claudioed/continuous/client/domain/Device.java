package tech.claudioed.continuous.client.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {

  private String id;

  private String name;

  private String location;

  private Parameters setup;

}
