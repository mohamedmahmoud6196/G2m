package concrete;

import java.util.ArrayList;

import business.model.FaceBookFriendUser;
import business.model.FaceBookUser;

/**
 * Created by m7md mimo on 11/21/2017.
 */

public interface IFaceBookFriendConcrete {
    interface IFaceBookFriendPresenter {

        void sendFriendList(ArrayList<FaceBookFriendUser> faceBookFriendUserArrayList);

        void getFriendList();

        void listEmpty();

        void sendUser(FaceBookUser faceBookUser);
    }

    interface IFaceBookFriendView {
        void showFriends(ArrayList<FaceBookFriendUser> faceBookFriendUserArrayList);

        void displayImage(String imagePath);

        void noFriendsFound();
    }
}
