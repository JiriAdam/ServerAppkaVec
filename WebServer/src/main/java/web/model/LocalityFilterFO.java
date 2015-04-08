package web.model;

/**
 * Created by Irrielde on 23.3.2015.
 */
public class LocalityFilterFO {

    private String country;

    private Long id;

    private Long[] regions;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long[] getRegions() {
        return regions;
    }

    public void setRegions(Long[] regions) {
        this.regions = regions;
    }
}
