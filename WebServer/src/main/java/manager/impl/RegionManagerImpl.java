package manager.impl;

import manager.RegionManager;
import mybatis.dao.RegionMapper;
import mybatis.model.complex.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Irrielde on 18.3.2015.
 */

@Service
public class RegionManagerImpl implements RegionManager{

    @Autowired
    private RegionMapper regionMapper;


    @Override
    public boolean updateRegion(Region region) {
        int rowsUpdated = regionMapper.updateByPrimaryKey(region);

        if(rowsUpdated > 0){
            return true;
        }
        return false;
    }

    @Override
    public Region getRegionByPrimaryKey(Long regionID) {
        return regionMapper.selectByPrimaryKey(regionID);
    }

    @Override
    public Region getRegionByArea1Name(String area1) {

        return regionMapper.selectByArea1(area1);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionMapper.selectAllRegions();
    }

    @Override
    public List<Region> getCountryRegionsByCountryId(Long id) {
        return regionMapper.selectCountryRegionsByCountryId(id);
    }

    @Override
    public List<Region> getFilteredRegionsByUsername(String username) {
        return regionMapper.selectFilteredRegionsByUsername(username);
    }

    @Override
    public List<Region> getFilteredRegionsByUserId(Long userId) {
        return regionMapper.selectFilteredRegionsByUserId(userId);
    }
}
