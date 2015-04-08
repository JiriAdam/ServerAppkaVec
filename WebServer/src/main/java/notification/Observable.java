package notification;

import mybatis.model.complex.Region;

/**
 * Created by Malis on 7.3.2015.
 */
public interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(ObserverNotification notification);

    Region getRegion();

    void setRegion(Region region);
}
