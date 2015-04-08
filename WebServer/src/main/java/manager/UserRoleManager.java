package manager;

import mybatis.model.enumeration.RoleType;

/**
 * Created by Irrielde on 22.12.2014.
 */
public interface UserRoleManager {


    int addUserRole(Long userID, RoleType roleType);

}
