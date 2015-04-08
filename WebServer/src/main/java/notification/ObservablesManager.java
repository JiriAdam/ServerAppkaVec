package notification;

import manager.RegionManager;
import mybatis.model.complex.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Malis on 7.3.2015.
 * todle se spusti po spusteni appky
 */


public interface ObservablesManager {

    int initObservers();

    void notifyObservers(ObserverNotification notification);



}
