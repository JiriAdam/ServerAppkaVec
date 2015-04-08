package util;


import org.springframework.security.crypto.codec.Base64;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by Irrielde on 1.4.2015.
 */
public class ToBase64From {

    public static String toBase64(byte[] bytes){


        String base64 =  DatatypeConverter.printBase64Binary(bytes);
        return base64;

    }

    public static byte[] fromBase64(String base64){

       // byte[] v2 = DatatypeConverter.parseBase64Binary(base64);

        byte[] bytes = Base64.decode(base64.getBytes());
        return bytes;

    }

}
