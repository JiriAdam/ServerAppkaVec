package notification;

import mybatis.model.complex.Event;

/**
 * Created by Irrielde on 23.3.2015.
 */
public class ObserverNotification {

    private Event event;

    public ObserverNotification(){
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    //naky info o eventu

}
