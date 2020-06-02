package weather.entity;

import lombok.*;

import java.util.Observable;

@Getter
@ToString
@EqualsAndHashCode
public class City extends Observable {
    @NonNull
    private String name;
    private String country;
    private String continent;
    private static City city;

    private City(){}

    public static City getInstance(){
        if(city == null){
            city = new City();
        }
        return city;
    }

    public void setCity(String city){
        this.name = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void notifyObservers(){
        setChanged();
        notifyObservers(this);
    }
}
