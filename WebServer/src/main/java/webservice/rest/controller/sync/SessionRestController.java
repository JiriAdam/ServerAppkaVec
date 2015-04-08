package webservice.rest.controller.sync;

import manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webservice.rest.model.RegisterUserRO;
import webservice.rest.model.RestReply;
import webservice.session.UserSessionManager;

/**
 * Created by Irrielde on 27.3.2015.
 */

@RestController
public class SessionRestController {


        @Autowired
        private UserSessionManager userSessionManager;


        @Autowired
        private UserManager userManager;



        @RequestMapping(value = "/rest/register", method = RequestMethod.POST)
        public RestReply registerNewUser(@RequestBody RegisterUserRO user){


                Long ok =  userManager.addUser(user);

                if(ok > 0)  return new RestReply(0,"Ok");


                return new RestReply(3,"DB error");

        }

        //http://localhost:38080/authenticate_and_get_token?pass_hash=a0d24d4a54046ad44cc9fcf21d1396b71747431b
        //http://localhost:38080/authenticate_and_get_token?username=malis?pass_hash=a0d24d4a54046ad44cc9fcf21d1396b71747431b?salt=sul

        @RequestMapping("/rest/authenticate_and_get_token")
        public RestReply authenticateAndGenerateToken(@RequestParam(value="username", defaultValue="malis") String username,
                               @RequestParam(value="pass_hash", defaultValue="heslo") String hashedPassword,
                               @RequestParam(value="salt", defaultValue="tady je sul") String saltUsed) {


                String token = userSessionManager.authenticateAndGenerateToken(username, hashedPassword, saltUsed);

                RestReply reply = new RestReply();



                if(token.equals("1")){

                        reply.setStatus(1);
                        reply.setMessage("Username was not found.");

                }else if(token.equals("2")){

                        reply.setStatus(2);
                        reply.setMessage("Incorrect password.");


                }else{
                        //ok

                        reply.setStatus(0);
                        reply.setMessage(token);

                }





                return reply;

        }





}
