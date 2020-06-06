package weather.entity.forecast;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

@Data
@ToString
@EqualsAndHashCode
public class ForecastDate {
    private Integer time;
    private String icon;
    private Double precipIntensity;
    private Double temperature;
    private Double apparentTemperature;
    private Double humidity;
    private Double pressurel;
    private Double windSpeed;
    private Double windGust;

    public Date getDateTime() {
        return new Date((long) this.time * 1000);
    }
}
