package webservice.rest.model;

import mybatis.model.complex.AppUser;

import java.util.Date;

/**
 * Created by Irrielde on 1.4.2015.
 */
public class CommentRO {


    private Long id;

    private String text;

    private String time;

    private UserRO author;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public UserRO getAuthor() {
        return author;
    }

    public void setAuthor(UserRO author) {
        this.author = author;
    }
}
