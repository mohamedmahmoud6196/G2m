package business.model;

import io.realm.RealmObject;

/**
 * Created by m7md mimo on 11/1/2017.
 */

public class FaceBookFriendUser extends RealmObject {
    private String name, imagePath,id;

    public String getFullName() {
        return name;
    }

    public void setFullName(String fullName) {
        this.name = fullName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
