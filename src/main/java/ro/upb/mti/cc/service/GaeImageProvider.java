package ro.upb.mti.cc.service;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.upb.mti.cc.dao.UserDao;
import ro.upb.mti.cc.domain.User;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author aburghelea
 * @since 1/8/14.
 */
@Service
public class GaeImageProvider {


    @Autowired
    UserDao userDao;

    public List<byte[]> convertImage(byte[] original) {
        ImagesService imagesService = ImagesServiceFactory.getImagesService();
        Image old = ImagesServiceFactory.makeImage(original);
        Transform smaller = ImagesServiceFactory.makeResize(150, 150, false);
        Transform slide = ImagesServiceFactory.makeResize(800, 600, false);
        Image smallerImage = imagesService.applyTransform(smaller, old);
        Image slideImage = imagesService.applyTransform(slide, old);
        List<byte[]> images = new ArrayList<>();
        images.add(original);
        images.add(smallerImage.getImageData());
        images.add(slideImage.getImageData());
        return images;
    }

    public void addImage(List<byte[]> images) {
        try {
            UserService userService = UserServiceFactory.getUserService();
            User user = userDao.get(userService.getCurrentUser().getEmail());
            if (user == null) {
                user = new User();
                user.setEmail(userService.getCurrentUser().getEmail());
                user.setImages(new ArrayList<List<byte[]>>());
                Key key = userDao.put(user);
            }
            user = userDao.get(userService.getCurrentUser().getEmail());
            user.getImages().add(images);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<AbstractMap.SimpleEntry<Integer, byte[]>> getThumbnails() {
        List<AbstractMap.SimpleEntry<Integer, byte[]>> thumbnails = new ArrayList<>();
        try {
            String email = UserServiceFactory.getUserService().getCurrentUser().getEmail();
            List<List<byte[]>> allImages = userDao.get(email).getImages();

            for (int i = allImages.size() - 1; i >= 0; i--) {
                thumbnails.add(new HashMap.SimpleEntry<Integer, byte[]>(i, allImages.get(i).get(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return thumbnails;
    }

    public byte[] getOriginal(int i) {
        byte[] image;
        try {
            String email = UserServiceFactory.getUserService().getCurrentUser().getEmail();
            List<List<byte[]>> allImages = userDao.get(email).getImages();

            image = allImages.get(i).get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return image;
    }
}
