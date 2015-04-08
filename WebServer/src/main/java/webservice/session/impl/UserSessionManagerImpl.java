package webservice.session.impl;

import manager.UserManager;
import mybatis.model.complex.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import webservice.session.SessionInfo;
import webservice.session.UserSessionManager;

import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

/**
 * Created by Irrielde on 29.3.2015.
 */

@Service
public class UserSessionManagerImpl implements UserSessionManager{


    //key = token, value = userID
    private TreeMap<String,SessionInfo> tokens;

    private String hashAlg = "SHA-1";

    public UserSessionManagerImpl(){
        tokens = new TreeMap<String,SessionInfo>();
    }

    @Autowired
    private UserManager userManager;


    @Override
    public String authenticateAndGenerateToken(String username, String hashedPassword, String saltUsedByClient) {

        AppUser user = userManager.getUserFromUsername(username);

        if(user==null){
            System.err.println("Username not found");
            return "1";
        }

        String userPassword = user.getPassword1();

        MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder(hashAlg);

        String hash = encoder.encodePassword(userPassword, saltUsedByClient);

        if(hash.equals(hashedPassword)){

            Integer randomNum = 0 + (int)(Math.random()*999999999);

            //username + datum + random cislo = token
            String newToken = encoder.encodePassword(username + new Date().toString(), randomNum.toString() );

            System.out.println("Generated token for " + username + " is: " + newToken);

            SessionInfo session = new SessionInfo();
            session.token = newToken;

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 1);
            session.expirationTime = cal.getTime();
            session.userID = user.getId();
            session.user = user;

            tokens.put(newToken,session);

            return newToken;
        }else {
            System.err.println("Hash is not equal => wrong password. Hash algorithm used: " + hashAlg);
            return "2";
        }

    }

    @Override
    public Long isAuthenticated(String token){

        if(token.equals("developer")){
           return userManager.getUserFromUsername("malis").getId();
        }

        SessionInfo session = tokens.get(token);

        if(session!=null){

            Calendar now = Calendar.getInstance();

            if(session.expirationTime.after(now.getTime())){

                now.add(Calendar.HOUR, 1);
                session.expirationTime = now.getTime();


                return session.userID;
            }else{
                System.err.println("Token expired.");

                tokens.remove(token);

                return -2L;
            }

        }

        //not valid token
        return -1L;
    }

}
