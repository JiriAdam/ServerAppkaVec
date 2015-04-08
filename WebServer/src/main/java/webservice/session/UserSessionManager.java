package webservice.session;

import mybatis.model.complex.AppUser;

/**
 * Created by Irrielde on 29.3.2015.
 */
public interface UserSessionManager {

    String authenticateAndGenerateToken(String username, String hashedPassword, String saltUsedByClient);

    Long isAuthenticated(String token);
}
