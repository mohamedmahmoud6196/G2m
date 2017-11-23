package business.model;

import javax.inject.Singleton;

import io.realm.RealmObject;

/**
 * Created by m7md mimo on 11/1/2017.
 */
@Singleton
public class FaceBookUser extends RealmObject {
    private String email;
    private String fullName;
    private String imagePath;

    public FaceBookUser(String userID, String userName, String imagePath) {
        this.fullName = userName;
        userId = userID;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    public FaceBookUser() {
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String usereMail) {
        email = usereMail;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String userFullName) {
        fullName = userFullName;
    }
}
