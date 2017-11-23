package dagger.subcomponent;

import com.example.m7mdmimo.g2mdx.HomeActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.module.FaceBookFriendBuilder;
import dagger.module.HomeModule;
import dagger.module.PhoneContactBuilder;

@Subcomponent(modules = {PhoneContactBuilder.class, FaceBookFriendBuilder.class, HomeModule.class})
public interface HomeComponent extends AndroidInjector<HomeActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeActivity> {
    }
}
