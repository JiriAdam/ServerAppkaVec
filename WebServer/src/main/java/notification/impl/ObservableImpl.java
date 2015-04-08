package notification.impl;

import mybatis.model.complex.AppUser;
import mybatis.model.complex.Region;
import notification.Observable;
import notification.Observer;
import notification.ObserverNotification;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Malis on 7.3.2015.
 *
 */
public class ObservableImpl implements Observable {

    private Region region;

    private Map<Long, Observer> observers;

    public ObservableImpl(){
        observers = new TreeMap<Long, Observer>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.put(observer.getAppUser().getId(),observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer.getAppUser().getId());
    }

    @Override
    public void notifyObservers(ObserverNotification notification) {

        Collection<Observer> c = observers.values();

        //obtain an Iterator for Collection
        Iterator<Observer> ito = c.iterator();

        //iterate through TreeMap values iterator
        while(ito.hasNext()){

            Observer observer = ito.next();
            observer.notifyObserver(notification);

        }



    }




    @Override
    public Region getRegion() {
        return region;
    }

    @Override
    public void setRegion(Region region) {
        this.region = region;
    }
}
