package notification.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Irrielde on 25.3.2015.
 */


public class EmailSender {

    private String senderEmail;
    private String senderPassword;

    private Properties properties;

    private Session session;

    private static EmailSender singleton = new EmailSender();

    public static EmailSender getInstance(){
        return singleton;
    }

    private EmailSender(){

        this.senderEmail = "venue.booking.dipl@gmail.com";
        this.senderPassword = "Qwe123asd";

        properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

    }

    public boolean sendEmail(String recipientEmail, String subject, String messageContent) {

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));


            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageContent);

            Transport.send(message);

        } catch (MessagingException ex) {
            System.err.println("EmailSender ERROR");
            System.err.println(ex.toString());
            return false;
        }

        return true;
    }
}
