package web.model;

/**
 * Created by Irrielde on 4.1.2015.
 */
public class InvitationsFO {

    private String usernames;
    private Long eventId;

    public String getUsernames() {
        return usernames;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
