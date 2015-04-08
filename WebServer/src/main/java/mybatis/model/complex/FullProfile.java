package mybatis.model.complex;

/**
 * Created by Irrielde on 31.3.2015.
 */

import mybatis.model.basic.EventType;
import mybatis.model.basic.UserRole;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.List;


import mybatis.model.basic.UserRole;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.List;

public class FullProfile {




    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.app_user.id
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.app_user.username
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    private String username;


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.app_user.email
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.app_user.birth_date
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    private Date birthDate;

    private byte[] avatar;

    private Integer attendanceRanking;

    private Boolean notifyViaPush;

    private Boolean notifyViaEmail;

    private String androidPushToken;

    private List<EventType> eventTypes;

    private List<Region> subscribedRegions;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.app_user.avatar
     *
     * @return the value of webserver.app_user.avatar
     *
     * @mbggenerated Thu Mar 12 17:21:26 CET 2015
     */
    /* public byte[] getAvatar() {
        return avatar;
    }*/

    public String getAvatarBase64(){
        String base64 = DatatypeConverter.printBase64Binary(avatar);
        return base64;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.app_user.avatar
     *
     * @param avatar the value for webserver.app_user.avatar
     *
     * @mbggenerated Thu Mar 12 17:21:26 CET 2015
     */
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.app_user.id
     *
     * @return the value of webserver.app_user.id
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.app_user.id
     *
     * @param id the value for webserver.app_user.id
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.app_user.username
     *
     * @return the value of webserver.app_user.username
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.app_user.username
     *
     * @param username the value for webserver.app_user.username
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }



    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.app_user.email
     *
     * @return the value of webserver.app_user.email
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.app_user.email
     *
     * @param email the value for webserver.app_user.email
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.app_user.birth_date
     *
     * @return the value of webserver.app_user.birth_date
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.app_user.birth_date
     *
     * @param birthDate the value for webserver.app_user.birth_date
     *
     * @mbggenerated Mon Dec 22 14:05:31 CET 2014
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.app_user.attendance_ranking
     *
     * @mbggenerated Fri Mar 27 14:57:19 CET 2015
     */
    public Integer getAttendanceRanking() {
        return attendanceRanking;
    }

    public void setAttendanceRanking(Integer attendanceRanking) {
        this.attendanceRanking = attendanceRanking;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.app_user.notify_via_push
     *
     * @mbggenerated Fri Mar 27 14:57:19 CET 2015
     */
    public Boolean getNotifyViaPush() {
        return notifyViaPush;
    }

    public void setNotifyViaPush(Boolean notifyViaPush) {
        this.notifyViaPush = notifyViaPush;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.app_user.notify_via_email
     *
     * @mbggenerated Fri Mar 27 14:57:19 CET 2015
     */
    public Boolean getNotifyViaEmail() {
        return notifyViaEmail;
    }

    public void setNotifyViaEmail(Boolean notifyViaEmail) {
        this.notifyViaEmail = notifyViaEmail;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.app_user.android_push_token
     *
     * @mbggenerated Fri Mar 27 14:57:19 CET 2015
     */
    public String getAndroidPushToken() {
        return androidPushToken;
    }

    public void setAndroidPushToken(String androidPushToken) {
        this.androidPushToken = androidPushToken;
    }

    public List<Region> getSubscribedRegions() {
        return subscribedRegions;
    }

    public void setSubscribedRegions(List<Region> subscribedRegions) {
        this.subscribedRegions = subscribedRegions;
    }

    public List<EventType> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(List<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }
}