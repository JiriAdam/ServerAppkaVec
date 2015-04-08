package notification.email;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Irrielde on 25.3.2015.
 */
public class MailMail {

    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String subject, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("venue.booking.dipl@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }

}
