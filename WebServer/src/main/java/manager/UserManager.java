package manager;

/**
 * Created by Irrielde on 14.12.2014.
 */

import mybatis.model.AppUser;
import org.springframework.stereotype.Service;
import web.model.UserRegForm;

import java.util.List;


public interface UserManager {

    boolean addRoleToUser(Long userID, Object role);

    UserRegForm getUserById(Long userID);


    void testMethod();
}
