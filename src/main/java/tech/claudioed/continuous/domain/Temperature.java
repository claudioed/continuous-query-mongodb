package tech.claudioed.continuous.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Temperature {

    @Id
    String id;

    Device device;

    Double value;

}
