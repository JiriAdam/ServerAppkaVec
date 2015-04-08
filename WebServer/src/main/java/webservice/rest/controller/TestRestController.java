package webservice.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webservice.session.UserSessionManager;

/**
 * Created by Irrielde on 1.4.2015.
 */
@RestController
public class TestRestController {



        @Autowired
        private UserSessionManager userSessionManager;


        @RequestMapping("/rest/greeting")
        public String greeting() {

            MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("SHA-1");

            String hash = encoder.encodePassword("heslo", "tady je sul");

            return hash;

        }


}
