package mybatis.model.complex;

import mybatis.model.basic.EventType;

import java.util.Date;
import java.util.List;

/**
 * Created by Irrielde on 12.3.2015.
 */
public class UserEventFilter {



    private List<EventType> eventTypes;


    private String username;

    private String password1;

    private Boolean enabled;

    private String email;

    private Date birthDate;

    private Long id;


    private Integer attendanceRanking;

    private Boolean notifyViaPush;

    private Boolean notifyViaEmail;

    private String androidPushToken;


    public List<EventType> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(List<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAttendanceRanking() {
        return attendanceRanking;
    }

    public void setAttendanceRanking(Integer attendanceRanking) {
        this.attendanceRanking = attendanceRanking;
    }

    public Boolean getNotifyViaPush() {
        return notifyViaPush;
    }

    public void setNotifyViaPush(Boolean notifyViaPush) {
        this.notifyViaPush = notifyViaPush;
    }

    public Boolean getNotifyViaEmail() {
        return notifyViaEmail;
    }

    public void setNotifyViaEmail(Boolean notifyViaEmail) {
        this.notifyViaEmail = notifyViaEmail;
    }

    public String getAndroidPushToken() {
        return androidPushToken;
    }

    public void setAndroidPushToken(String androidPushToken) {
        this.androidPushToken = androidPushToken;
    }
}
