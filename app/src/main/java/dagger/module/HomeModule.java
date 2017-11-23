package dagger.module;

import com.example.m7mdmimo.g2mdx.HomeActivity;

import business.manger.SharedManger;
import concrete.IHomeConcrete;
import dagger.Module;
import dagger.Provides;
import presenter.HomePresenter;

/**
 * Created by m7md mimo on 11/21/2017.
 */
@Module
public  class HomeModule {
    @Provides
    IHomeConcrete.IHomeView provideHomeView() {
        return new HomeActivity();
    }

    @Provides
    HomePresenter provideLoginPresenter(IHomeConcrete.IHomeView homeView, SharedManger sharedManger) {
        return new HomePresenter(homeView, sharedManger);
    }
}
