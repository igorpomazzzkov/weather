package weather.entity.forecast;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Forecast {
    private Double latitude;
    private Double longitude;
    private ForecastDate currently;
    private ForecastHourly hourly;
    private ForecastHourly daily;
}
