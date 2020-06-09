package weather.entity.geo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import weather.entity.forecast.Forecast;

import java.io.Serializable;
import java.util.Observable;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class GEO extends Observable implements Serializable {
    protected static final long serialVersionUID = 1L;
    private Area area;
    private City city;
    private Continent continent;
    private Country country;
    private Location location;
    private Forecast forecast;

    public void notifyObservers(){
        setChanged();
        notifyObservers(this);
    }
}
