package dagger.subcomponent;

import com.example.m7mdmimo.g2mdx.FaceBookFriendUserFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.module.FaceBookFriendModule;

@Subcomponent(modules = FaceBookFriendModule.class)
public interface FaceBookFriendComponent extends AndroidInjector<FaceBookFriendUserFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<FaceBookFriendUserFragment> {

    }
}
