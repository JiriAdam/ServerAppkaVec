package webservice.rest.model;

import java.util.Date;

/**
 * Created by Irrielde on 1.4.2015.
 */
public class UserRO {

    private String username;

    private String email;

    //format: dd/MM/yyyy
    private String birthDate;

    private String avatarBase64;

    private Integer attendanceRanking;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAvatarBase64() {
        return avatarBase64;
    }

    public void setAvatarBase64(String avatarBase64) {
        this.avatarBase64 = avatarBase64;
    }

    public Integer getAttendanceRanking() {
        return attendanceRanking;
    }

    public void setAttendanceRanking(Integer attendanceRanking) {
        this.attendanceRanking = attendanceRanking;
    }
}
