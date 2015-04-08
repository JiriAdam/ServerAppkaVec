package manager;

import mybatis.model.complex.Event;
import web.model.EventFO;
import webservice.rest.model.CreateEventRO;

import java.util.Date;
import java.util.List;

/**
 * Created by Irrielde on 3.1.2015.
 */
public interface EventManager {

    Long addEvent(EventFO fo);

    Long addEvent(CreateEventRO event, Long userID);

    List<Event> getAllPublicEvents();

    List<Event> getAllOwnersEvents(Long idOwner);

    Event getPublicEvent(Long idEvent);

    Event getPrivateEvent(Long idEvent);

    Event getEventWithCommentPage(Long idEvent, Integer pageIndex);

    Event getUsersEvent(Long idEvent, Long idUser);

    List<Event> getPublicEventsBetween(Date since, Date now, Integer pageIndex, Integer eventsPerPage);

    List<Event> getPublicEventsBetweenWithUserFilter(Date since, Date now, Integer pageIndex, Long userID, Integer eventsPerPage);

    List<Event> getAllPublicEventsPageThatHaventStarted(Integer page_number, Integer eventsPerPage);

    Long getNumberOfEventsThatHaventStarted();
}
