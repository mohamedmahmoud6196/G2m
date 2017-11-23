package business.manger;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import javax.inject.Singleton;

import business.model.FaceBookFriendUser;
import business.model.FaceBookUser;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import presenter.FaceBookFriendPresenter;


/**
 * Created by m7md mimo on 11/1/2017.
 */
@Singleton
public class FaceBookFriendManger {
    private ArrayList<FaceBookFriendUser> faceBookFriendUserArrayList = new ArrayList<>(1);
    private FaceBookFriendPresenter faceBookFriendPresnter;
    private Realm realm;

    public FaceBookFriendManger() {
    }

    public void setPresenter(FaceBookFriendPresenter faceBookFriendPresnter) {
        this.faceBookFriendPresnter = faceBookFriendPresnter;
    }

    public void getFriendList() {
        if (getFaceBookFriendUserArrayList().size() <= 0) {
            realm = Realm.getDefaultInstance();
            RealmQuery<FaceBookUser> query = realm.where(FaceBookUser.class);
            FaceBookUser faceBookUser = query.findFirst();
            String userId = faceBookUser.getUserId();
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    '/' + userId + "/friends",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            try {
                                JSONArray jsonFriendList = response.getJSONObject().getJSONArray("data");
                                for (int i = 0; i < jsonFriendList.length(); i++) {
                                    String jsonFB_Friend = jsonFriendList.getString(i);
                                    FaceBookFriendUser faceBookFriendUser = new Gson().fromJson(jsonFB_Friend, FaceBookFriendUser.class);
                                    String imagePath = "https://graph.facebook.com/" + faceBookFriendUser.getId() + "/picture?type=small";
                                    faceBookFriendUser.setImagePath(imagePath);
                                    faceBookFriendUserArrayList.add(faceBookFriendUser);
                                    faceBookFriendPresnter.sendFriendList(faceBookFriendUserArrayList);
                                    saveFriendList();
                                }
                            } catch (Exception e) {
                                faceBookFriendPresnter.listEmpty();
                            }
                        }
                    }
            ).executeAsync();

        } else {
            faceBookFriendPresnter.sendFriendList(faceBookFriendUserArrayList);
        }
    }

    private void saveFriendList() {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(faceBookFriendUserArrayList);
        realm.commitTransaction();
    }

    private ArrayList<FaceBookFriendUser> getFaceBookFriendUserArrayList() {
        realm = Realm.getDefaultInstance();
        RealmQuery<FaceBookFriendUser> query = realm.where(FaceBookFriendUser.class);
        RealmResults<FaceBookFriendUser> result1 = query.findAll();
        for (int i = 0; i < result1.size() - 1; i++) {
            faceBookFriendUserArrayList.add(result1.get(i));
        }
        return faceBookFriendUserArrayList;

    }
}
