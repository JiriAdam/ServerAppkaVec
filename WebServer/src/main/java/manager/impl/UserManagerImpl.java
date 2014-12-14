package manager.impl;

/**
 * Created by Irrielde on 14.12.2014.
 */

import mybatis.dao.AppUserMapper;
import mybatis.dao.UserRoleMapper;
import mybatis.model.AppUser;
import mybatis.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;

import manager.UserManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import web.model.UserFO;
import web.model.UserRegForm;

import java.util.List;

@Service
public class UserManagerImpl implements UserManager{

    @Autowired
    private AppUserMapper appUserMapper;


    @Override
    public boolean addRoleToUser(Long userID, Object rol) {
        return false;
    }

    @Override
    public UserRegForm getUserById(Long userID) {
        return null;
    }

    @Override
    public void testMethod() {

        System.out.println("start");

        List<UserRole> roles = appUserMapper.selectRolesList(2L);

        for (UserRole role : roles) {

            System.out.println(role.toString());

        }

        System.out.println("end");

    }


/*
    public boolean addAddresssToUser(Long userID, Object address) {

        AppUser user = userMapper.selectByPrimaryKey(userID);


        user.setIdAddress(address2.getId());

        userMapper.updateByPrimaryKey(user);

        return false;
    }


*/
}
