package dagger.module;

import android.support.v4.app.Fragment;

import com.example.m7mdmimo.g2mdx.FaceBookFriendUserFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import dagger.subcomponent.FaceBookFriendComponent;

/**
 * Created by m7md mimo on 11/22/2017.
 */
@Module(subcomponents = FaceBookFriendComponent.class)
public  abstract class FaceBookFriendBuilder {
    @Binds
    @IntoMap
    @FragmentKey(FaceBookFriendUserFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindFacebookFriendFragment(FaceBookFriendComponent.Builder builder);

}
