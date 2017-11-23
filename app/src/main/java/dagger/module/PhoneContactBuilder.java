package dagger.module;

import android.support.v4.app.Fragment;

import com.example.m7mdmimo.g2mdx.ContactFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import dagger.subcomponent.PhoneContactComponent;

/**
 * Created by m7md mimo on 11/22/2017.
 */
@Module(subcomponents = PhoneContactComponent.class)
public abstract class PhoneContactBuilder {
    @Binds
    @IntoMap
    @FragmentKey(ContactFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindContactFragment(PhoneContactComponent.Builder builder);

}
