package tech.claudioed.continuous.client.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Temperature {

  String id;

  Device device;

  BigDecimal value;

  LocalDateTime at;

}
