package webservice.rest.model;


/**
 * Created by Irrielde on 31.3.2015.
 */
public class RegionRO {


    private Long id;
    private String name;
    private Long idCountry;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }
}
