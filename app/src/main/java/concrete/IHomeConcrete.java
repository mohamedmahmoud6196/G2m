package concrete;

import business.model.FaceBookUser;

/**
 * Created by m7md mimo on 11/21/2017.
 */

public interface IHomeConcrete {
    interface IHomePresenter  {
        void setup(IHomeView iHomeView);

        void signOut();

        void sendUser(FaceBookUser faceBookUser);

        void removeView();
    }

    interface IHomeView {
        void navigagetToLogin();

    }
}
