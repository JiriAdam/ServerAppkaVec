package manager.impl;

import manager.EventFilterManager;
import mybatis.dao.EventFilterMapper;
import mybatis.dao.EventTypeMapper;
import mybatis.model.basic.EventFilter;
import mybatis.model.basic.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.EventFilterFO;

import java.util.List;

/**
 * Created by Irrielde on 11.3.2015.
 */

@Service
public class EventFilterManagerImpl implements EventFilterManager {

    @Autowired
    private EventFilterMapper eventFilterMapper;

    @Autowired
    private EventTypeMapper eventTypeMapper;


    @Override
    public Boolean updateUserFilter(EventFilterFO filter, Long userID) {

        List<EventType> actualFilter = eventFilterMapper.selectEventFilterByUserId(userID);


            Long eventID =1L;
            Long mnID = actualFilterHas("isPeriodic", actualFilter);



            if (filter.getIsPeriodic() == false && mnID > 0) {
                //delete record with mnID
                if(eventFilterMapper.deleteByPrimaryKey(mnID) <= 0){
                    System.err.println("Error deleting record with id " + mnID );
                }
            } else if (filter.getIsPeriodic() == true && mnID <= 0) {
                //create record with userID
                if (insertEventFilterRecord(eventID, userID) <= 0) {
                    System.err.println("Error inserting record with id " + eventID );
                }
            }
        eventID++;
         mnID = actualFilterHas("casual", actualFilter);

        if (filter.getCasual() == false && mnID > 0) {
            //delete record with mnID
            if(eventFilterMapper.deleteByPrimaryKey(mnID) <= 0){
                System.err.println("Error deleting record with id " + mnID );
            }
        } else if (filter.getCasual() == true && mnID <= 0) {
            //create record with userID
            if (insertEventFilterRecord(eventID, userID) <= 0) {
                System.err.println("Error inserting record with id " + eventID );
            }
        }

        eventID++;
        mnID = actualFilterHas("birthday", actualFilter);

        if (filter.getBirthday() == false && mnID > 0) {
            //delete record with mnID
            if(eventFilterMapper.deleteByPrimaryKey(mnID) <= 0){
                System.err.println("Error deleting record with id " + mnID );
            }
        } else if (filter.getBirthday() == true && mnID <= 0) {
            //create record with userID
            if (insertEventFilterRecord(eventID, userID) <= 0) {
                System.err.println("Error inserting record with id " + eventID );
            }
        }

        eventID++;
        mnID = actualFilterHas("cultural", actualFilter);

        if (filter.getCultural() == false && mnID > 0) {
            //delete record with mnID
            if(eventFilterMapper.deleteByPrimaryKey(mnID) <= 0){
                System.err.println("Error deleting record with id " + mnID );
            }
        } else if (filter.getCultural() == true && mnID <= 0) {
            //create record with userID
            if (insertEventFilterRecord(eventID, userID) <= 0) {
                System.err.println("Error inserting record with id " + eventID );
            }
        }
        eventID++;
        mnID = actualFilterHas("social", actualFilter);

        if (filter.getSocial() == false && mnID > 0) {
            //delete record with mnID
            if(eventFilterMapper.deleteByPrimaryKey(mnID) <= 0){
                System.err.println("Error deleting record with id " + mnID );
            }
        } else if (filter.getSocial() == true && mnID <= 0) {
            //create record with userID
            if (insertEventFilterRecord(eventID, userID) <= 0) {
                System.err.println("Error inserting record with id " + eventID );
            }
        }
        eventID++;
        mnID = actualFilterHas("festiveHoliday", actualFilter);

        if (filter.getFestiveHoliday() == false && mnID > 0) {
            //delete record with mnID
            if(eventFilterMapper.deleteByPrimaryKey(mnID) <= 0){
                System.err.println("Error deleting record with id " + mnID );
            }
        } else if (filter.getFestiveHoliday() == true && mnID <= 0) {
            //create record with userID
            if (insertEventFilterRecord(eventID, userID) <= 0) {
                System.err.println("Error inserting record with id " + eventID );
            }
        }
        eventID++;
        mnID = actualFilterHas("liveMusic", actualFilter);

        if (filter.getLiveMusic() == false && mnID > 0) {
            //delete record with mnID
            if(eventFilterMapper.deleteByPrimaryKey(mnID) <= 0){
                System.err.println("Error deleting record with id " + mnID );
            }
        } else if (filter.getLiveMusic() == true && mnID <= 0) {
            //create record with userID
            if (insertEventFilterRecord(eventID, userID) <= 0) {
                System.err.println("Error inserting record with id " + eventID );
            }
        }
        eventID++;
        mnID = actualFilterHas("forWomen", actualFilter);

        if (filter.getForWomen() == false && mnID > 0) {
            //delete record with mnID
            if(eventFilterMapper.deleteByPrimaryKey(mnID) <= 0){
                System.err.println("Error deleting record with id " + mnID );
            }
        } else if (filter.getForWomen() == true && mnID <= 0) {
            //create record with userID
            if (insertEventFilterRecord(eventID, userID) <= 0) {
                System.err.println("Error inserting record with id " + eventID );
            }
        }
        eventID++;
        mnID = actualFilterHas("forMen", actualFilter);

        if (filter.getForMen() == false && mnID > 0) {
            //delete record with mnID
            if(eventFilterMapper.deleteByPrimaryKey(mnID) <= 0){
                System.err.println("Error deleting record with id " + mnID );
            }
        } else if (filter.getForMen() == true && mnID <= 0) {
            //create record with userID
            if (insertEventFilterRecord(eventID, userID) <= 0) {
                System.err.println("Error inserting record with id " + eventID );
            }
        }

        eventID++;
        mnID = actualFilterHas("forChildren", actualFilter);

        if (filter.getForChildren() == false && mnID > 0) {
            //delete record with mnID
            if(eventFilterMapper.deleteByPrimaryKey(mnID) <= 0){
                System.err.println("Error deleting record with id " + mnID );
            }
        } else if (filter.getForChildren() == true && mnID <= 0) {
            //create record with userID
            if (insertEventFilterRecord(eventID, userID) <= 0) {
                System.err.println("Error inserting record with id " + eventID );
            }
        }

        return true;

    }

    @Override
    public List<EventType> getEventFilterByUserId(Long userID) {
        return eventFilterMapper.selectEventFilterByUserId(userID);
    }

    @Override
    public List<EventType> getAllEventTypes() {
        return eventTypeMapper.selectAllTypes();
    }

    private int insertEventFilterRecord(Long idEventType, Long userID) {

        EventFilter ef = new EventFilter();
        ef.setIdUser(userID);
        ef.setIdEventType(idEventType);

        return eventFilterMapper.insert(ef);
    }

    private Long actualFilterHas(String eventName, List<EventType> actualFilter) {


        for (EventType et : actualFilter) {


            if (et.getTypeName().equals(eventName)) {

                return et.getId();
            }

        }
        return -1L;
    }


}
