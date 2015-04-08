package manager;

import mybatis.model.basic.LocalityFilter;
import mybatis.model.complex.AppUser;

import java.util.List;

/**
 * Created by Irrielde on 25.3.2015.
 */
public interface LocalityFilterManager {

    List<LocalityFilter> getUserLocalityFilterForCountry(Long userID, Long countryID);

    boolean addUserFilterForRegion(LocalityFilter userFilter);

    boolean deleteUserFilterForRegion(Long idUser, Long idRegion);

    List<LocalityFilter> getUserLocalityFilter(Long userId);
}
