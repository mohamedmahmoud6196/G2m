package dagger.module;

import com.example.m7mdmimo.g2mdx.ContactFragment;

import business.manger.ContactManger;
import concrete.IPhoneContactConcrete;
import dagger.Module;
import dagger.Provides;
import presenter.PhoneContactPresenter;

/**
 * Created by m7md mimo on 11/21/2017.
 */
@Module
public class PhoneContactModule {
    @Provides
    IPhoneContactConcrete.IPhoneContactView provideContactView() {
        return new ContactFragment();
    }


    //// TODO: 11/22/2017  handle providing view
    @Provides
    PhoneContactPresenter providePhoneContactManger(ContactManger contactManger) {
        return new PhoneContactPresenter(contactManger);
    }
}
