package dagger.module;

import com.example.m7mdmimo.g2mdx.FaceBookFriendUserFragment;

import business.manger.FaceBookFriendManger;
import business.manger.FaceBookUserManger;
import concrete.IFaceBookFriendConcrete;
import dagger.Module;
import dagger.Provides;
import presenter.FaceBookFriendPresenter;

/**
 * Created by m7md mimo on 11/21/2017.
 */
@Module
public class FaceBookFriendModule {
    @Provides
    IFaceBookFriendConcrete.IFaceBookFriendView provideFaceBookView() {
        return new FaceBookFriendUserFragment();
    }

    @Provides
    FaceBookFriendManger provideFaceBookUserManger() {
        return new FaceBookFriendManger();
    }

    @Provides
    FaceBookFriendPresenter provideLoginPresenter(FaceBookFriendManger faceBookFriendManger, FaceBookUserManger faceBookUserManger) {
        return new FaceBookFriendPresenter(faceBookFriendManger, faceBookUserManger);
    }
}
