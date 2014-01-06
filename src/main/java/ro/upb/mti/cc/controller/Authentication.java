package ro.upb.mti.cc.controller;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author aburghelea
 * @since 1/6/14.
 */
@Controller
public class Authentication {

    @RequestMapping("login")
    public String index(HttpServletRequest request) {
        UserService userService = UserServiceFactory.getUserService();
        return "redirect:" + userService.createLoginURL(getRootUrl(request));
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        UserService userService = UserServiceFactory.getUserService();
        return "redirect:" + userService.createLogoutURL(getRootUrl(request));
    }

    private String getRootUrl(HttpServletRequest request) {
        return request.getRequestURL().toString().replaceAll(request.getServletPath(), "");
    }
}
