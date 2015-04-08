package manager.impl;

import manager.CountryManager;
import mybatis.dao.CountryMapper;
import mybatis.model.basic.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Irrielde on 23.3.2015.
 */
@Service
public class CountryManagerImpl implements CountryManager {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<Country> getAllCountries() {
        return countryMapper.selectAll();
    }
}
