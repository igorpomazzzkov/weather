package weather.entity.geo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Country {
    private String capital;
    private String code;
    private Long geonameid;
    private String name;
    private Long population;
}