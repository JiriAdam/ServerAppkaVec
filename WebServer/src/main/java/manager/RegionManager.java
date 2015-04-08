package manager;

import mybatis.model.complex.Region;

import java.util.List;

/**
 * Created by Irrielde on 18.3.2015.
 */
public interface RegionManager {

    boolean updateRegion(Region region);

    Region getRegionByPrimaryKey(Long regionID);

    Region getRegionByArea1Name(String area1);

    List<Region> getAllRegions();

    List<Region> getCountryRegionsByCountryId(Long id);

    List<Region> getFilteredRegionsByUsername(String username);

    List<Region> getFilteredRegionsByUserId(Long userId);

}
