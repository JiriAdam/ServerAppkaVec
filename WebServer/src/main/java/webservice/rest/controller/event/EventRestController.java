package webservice.rest.controller.event;

import manager.CommentManager;
import manager.EventManager;
import mybatis.model.complex.AppUser;
import mybatis.model.complex.CommentComplex;
import mybatis.model.complex.Event;
import notification.ObservablesManager;
import notification.ObserverNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.ToBase64From;
import webservice.rest.model.CommentRO;
import webservice.rest.model.CreateEventRO;
import webservice.rest.model.RestReply;
import webservice.rest.model.UserRO;
import webservice.session.UserSessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Irrielde on 27.3.2015.
 */

@RestController
public class EventRestController {

    @Autowired
    private UserSessionManager userSessionManager;

    @Autowired
    private CommentManager commentManager;

    @Autowired
    private EventManager eventManager;

    @Autowired
    private ObservablesManager observablesManager;

    private String usedDateFormat = "dd/MM/yyyy";


    private String usedDateFormatWithTime = "dd_MM_yyyy_HH_mm_ss";


    private int eventsPerPage = 4;


    /**
     * @param pageIndex Indexing starts from 1. Meaning 1st Page = index 1 / 2nd Page = index 2 / ....
     * @return
     */
    @RequestMapping(value = "/rest/events/get_comments", method = RequestMethod.GET)
    public RestReply setEventTypeSettings(@RequestParam(value = "token", defaultValue = "developer") String token,
                                          @RequestParam(value = "event_id", defaultValue = "15") Long eventID,
                                          @RequestParam(value = "page", defaultValue = "1") Integer pageIndex) {

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        //if page index is incorrect we return page 1
        if (pageIndex <= 0) pageIndex = 1;


        List<CommentComplex> comments = commentManager.getCommentsPageForEventByID(eventID, pageIndex - 1);

        Iterator<CommentComplex> itc = comments.iterator();

        List<CommentRO> commentsForReply = new LinkedList<>();

        while (itc.hasNext()) {
            CommentComplex cc = itc.next();
            CommentRO ro = new CommentRO();
            ro.setId(cc.getId());
            ro.setText(cc.getText());
            SimpleDateFormat sdf = new SimpleDateFormat(usedDateFormat);
            ro.setTime(sdf.format(cc.getTime()));


            AppUser user = cc.getUser();
            //is author anonymous
            if (user == null) {
                ro.setAuthor(null);
                commentsForReply.add(ro);

                continue;
            }

            UserRO userRO = new UserRO();

            userRO.setEmail(user.getEmail());
            userRO.setAttendanceRanking(user.getAttendanceRanking());
            userRO.setBirthDate(sdf.format(user.getBirthDate()));
            userRO.setUsername(user.getUsername());
            userRO.setAvatarBase64(ToBase64From.toBase64(user.getAvatar()));

            ro.setAuthor(userRO);

            commentsForReply.add(ro);

        }

        //    System.out.println("Number of comments: " + commentsForReply.size());

        RestReply reply = new RestReply(0, "page_index:" + pageIndex);
        reply.setData(commentsForReply);


        return reply;
    }


    @RequestMapping(value = "/rest/events/new", method = RequestMethod.POST)
    public RestReply createNewEvent(@RequestParam(value = "token", defaultValue = "developer") String token,
                                    @RequestBody CreateEventRO event) {

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }


        if (event.getEventTypes().length == 0) {
            String[] typesDefault = {"casual"};
            event.setEventTypes(typesDefault);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(usedDateFormat);

        try {
            sdf.parse(event.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return new RestReply(6, "Incorrect date format. Please use: " + usedDateFormat);
        }

        Long newEventID = eventManager.addEvent(event, userID);

        if (newEventID <= 0) {
            return new RestReply(3, "DB error");
        }

        //Notify observers

        ObserverNotification observerNotification = new ObserverNotification();
        Event eventToNotifyAbout = eventManager.getUsersEvent(newEventID, userID);
        observerNotification.setEvent(eventToNotifyAbout);
        observablesManager.notifyObservers(observerNotification);

        return new RestReply(0, "event_id:" + newEventID);
    }




    @RequestMapping(value = "/rest/events/get_all", method = RequestMethod.GET)
    public RestReply getPublicEvents(@RequestParam(value = "token", defaultValue = "developer") String token,
                                     @RequestParam(value = "since", defaultValue = "02_03_2015_17_14_40") String sinceStr,
                                     @RequestParam(value = "page", defaultValue = "1") Integer pageIndex) {

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        Date since = null;

        SimpleDateFormat sdf = new SimpleDateFormat(usedDateFormatWithTime);

        try {
            since = sdf.parse(sinceStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new RestReply(6, "Incorrect date format. Please use: "+ usedDateFormatWithTime);
        }

        Date now = new Date();

        //if page index is incorrect we return page 1
        if (pageIndex <= 0) pageIndex = 1;
        pageIndex = pageIndex -1 ;

        //  System.out.println("Getting page: " + pageIndex);
        List<Event> events = eventManager.getPublicEventsBetween(since, now, pageIndex, eventsPerPage);
        RestReply reply =  new RestReply(0, "page:"+pageIndex);
        reply.setData(events);

        return reply;
    }


    /**
     *
     * @param token
     * @param sinceStr
     * @param pageIndex
     * @return returns data json of list of events based on the user locality filter and event type filter
     */
    @RequestMapping(value = "/rest/events/get_events_filtered", method = RequestMethod.GET)
    public RestReply getPublicEventsUserFiltered(@RequestParam(value = "token", defaultValue = "developer") String token,
                                             @RequestParam(value = "since", defaultValue = "02_03_2015_17_14_40") String sinceStr,
                                             @RequestParam(value = "page", defaultValue = "1") Integer pageIndex) {

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        Date since = null;

        SimpleDateFormat sdf = new SimpleDateFormat(usedDateFormatWithTime);

        try {
            since = sdf.parse(sinceStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new RestReply(6, "Incorrect date format. Please use: "+ usedDateFormatWithTime);
        }

        Date now = new Date();

        //if page index is incorrect we return page 1
        if (pageIndex <= 0) pageIndex = 1;
        pageIndex = pageIndex -1 ;

        //  System.out.println("Getting page: " + pageIndex);
        List<Event> events = eventManager.getPublicEventsBetweenWithUserFilter(since, now, pageIndex, userID, eventsPerPage);
        RestReply reply =  new RestReply(0, "page:"+pageIndex);
        reply.setData(events);

        return reply;
    }

    /**
     * custom types and all regions...
     */
    @RequestMapping(value = "/rest/events/get_events_custom_filtered", method = RequestMethod.GET)
    public RestReply getPublicEventsUserCustomFiltered(@RequestParam(value = "token", defaultValue = "developer") String token,
                                                 @RequestParam(value = "since", defaultValue = "02_03_2015_17_14_40") String sinceStr,
                                                 @RequestParam(value = "page", defaultValue = "1") Integer pageIndex,
                                                 @RequestBody List<String> types) {

        Long userID = userSessionManager.isAuthenticated(token);
        if (userID == -2L) {
            return new RestReply(2, "Token expired... log in again.");
        } else if (userID == -1L) {
            return new RestReply(1, "Invalid token");
        }

        if(types==null){
            return new RestReply(4, "Invalid POST body content. This method requires List<String> of event types");
        }

        if(types.isEmpty()){
            return new RestReply(4, "Invalid POST body content. This method requires Non-empty List<String> of event types");
        }

        Date since = null;

        SimpleDateFormat sdf = new SimpleDateFormat(usedDateFormatWithTime);

        try {
            since = sdf.parse(sinceStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new RestReply(6, "Incorrect date format. Please use: "+ usedDateFormatWithTime);
        }

        Date now = new Date();

        //if page index is incorrect we return page 1
        if (pageIndex <= 0) pageIndex = 1;
        pageIndex = pageIndex -1 ;

        //  System.out.println("Getting page: " + pageIndex);
        //TODO dodelat custom filtr
        List<Event> events = eventManager.getPublicEventsBetweenWithUserFilter(since, now, pageIndex, userID, eventsPerPage);

        RestReply reply =  new RestReply(0, "page:"+pageIndex);
        reply.setData(events);

        return reply;
    }



}
