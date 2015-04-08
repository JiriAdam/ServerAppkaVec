package manager;

import mybatis.model.basic.Country;

import java.util.List;

/**
 * Created by Irrielde on 23.3.2015.
 */
public interface CountryManager {

    List<Country> getAllCountries();

}
