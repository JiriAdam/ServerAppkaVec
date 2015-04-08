package notification.impl;

import manager.RegionManager;
import manager.UserManager;
import mybatis.model.complex.AppUser;
import mybatis.model.complex.Region;
import notification.Observable;
import notification.ObservablesManager;
import notification.Observer;
import notification.ObserverNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Irrielde on 23.3.2015.
 */

@Service
public class ObservablesManagerImpl implements ObservablesManager {


    private Map<Long, Observable> allObservables = null;

    @Autowired
    private RegionManager regionManager;

    @Autowired
    private UserManager userManager;


    public ObservablesManagerImpl(){
        allObservables = new TreeMap<>();
    //    initObservers();

    }

    @Override
    public int initObservers(){

        List<Region> allRegions = regionManager.getAllRegions();

        if(allRegions.isEmpty()) return 3;

        for(int i = 0;i < allRegions.size(); i ++){

            Region region = allRegions.get(i);

            Observable regionObservable = new ObservableImpl();

            regionObservable.setRegion(region);

            if(region.getArea1()==null) continue;

            if(region.getArea1().equals("")) continue;

            List<AppUser> usersWithRegion = userManager.getAllUsersForRegion(region.getId());

            for (int j = 0; j < usersWithRegion.size(); j++) {

                Observer observer = new ObserverImpl();
                observer.setAppUser(usersWithRegion.get(j));
                regionObservable.addObserver(observer);

            }

            if(usersWithRegion.size()>0){
                System.out.println("Region: " + region.getName() + " / " + usersWithRegion.size() + " subscribers");
            }

            allObservables.put(region.getId(), regionObservable);

        }

        return allObservables.size();

    }

    @Override
    public void notifyObservers(ObserverNotification notification) {

        //vytahnu region notifikace
        Long regionID = notification.getEvent().getAddress().getIdRegion();

        if(allObservables.isEmpty()){
            System.err.println("Observers were not started...");
            return ;
        }

        //vyberu spravnej observable
        Observable observable = allObservables.get(regionID);

        if(observable == null){
            System.err.println("Region with id: " + regionID + " has no Observable.");
            return ;
        }

        //notifikuju vsechny
        observable.notifyObservers(notification);

    }

}
