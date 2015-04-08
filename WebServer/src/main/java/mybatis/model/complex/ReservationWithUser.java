package mybatis.model.complex;

import java.util.Date;

/**
 * Created by Irrielde on 5.1.2015.
 */
public class ReservationWithUser {

   //reservation
    private Long id;
    private Date time;
    private Long idOwner;
    private Long idEvent;
    private Boolean confirmed;
    private Boolean confirmPending;
    private Boolean invitationPending;

    private Boolean attended;

    //user info
    private Long userId;
    private String username;

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getConfirmPending() {
        return confirmPending;
    }

    public void setConfirmPending(Boolean confirmPending) {
        this.confirmPending = confirmPending;
    }

    public Boolean getInvitationPending() {
        return invitationPending;
    }

    public void setInvitationPending(Boolean invitationPending) {
        this.invitationPending = invitationPending;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }
}
