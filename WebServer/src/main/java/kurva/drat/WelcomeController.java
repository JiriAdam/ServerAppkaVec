package kurva.drat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")

//@RequestMapping("/index.html")
public class WelcomeController {


	@RequestMapping(method=RequestMethod.GET)
    protected String goToView(Model model) throws Exception {      
        return "view1";
    }
	
	
	
}

