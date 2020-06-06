package weather.entity.forecast;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;

@Data
@EqualsAndHashCode
@ToString
public class ForecastHourly {
    private String summary;
    private String icon;
    private ArrayList<ForecastDate> data;
}
