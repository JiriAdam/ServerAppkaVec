package notification.gcm;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Irrielde on 2.4.2015.
 */

public class PushSender {

    private static String googleApiKey = "AIzaSyDz3QGCq7BTAmkJNKKEKmOrfw5NR1O4USo";

    public void test(){

       List<String> regIds = new LinkedList<>();

        int numOfRetries = 3;

        Sender sender = new Sender(googleApiKey);


        Message message = new Message.Builder()
                .addData("message", "this is the message")
                .addData("other-parameter", "some value")
                .build();

        MulticastResult result;
        try {

             result = sender.send(message,regIds,numOfRetries);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
