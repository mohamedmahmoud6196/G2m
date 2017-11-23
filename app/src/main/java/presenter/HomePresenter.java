package presenter;

import com.facebook.login.LoginManager;

import javax.inject.Inject;

import business.manger.SharedManger;
import business.model.FaceBookUser;
import concrete.IHomeConcrete;

import static business.GmdxUtil.KEY_REMEMBER;
import static business.GmdxUtil.USER_REMEMBER;

/**
 * Created by m7md mimo on 11/22/2017.
 */

public class HomePresenter implements IHomeConcrete.IHomePresenter {
    private IHomeConcrete.IHomeView iHomeView;
    private SharedManger sharedManger;

    @Inject
    public HomePresenter(IHomeConcrete.IHomeView iHomeView, SharedManger sharedManger) {
        this.sharedManger = sharedManger;
        this.iHomeView = iHomeView;
    }

    @Override
    public void setup(IHomeConcrete.IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }

    @Override
    public void sendUser(FaceBookUser faceBookUser) {
    }

    @Override
    public void removeView() {
        iHomeView = null;
    }

    public boolean isRembembered() {
        int remembered = sharedManger.getData(KEY_REMEMBER);
        if (remembered == USER_REMEMBER)
            return true;
        else
            return false;
    }

    @Override
    public void signOut() {
        LoginManager.getInstance().logOut();
        sharedManger.removeRememberData();
        if (iHomeView != null)
            iHomeView.navigagetToLogin();
    }
}
