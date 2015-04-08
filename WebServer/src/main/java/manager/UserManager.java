package manager;

/**
 * Created by Irrielde on 14.12.2014.
 */

import mybatis.model.complex.AppUser;
import mybatis.model.basic.UserRole;
import mybatis.model.complex.FullProfile;
import mybatis.model.complex.UserEventFilter;
import mybatis.model.enumeration.RoleType;
import web.model.UserFO;
import webservice.rest.model.RegisterUserRO;

import java.util.List;


public interface UserManager {


    Long addUser(UserFO fo);

    Long addUser(RegisterUserRO user);

    List<UserRole> getUserRolesTest(Long userID);

    AppUser getUserFromUsername(String name);

    FullProfile getFullUserInfoFromID(Long id);

    UserEventFilter getUserEventFilterFromUsername(String name);

    List<AppUser> getUsersWithRole(RoleType roleType);

    boolean updateUserWithBlobs(AppUser appUser);

    AppUser getProfileFromID(Long user_id);

    List<AppUser> getAllUsersForRegion(Long idRegion);

    boolean updateUser(AppUser user);

    boolean updateUserSelective(AppUser newUserValues);
}
