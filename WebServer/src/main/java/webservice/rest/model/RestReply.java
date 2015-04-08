package webservice.rest.model;

/**
 * Created by Irrielde on 30.3.2015.
 */
public class RestReply {

    //0 ok
    //1 Invalid token
    //2 token expired ... login again
    //3 db error
    //4 Invalid json body
    //5 Invalid method. Only subscribe or unsubscribe are valid
    //6 Incorrect date format. Please use: dd/mm/yyyy
    //7 This event reached its capacity.
    //8 ok... awaiting event's owner for confirmation
    //9 reservation not found
    //10 Reservation already exists.
    private int status;

    private String message="";

    private Object data;

    public RestReply(){}

    public RestReply(int statusCode, String message){
        this.status = statusCode;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
