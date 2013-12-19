package ro.upb.mti.cc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author aburghelea
 * @since 12/18/13.
 */
@Controller
public class Index {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
