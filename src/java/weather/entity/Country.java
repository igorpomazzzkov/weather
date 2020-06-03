package weather.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
public class Country {
    private String areaSize;
    private String capital;
    private String code;
    private Long geonameid;
    private String name;
    private Long population;
}
