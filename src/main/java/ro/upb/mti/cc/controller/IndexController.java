package ro.upb.mti.cc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.upb.mti.cc.service.GaeImageProvider;
import ro.upb.mti.cc.dto.MultipartFileDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.AbstractMap;
import java.util.List;


/**
 * @author aburghelea
 * @since 12/18/13.
 */
@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    GaeImageProvider gaeImageProvider;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ModelAttribute("user")
    public User getUser() {
        return userService.getCurrentUser();
    }

    @RequestMapping("/upload")
    public String upload(MultipartFileDTO upload, HttpServletRequest request) {
        if (ServletFileUpload.isMultipartContent(request) && upload.getFile().getSize() > 0) {
            List<byte[]> images = gaeImageProvider.convertImage(upload.getFile().getBytes());
            gaeImageProvider.addImage(images);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/images/small", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<AbstractMap.SimpleEntry<Integer,byte[]>> getThumbnails() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return gaeImageProvider.getThumbnails();
    }


}
