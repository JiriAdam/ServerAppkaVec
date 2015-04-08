package manager.impl;

import manager.UserRoleManager;
import mybatis.dao.UserRoleMNMapper;
import mybatis.model.basic.UserRoleMN;
import mybatis.model.enumeration.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Irrielde on 22.12.2014.
 */

@Service
public class UserRoleManagerImpl implements UserRoleManager {


    @Autowired
    private UserRoleMNMapper userRoleMNMapper;


    @Override
    public int addUserRole(Long userID, RoleType roleType) {

        UserRoleMN userRole = new UserRoleMN();

        userRole.setIdUser(userID);
        userRole.setIdRole(RoleType.getRoleIdFromDatabase(roleType));

        return userRoleMNMapper.insert(userRole);
    }
}
