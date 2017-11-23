package dagger.subcomponent;

import com.example.m7mdmimo.g2mdx.ContactFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.module.PhoneContactModule;

@Subcomponent(modules = PhoneContactModule.class)
public interface PhoneContactComponent extends AndroidInjector<ContactFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ContactFragment> {

    }
}
