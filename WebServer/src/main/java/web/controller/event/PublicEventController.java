package web.controller.event;

import manager.CommentManager;
import manager.EventManager;
import manager.ReservationManager;
import manager.UserManager;
import mybatis.model.basic.Reservation;
import mybatis.model.complex.AppUser;
import mybatis.model.complex.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.CommentFO;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Irrielde on 3.1.2015.
 */
@Controller
public class PublicEventController {

    @Autowired
    private EventManager eventManager;

    @Autowired
    private CommentManager commentManager;

    @Autowired
    private ReservationManager reservationManager;

    @Autowired
    private UserManager userManager;

    private int eventsPerPage = 20;

    @RequestMapping(value = "/public/events", method = RequestMethod.GET)
    public String publicEvents(ModelMap model) {

        List<Event> publicEvents = eventManager.getAllPublicEvents();

        model.addAttribute("publicEvents", publicEvents);

        return "event/public_events";

    }

    @RequestMapping(value = "/public/events/page/{page_number}", method = RequestMethod.GET)
    public String publicEventsPage(@PathVariable Integer page_number, ModelMap model) {

        if (page_number <= 0) page_number = 1;

        List<Event> publicEvents = eventManager.getAllPublicEventsPageThatHaventStarted(page_number - 1, eventsPerPage);

        Long totalEvents = eventManager.getNumberOfEventsThatHaventStarted();

        Long totalPages = 0L;

        while (totalEvents > totalPages * eventsPerPage) {
            totalPages = totalPages + 1L;
        }


        model.addAttribute("publicEvents", publicEvents);

        calculatePageNumbersAndAddToModel(totalPages, page_number, model);


        return "event/public_events";

    }

    private void calculatePageNumbersAndAddToModel(Long totalPages, Integer pageNumber, ModelMap model) {


        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNumber);
        Long previousPage = 0L;
        Long nextPage = 0L;
        if (pageNumber == totalPages.intValue()) {
            nextPage = totalPages;
            if (totalPages.intValue() == 1) {
                previousPage = totalPages;
            } else {
                previousPage = totalPages - 1L;
            }

        } else if (pageNumber < totalPages.intValue()) {
            nextPage = pageNumber + 1L;
            previousPage = pageNumber - 1L;
            if (previousPage <= 0L) previousPage = 1L;
        }

        List<Integer> list = new LinkedList<>();

        int countToShow = 5;

        if (totalPages < countToShow) {

            for (int i = 1; i <= totalPages; i++) {
                list.add(i);
            }

        } else {
            //to close to end
            if (totalPages.intValue() - pageNumber < 2) {

                int max = totalPages.intValue();
                int i = max - 4;
                for (; i <= max; i++) {
                    list.add(i);
                }

                //to close to start
            } else if (pageNumber <= 2) {

                int i = 1;
                int max = i + 4;

                for (; i <= max; i++) {
                    list.add(i);
                }
                // somewhere in between, standard
            } else {
                int i = pageNumber - 2;
                int max = i + 4;

                for (; i <= max; i++) {
                    list.add(i);
                }


            }


        }


        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);

        model.addAttribute("listedPages", list);


    }

    //TODO get event se strankou commentu
    @RequestMapping(value = "/public/event/{event_id}", method = RequestMethod.GET)
    public String yourEvents(@PathVariable Long event_id, ModelMap model) {

        // @PathVariable Integer comment_page
        ///{comment_page}

        reloadEventDetails(event_id, model);

        return "event/event_details";

    }


    @RequestMapping(value = "/public/event/add_comment/{event_id}", method = RequestMethod.POST)
    public String yourEvents(@PathVariable Long event_id, @ModelAttribute("newCommentForm") CommentFO comment,
                             ModelMap model, Principal principal) {

        if (principal != null) {
            String name = principal.getName();
            AppUser user = userManager.getUserFromUsername(name);
            comment.setIdUser(user.getId());
        }

        comment.setIdEvent(event_id);
        commentManager.addComment(comment);


        reloadEventDetails(event_id, model);

        return "event/event_details";
    }

    private void reloadEventDetails(Long event_id, ModelMap model) {

        Event event = eventManager.getPublicEvent(event_id);


        List<Reservation> allReservations = reservationManager.getReservationsForEvent(event_id);

        if (allReservations != null) {
            model.addAttribute("numberOfReservations", allReservations.size());

        } else {
            model.addAttribute("numberOfReservations", "0");
        }


        if (event == null) {
            model.addAttribute("error", "Such event does not exist.");
        } else {

            model.addAttribute("event", event);
        }

        CommentFO commentForm = new CommentFO();

        model.addAttribute("newCommentForm", commentForm);


    }


}
