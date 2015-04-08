package web.model.user;

import java.util.Date;

/**
 * Created by Irrielde on 27.3.2015.
 */
public class AccountFO {

    private String email;

    private Boolean notifyViaEmail;

    private Boolean notifyViaPush;

    private String birthDate;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getNotifyViaEmail() {
        return notifyViaEmail;
    }

    public void setNotifyViaEmail(Boolean notifyViaEmail) {
        this.notifyViaEmail = notifyViaEmail;
    }

    public Boolean getNotifyViaPush() {
        return notifyViaPush;
    }

    public void setNotifyViaPush(Boolean notifyViaPush) {
        this.notifyViaPush = notifyViaPush;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
