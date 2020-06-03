package weather.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import weather.configuration.Serialization;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

@Data
@ToString
@EqualsAndHashCode
public class GEO extends Observable implements Serializable {
    protected static final long serialVersionUID = 1L;
    private Area area;
    private City city;
    private Continent continent;
    private Country country;
    private Location location;

    public void notifyObservers(){
        setChanged();
        notifyObservers(this);
    }
}
