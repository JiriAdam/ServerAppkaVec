package manager;

import mybatis.model.basic.EventType;
import web.model.EventFilterFO;

import java.util.List;

/**
 * Created by Irrielde on 11.3.2015.
 */
public interface EventFilterManager {


    Boolean updateUserFilter(EventFilterFO eventTypes, Long userID);

    List<EventType> getEventFilterByUserId(Long userID);

    List<EventType> getAllEventTypes();
}
