package notification.impl;

import mybatis.model.complex.AppUser;
import notification.Observer;
import notification.ObserverNotification;
import notification.email.EmailSender;
import notification.email.MailMail;
import util.ApplicationContextProvider;

/**
 * Created by Malis on 7.3.2015.
 */
public class ObserverImpl implements Observer {

    private AppUser appUser;

    @Override
    public void notifyObserver(ObserverNotification notification) {

       //Get the mailer instance
  /*
        EmailSender emailSender = EmailSender.getInstance();
        Boolean sent = emailSender.sendEmail(appUser.getEmail(),"Test email", "New event at: " + notification.getEvent().getAddress().getCity());
        System.out.println("Sent: " + sent);
*/

        if(appUser.getNotifyViaEmail()) sendEmail(notification);


        if(appUser.getNotifyViaPush()) sendPushNotification(notification);





    }

    private void sendEmail(ObserverNotification notification) {

        ApplicationContextProvider appContext = new ApplicationContextProvider();

        MailMail mm = (MailMail) appContext.getApplicationContext().getBean("mailMail");

        mm.sendMail(appUser.getEmail(),
                "Test email",
                "New event at: " + notification.getEvent().getAddress().getCity());

    }

    private void sendPushNotification(ObserverNotification notification) {

        //TODO push notification implementation

    }

    @Override
    public AppUser getAppUser() {
        return appUser;
    }

    @Override
    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
