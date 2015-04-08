package web.model.user;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Irrielde on 13.3.2015.
 */
public class AvatarFO {

    private List<MultipartFile> avatar;

    public List<MultipartFile> getAvatar() {
        return avatar;
    }

    public void setAvatar(List<MultipartFile> avatar) {
        this.avatar = avatar;
    }

}
