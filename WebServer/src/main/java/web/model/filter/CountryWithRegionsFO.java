package web.model.filter;

import mybatis.model.basic.Country;
import mybatis.model.complex.Region;

import java.util.List;

/**
 * Created by Irrielde on 26.3.2015.
 */
public class CountryWithRegionsFO {

    private Country country;

    private List<Region> regions;


    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
}
