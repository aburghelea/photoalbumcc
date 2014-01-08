package ro.upb.mti.cc.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.List;

/**
 * @author aburghelea
 * @since 1/8/14.
 */
@Entity
public class User {

    @Id
    private String email;

    private List<List<byte[]>> images;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<List<byte[]>> getImages() {
        return images;
    }

    public void setImages(List<List<byte[]>> images) {
        this.images = images;
    }
}
