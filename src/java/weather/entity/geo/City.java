package weather.entity.geo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class City {
    private Long geonameid;
    private String name;
    private Long population;
}
