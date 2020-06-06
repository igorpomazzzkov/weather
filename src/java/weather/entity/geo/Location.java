package weather.entity.geo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Location {
    private Double latitude;
    private Double longitude;
}
