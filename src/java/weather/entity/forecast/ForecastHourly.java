package weather.entity.forecast;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
public class ForecastHourly {
    private String summary;
    private String icon;
    private List<ForecastDate> data;
}
