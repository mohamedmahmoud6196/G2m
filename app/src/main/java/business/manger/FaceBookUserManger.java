package business.manger;

import android.content.Context;

import javax.inject.Inject;

import business.model.FaceBookUser;
import io.realm.Realm;
import io.realm.RealmQuery;
import presenter.FaceBookFriendPresenter;

/**
 * Created by m7md mimo on 11/22/2017.
 */

public class FaceBookUserManger {
    private FaceBookUser faceBookUser;
    private Context context;
    private Realm realm;
    private FaceBookFriendPresenter presenter;

    public FaceBookUserManger() {
    }

    @Inject
    public FaceBookUserManger(Context context) {
        this.context = context;
    }

    public void createFaceBookUser(String userID, String userName, String imagePath) {
        faceBookUser = new FaceBookUser(userID, userName, imagePath);
        saveUser();
    }


    public void getFaceBookUser() {
        realm = Realm.getDefaultInstance();
        RealmQuery<FaceBookUser> query = realm.where(FaceBookUser.class);
        faceBookUser = query.findFirst();
        presenter.sendUser(faceBookUser);
    }

    private void saveUser() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(faceBookUser);
        realm.commitTransaction();
    }

    public void setPresenter(FaceBookFriendPresenter presenter) {
        this.presenter = presenter;
    }
}
