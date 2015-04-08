package notification;

import mybatis.model.complex.AppUser;

/**
 * Created by Malis on 7.3.2015.
 */
public interface Observer {

    void notifyObserver(ObserverNotification notification);

    AppUser getAppUser();

    void setAppUser(AppUser appUser);

}
