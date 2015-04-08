package manager.impl;

import manager.LocalityFilterManager;
import mybatis.dao.LocalityFilterMapper;
import mybatis.handler.TwoLongParameters;
import mybatis.model.basic.LocalityFilter;
import mybatis.model.complex.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Irrielde on 25.3.2015.
 */
@Service
public class LocalityFilterManagerImpl implements LocalityFilterManager{

    @Autowired
    private LocalityFilterMapper localityFilterMapper;

    @Override
    public List<LocalityFilter> getUserLocalityFilterForCountry(Long userID, Long countryID) {
        return localityFilterMapper.selectUserLocalityFilterByUserAndCountryIDs(new TwoLongParameters(userID, countryID));
    }

    @Override
    public boolean addUserFilterForRegion(LocalityFilter userFilter) {

        int rowsAffected = localityFilterMapper.insert(userFilter);

        if(rowsAffected > 0) return true;

        return false;
    }


    @Override
    public boolean deleteUserFilterForRegion(Long idUser, Long idRegion) {

        int rowsAffected =  localityFilterMapper.deleteByUserAndRegionIDs(new TwoLongParameters(idUser,idRegion));

        if(rowsAffected > 0) return true;

        return false;
    }

    @Override
    public List<LocalityFilter> getUserLocalityFilter(Long userId) {
        return localityFilterMapper.selectUserLocalityFilterByUserID(userId);

    }

}
