package webservice.rest.model;

import java.io.Serializable;

/**
 * Created by Irrielde on 30.3.2015.
 */
public class UserInfo implements Serializable {

    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
