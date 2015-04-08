package webservice.session;

import mybatis.model.complex.AppUser;

import java.util.Date;

/**
 * Created by Irrielde on 30.3.2015.
 */

/**
 * simple wrapper class
 */
public class SessionInfo {

    public Date expirationTime;

    public String token;

    public Long userID;

    public AppUser user;

}
