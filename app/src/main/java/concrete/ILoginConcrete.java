package concrete;

import com.facebook.login.LoginResult;

/**
 * Created by m7md mimo on 11/21/2017.
 */

public interface ILoginConcrete {

    interface ILoginPresenter {
        void faceBookLogin(int remembered, LoginResult loginResult);

        void setup(ILoginConcrete.iLoginView loginView);
    }

    interface iLoginView {

        void navigateToHome();
    }
}
