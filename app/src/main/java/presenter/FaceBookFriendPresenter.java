package presenter;

import java.util.ArrayList;

import javax.inject.Inject;

import business.manger.FaceBookFriendManger;
import business.manger.FaceBookUserManger;
import business.model.FaceBookFriendUser;
import business.model.FaceBookUser;
import concrete.IFaceBookFriendConcrete;

/**
 * Created by m7md mimo on 11/22/2017.
 */

public class FaceBookFriendPresenter implements IFaceBookFriendConcrete.IFaceBookFriendPresenter {
    private final FaceBookUserManger faceBookUserManger;
    @Inject
    FaceBookFriendManger faceBookFriendManger;
    IFaceBookFriendConcrete.IFaceBookFriendView iFaceBookFriendView;

    @Override
    public void sendUser(FaceBookUser faceBookUser) {
        iFaceBookFriendView.displayImage(faceBookUser.getImagePath());
    }

    public FaceBookFriendPresenter(FaceBookFriendManger faceBookFriendManger, FaceBookUserManger faceBookUserManger) {
        this.faceBookFriendManger = faceBookFriendManger;
        faceBookFriendManger.setPresenter(this);
        this.faceBookUserManger = faceBookUserManger;
        faceBookUserManger.setPresenter(this);
    }

    public void setUp(IFaceBookFriendConcrete.IFaceBookFriendView faceBookFriendView) {
        this.iFaceBookFriendView = faceBookFriendView;
        faceBookUserManger.getFaceBookUser();

    }

    @Override
    public void sendFriendList(ArrayList<FaceBookFriendUser> faceBookFriendUserArrayList) {
        iFaceBookFriendView.showFriends(faceBookFriendUserArrayList);
    }

    @Override
    public void getFriendList() {
        faceBookFriendManger.getFriendList();
    }

    @Override
    public void listEmpty() {
        iFaceBookFriendView.noFriendsFound();
    }
}
