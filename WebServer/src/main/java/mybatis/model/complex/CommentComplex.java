package mybatis.model.complex;

import java.util.Date;

public class CommentComplex {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.comment.id
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.comment.text
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private String text;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.comment.time
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private Date time;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webserver.comment.id_event
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    private Long idEvent;


    private AppUser user;




    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.comment.id
     *
     * @return the value of webserver.comment.id
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.comment.id
     *
     * @param id the value for webserver.comment.id
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.comment.text
     *
     * @return the value of webserver.comment.text
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public String getText() {
        return text;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.comment.text
     *
     * @param text the value for webserver.comment.text
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.comment.time
     *
     * @return the value of webserver.comment.time
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.comment.time
     *
     * @param time the value for webserver.comment.time
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webserver.comment.id_event
     *
     * @return the value of webserver.comment.id_event
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public Long getIdEvent() {
        return idEvent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webserver.comment.id_event
     *
     * @param idEvent the value for webserver.comment.id_event
     *
     * @mbggenerated Wed Mar 11 15:22:30 CET 2015
     */
    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }


    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}