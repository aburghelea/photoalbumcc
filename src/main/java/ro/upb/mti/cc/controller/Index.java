package ro.upb.mti.cc.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author aburghelea
 * @since 12/18/13.
 */
@Controller
public class Index {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ModelAttribute("user")
    public User getUser() {
        return userService.getCurrentUser();
    }

}
