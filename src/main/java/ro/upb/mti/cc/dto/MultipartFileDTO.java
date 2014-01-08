package ro.upb.mti.cc.dto;

import org.gmr.web.multipart.GMultipartFile;

/**
 * @author aburghelea
 * @since 1/8/14.
 */
public class MultipartFileDTO {
    private String filename;
    private GMultipartFile file;

    public String getName() {
        return filename;
    }

    public void setName(String filename) {
        this.filename = filename;
    }

    public void setFile(GMultipartFile file) {
        this.file = file;
    }

    public GMultipartFile getFile() {
        return file;
    }
}
