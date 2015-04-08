package webservice.rest.model;

import java.util.Date;

/**
 * Created by Irrielde on 31.3.2015.
 */
public class RegisterUserRO {

    private String username;
    private String password;
    private String email;

    //format: dd/MM/yyyy
    private String birthday;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
