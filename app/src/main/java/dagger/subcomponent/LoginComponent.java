package dagger.subcomponent;

import com.example.m7mdmimo.g2mdx.LoginActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.module.LoginModule;

@Subcomponent(modules = LoginModule.class)
public interface LoginComponent extends AndroidInjector<LoginActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginActivity> {
    }
}
