package web.model;

import mybatis.model.basic.EventType;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Irrielde on 12.3.2015.
 */
public class EventFilterFO {

    private Boolean isPeriodic;

    private Boolean casual;

    private Boolean birthday;

    private Boolean cultural;

    private Boolean social;

    private Boolean festiveHoliday;

    private Boolean forWomen;

    private Boolean forMen;

    private Boolean forChildren;

    private Boolean liveMusic;

    public EventFilterFO(){
        initValues();
    }

    public EventFilterFO(List<String> types) {

        Iterator<String> it = types.iterator();

        while(it.hasNext()){
            String type = it.next();
            setAttributeFromString(type);
        }

    }

    private void initValues(){
        isPeriodic=false;
        casual=false;
        birthday=false;
        cultural=false;
        social=false;
        festiveHoliday=false;
        liveMusic = false;
        forWomen=false;
        forMen=false;
        forChildren=false;
    }

    public void setFilterFromList(List<EventType> eventTypes) {

        initValues();

        for(EventType et : eventTypes){

            setAttributeFromString(et.getTypeName());

        }

    }

    private void setAttributeFromString(String typeName) {

        if(typeName.equalsIgnoreCase("isPeriodic") ){
            isPeriodic=true;
        }else if(typeName.equalsIgnoreCase("casual") ){
            casual=true;
        }else if(typeName.equalsIgnoreCase("birthday") ){
            birthday=true;
        }else if(typeName.equalsIgnoreCase("cultural") ){
            cultural=true;
        }else if(typeName.equalsIgnoreCase("social") ){
            social=true;
        }else if(typeName.equalsIgnoreCase("festiveHoliday") ){
            festiveHoliday=true;
        }else if(typeName.equalsIgnoreCase("liveMusic") ){
            liveMusic=true;
        }else if(typeName.equalsIgnoreCase("forWomen") ){
            forWomen=true;
        }else if(typeName.equalsIgnoreCase("forMen") ){
            forMen=true;
        }else if(typeName.equalsIgnoreCase("forChildren") ){
            forChildren=true;
        }else{
            System.err.println("[EventFilterFO]: unknown event type: " + typeName + " id: ");
        }


    }


    public Boolean getIsPeriodic() {
        return isPeriodic;
    }

    public void setIsPeriodic(Boolean isPeriodic) {
        this.isPeriodic = isPeriodic;
    }

    public Boolean getCultural() {
        return cultural;
    }

    public void setCultural(Boolean cultural) {
        this.cultural = cultural;
    }

    public Boolean getSocial() {
        return social;
    }

    public void setSocial(Boolean social) {
        this.social = social;
    }

    public Boolean getFestiveHoliday() {
        return festiveHoliday;
    }

    public void setFestiveHoliday(Boolean festiveHoliday) {
        this.festiveHoliday = festiveHoliday;
    }

    public Boolean getForWomen() {
        return forWomen;
    }

    public void setForWomen(Boolean forWomen) {
        this.forWomen = forWomen;
    }

    public Boolean getForMen() {
        return forMen;
    }

    public void setForMen(Boolean forMen) {
        this.forMen = forMen;
    }

    public Boolean getForChildren() {
        return forChildren;
    }

    public void setForChildren(Boolean forChildren) {
        this.forChildren = forChildren;
    }

    public Boolean getLiveMusic() {
        return liveMusic;
    }

    public void setLiveMusic(Boolean liveMusic) {
        this.liveMusic = liveMusic;
    }

    public Boolean getCasual() {
        return casual;
    }

    public void setCasual(Boolean casual) {
        this.casual = casual;
    }

    public Boolean getBirthday() {
        return birthday;
    }

    public void setBirthday(Boolean birthday) {
        this.birthday = birthday;
    }


}
