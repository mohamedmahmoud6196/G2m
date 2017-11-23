package dagger.module;

import com.example.m7mdmimo.g2mdx.LoginActivity;

import business.manger.FaceBookUserManger;
import business.manger.SharedManger;
import concrete.ILoginConcrete;
import dagger.Module;
import dagger.Provides;
import presenter.LoginPresenter;

/**
 * Created by m7md mimo on 11/21/2017.
 */
@Module
public class LoginModule {
    @Provides
    ILoginConcrete.iLoginView provideLoginView() {
        return new LoginActivity();
    }

    @Provides
    LoginPresenter provideLoginPresenter(ILoginConcrete.iLoginView loginView, SharedManger sharedManger, FaceBookUserManger faceBookUserManger) {
        return new LoginPresenter(loginView, sharedManger, faceBookUserManger);
    }
}
