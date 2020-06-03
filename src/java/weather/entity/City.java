package weather.entity;

import lombok.*;
import weather.configuration.Serialization;

import java.io.Serializable;
import java.util.Observable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class City {
    private Long geonameid;
    private String name;
    private Long population;
}
