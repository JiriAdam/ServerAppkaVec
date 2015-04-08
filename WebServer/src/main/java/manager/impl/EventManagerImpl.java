package manager.impl;

import manager.EventManager;
import mybatis.dao.*;
import mybatis.handler.TwoDateTwoIntOneLong;
import mybatis.handler.TwoDateTwoIntParams;
import mybatis.model.basic.Address;
import mybatis.model.basic.EventType;
import mybatis.model.basic.EventTypeMN;
import mybatis.model.complex.Event;
import mybatis.model.complex.Region;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import web.model.EventFO;
import webservice.rest.model.CreateEventRO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class EventManagerImpl implements EventManager {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private EventTypeMNMapper eventTypeMNMapper;

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private EventTypeMapper eventTypeMapper;

    private Long[] typesID = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};

    private String[] typesName = {"isPeriodic", "casual", "birthday", "cultural", "social",
            "festiveHoliday", "liveMusic", "forWomen", "forMen", "forChildren"
    };




    private String usedDateFormat = "dd/MM/yyyy";

    @Override
    public Long addEvent(EventFO fo) {

        Address newAddress = new Address();

        newAddress.setCity(fo.getCity());
        newAddress.setCountry(fo.getCountry());
        newAddress.setRegion(fo.getRegion());
        newAddress.setStreetName(fo.getStreetName());

        newAddress.setLatitude(fo.getLatitude());
        newAddress.setLongitude(fo.getLongitude());

        String noSpaceCheck = fo.getPostalCode();
        noSpaceCheck = noSpaceCheck.replaceAll(" ", "");

        System.out.println(newAddress.toString());


        try {

            newAddress.setPostalCode(Integer.parseInt(noSpaceCheck));

        } catch (NumberFormatException nfe) {

            newAddress.setPostalCode(-1);
            System.out.println(nfe.toString());
        }

        noSpaceCheck = fo.getRoute();
        noSpaceCheck = noSpaceCheck.replaceAll(" ", "");

        try {
            newAddress.setStreetNumber(Integer.parseInt(noSpaceCheck));

        } catch (NumberFormatException nfe) {
            newAddress.setStreetNumber(-1);
            System.out.println(nfe.toString());
        }

        Region region = regionMapper.selectByArea1(fo.getRegion());

        System.out.println("is region null? " + (region==null));

        newAddress.setIdRegion(region.getId());

        System.out.println("Event assigned to region: " + region.getName() + "("+region.getArea1()+")" + region.getCountry().getName());

        if (addressMapper.insert(newAddress) <= 0) {

            return -2L;

        }


        mybatis.model.basic.Event newEvent = new mybatis.model.basic.Event();

        newEvent.setIsPublic(fo.getIsPublic());
        newEvent.setDescription(fo.getDescription());
        newEvent.setName(fo.getName());
        newEvent.setTime(fo.getTime());
        newEvent.setCreated(new Date());
        newEvent.setCapacity(fo.getCapacity());
        newEvent.setIdOwner(fo.getIdOwner());
        newEvent.setRequireConfirm(fo.getRequireConfirm());

        newEvent.setIdAddress(newAddress.getId());

        if (eventMapper.insert(newEvent) > 0) {

            Long insertedID = newEvent.getId();

            for (int j = 0; j < fo.getEventTypes().length; j++) {
                boolean typeExists = false;

                EventTypeMN eventTypeMN = new EventTypeMN();
                eventTypeMN.setIdEvent(insertedID);

                for (int i = 0; i < typesName.length; i++) {
                    if(typesName[i].equals(fo.getEventTypes()[j])){
                        eventTypeMN.setIdEventType(typesID[i]);
                        typeExists=true;
                        eventTypeMNMapper.insert(eventTypeMN);
                        break;
                    }
                }

                if(!typeExists) System.err.println("Nenasel typ " + fo.getEventTypes()[j]);

            }

            return insertedID;

        } else {
            return -1L;
        }

    }

    @Override
    public Long addEvent(CreateEventRO ro, Long userID) {

        Address newAddress = new Address();

        newAddress.setCity(ro.getCity());
        newAddress.setCountry(ro.getCountry());
        newAddress.setRegion(ro.getRegion());
        newAddress.setStreetName(ro.getStreetName());

        newAddress.setLatitude(ro.getLatitude());
        newAddress.setLongitude(ro.getLongitude());

        String noSpaceCheck = ro.getPostalCode();
        noSpaceCheck = noSpaceCheck.replaceAll(" ", "");

        System.out.println(newAddress.toString());


        try {

            newAddress.setPostalCode(Integer.parseInt(noSpaceCheck));

        } catch (NumberFormatException nfe) {

            newAddress.setPostalCode(-1);
            System.out.println(nfe.toString());
        }

        noSpaceCheck = ro.getStreetNumber();
        noSpaceCheck = noSpaceCheck.replaceAll(" ", "");

        try {
            newAddress.setStreetNumber(Integer.parseInt(noSpaceCheck));

        } catch (NumberFormatException nfe) {
            newAddress.setStreetNumber(-1);
            System.out.println(nfe.toString());
        }

        Region region = regionMapper.selectByArea1(ro.getRegion());

        System.out.println("is region null? " + (region==null));

        newAddress.setIdRegion(region.getId());

        System.out.println("Event assigned to region: " + region.getName() + "("+region.getArea1()+")" + region.getCountry().getName());

        if (addressMapper.insert(newAddress) <= 0) {

            return -2L;

        }


        mybatis.model.basic.Event newEvent = new mybatis.model.basic.Event();

        newEvent.setIsPublic(ro.getIsPublic());
        newEvent.setDescription(ro.getDescription());
        newEvent.setName(ro.getName());
        newEvent.setCreated(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat(usedDateFormat);
        try {
            newEvent.setTime(sdf.parse(ro.getTime()));

        } catch (ParseException e) {
            System.err.println("parse error...");
            e.printStackTrace();
        }


        newEvent.setCapacity(ro.getCapacity());
        newEvent.setIdOwner(userID);
        newEvent.setRequireConfirm(ro.getRequireConfirm());

        newEvent.setIdAddress(newAddress.getId());

        if (eventMapper.insert(newEvent) > 0) {

            Long insertedID = newEvent.getId();

            for (int j = 0; j < ro.getEventTypes().length; j++) {
                boolean typeExists = false;

                EventTypeMN eventTypeMN = new EventTypeMN();
                eventTypeMN.setIdEvent(insertedID);

                for (int i = 0; i < typesName.length; i++) {
                    if(typesName[i].equals(ro.getEventTypes()[j])){
                        eventTypeMN.setIdEventType(typesID[i]);
                        typeExists=true;
                        eventTypeMNMapper.insert(eventTypeMN);
                        break;
                    }
                }

                if(!typeExists) System.err.println("Nenasel typ " + ro.getEventTypes()[j]);

            }

            return insertedID;

        } else {
            return -1L;
        }
    }

    @Override
    public List<Event> getAllPublicEvents() {

        List<Event> allPublic = eventMapper.selectAllPublicAddressList();


        return allPublic;
    }

    @Override
    public List<Event> getAllOwnersEvents(Long idOwner) {
        List<Event> owners = eventMapper.selectAllOwnersEventsList(idOwner);

        return owners;
    }

    @Override
    public Event getPublicEvent(Long idEvent) {

        Event event = eventMapper.selectPublicEventByPrimaryKey(idEvent);

        List<EventType> eventTypes = eventTypeMapper.selectEventTypesForEvent(idEvent);
        event.setEventTypes(eventTypes);

        return event;

    }

    @Override
    public Event getPrivateEvent(Long idEvent) {
        return eventMapper.selectPrivateEventByPrimaryKey(idEvent);
    }

    //TODO get event with page comments
    @Override
    public Event getEventWithCommentPage(Long idEvent, Integer pageIndex) {
      //  eventMapper;
        return null;
    }

    @Override
    public List<Event> getPublicEventsBetween(Date since, Date now, Integer pageIndex, Integer eventsPerPage){


        int offset = eventsPerPage*pageIndex;

        TwoDateTwoIntParams params = new TwoDateTwoIntParams(since, now, offset, eventsPerPage);

        List<Event> events = eventMapper.selectPublicEventPageBetweenDates(params);

        fillEventWithTypes(events, since, now);

        return events;
    }

    private void fillEventWithTypes(List<Event> events, Date since, Date now) {

        TwoDateTwoIntOneLong params2 = new TwoDateTwoIntOneLong(since, now, 0,0,0L);

        List<Event> types = eventMapper.selectPublicEventTypesPageBetweenDates(params2);

        Iterator<Event> ite1 = events.iterator();

        while(ite1.hasNext()){
            Event event = ite1.next();

            Iterator<Event> itt = types.iterator();


            while(itt.hasNext()){
                Event eventWithType = itt.next();

                if(event.getId().longValue() == eventWithType.getId().longValue()){
                    event.setEventTypes(eventWithType.getEventTypes());
                    break;
                }

            }

        }

    }

    @Override
    public List<Event> getPublicEventsBetweenWithUserFilter(Date since, Date now, Integer pageIndex, Long userID, Integer eventsPerPage) {

        int offset = eventsPerPage*pageIndex;


        TwoDateTwoIntOneLong params = new TwoDateTwoIntOneLong(since, now, offset, eventsPerPage, userID);

        List<Event> events = eventMapper.selectPublicEventPageBetweenDatesAndUserFilter(params);

        fillEventWithTypes(events, since, now);

        return events;
    }

    @Override
    public List<Event> getAllPublicEventsPageThatHaventStarted(Integer pageNumber, Integer eventsPerPage) {

        int offset = eventsPerPage * pageNumber;

        Date now = new Date();

        TwoDateTwoIntParams params = new TwoDateTwoIntParams(now, null , offset, eventsPerPage);

        List<Event> events = eventMapper.selectPublicEventPageThatHaventStarted(params);

        fillEventWithTypes(events, null, now);

        return events;
    }

    @Override
    public Long getNumberOfEventsThatHaventStarted() {

        return eventMapper.selectNumberOfEventsThatHaventStarted();
    }


    //TODO evaluate event ...
    public void evaluateEvent(Event event) {

    }




    @Override
    public Event getUsersEvent(Long idEvent, Long idUser) {

        Event event = eventMapper.selectByPrimaryKey(idEvent);

        if (event.getIdOwner() == idUser) {
            return event;
        } else {
            return null;
        }

    }
}
