package tech.claudioed.continuous.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "devices")
public class Device {

  @Id
  private String id;

  private String name;

  private String location;

  private Parameters setup;

}
