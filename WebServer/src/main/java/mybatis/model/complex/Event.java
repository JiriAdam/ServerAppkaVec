package mybatis.model.complex;

import flexjson.JSON;
import mybatis.model.basic.Address;
import mybatis.model.basic.EventType;

import java.util.Date;
import java.util.List;

/**
 * Created by Irrielde on 11.3.2015.
 */
public class Event {

    private Long id;

    private String name;

    private Boolean isPublic;

    private Date time;

    private Date created;

    private Integer capacity;

    private String description;

    private Long idOwner;

    private Boolean requireConfirm;

    private Address address;

    private AppUser owner;

    private List<CommentComplex> comments;

    private List<EventType> eventTypes;

    public String getShortDescription(){
        String shortDescr = "";

        if(description.length() > 86){
            shortDescr = description.substring(0,84) + "...";
        }else{
            shortDescr = description;
        }



        return shortDescr;
    }

    @Override
    public String toString() {
        return "Event["+id+"] " + name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public Boolean getRequireConfirm() {
        return requireConfirm;
    }

    public void setRequireConfirm(Boolean requireConfirm) {
        this.requireConfirm = requireConfirm;
    }

    @JSON
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CommentComplex> getComments() {
        return comments;
    }

    public void setComments(List<CommentComplex> comments) {
        this.comments = comments;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public List<EventType> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(List<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
