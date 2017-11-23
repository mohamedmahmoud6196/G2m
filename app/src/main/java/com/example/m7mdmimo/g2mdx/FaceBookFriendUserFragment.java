package com.example.m7mdmimo.g2mdx;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import adapter.MyFaceBookFriendUserRecyclerViewAdapter;
import business.model.FaceBookFriendUser;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import concrete.IFaceBookFriendConcrete;
import dagger.android.support.AndroidSupportInjection;
import presenter.FaceBookFriendPresenter;


public class FaceBookFriendUserFragment extends Fragment implements IFaceBookFriendConcrete.IFaceBookFriendView {
    @BindView(R.id.image_profile)
    ImageView imageView_profile;
    @BindView(R.id.recycler_facebook_friends)
    RecyclerView recyclerView_faceBook_friends;
    @Inject
    FaceBookFriendPresenter faceBookFriendPresenter;
    ShareDialog shareDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_face_book_friend_user, container, false);
        ButterKnife.bind(this, view);
        faceBookFriendPresenter.setUp(this);
        recyclerView_faceBook_friends.setLayoutManager(new LinearLayoutManager(getActivity()));
        LoginManager.getInstance().logInWithReadPermissions(
                getActivity(),
                Arrays.asList("user_friends"));
        faceBookFriendPresenter.getFriendList();
        shareDialog = new ShareDialog(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.btn_share_image)
    public void shareImage() {
        imageView_profile.setDrawingCacheEnabled(true);
        Bitmap bmap = imageView_profile.getDrawingCache();
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bmap)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareDialog.show(content);
    }

    @Override
    public void displayImage(String imagePath) {
        Glide.with(getActivity()).load(imagePath).into(imageView_profile);

    }

    @Override
    public void showFriends(ArrayList<FaceBookFriendUser> faceBookFriendUserArrayList) {
        recyclerView_faceBook_friends.setAdapter(new MyFaceBookFriendUserRecyclerViewAdapter(getActivity(), faceBookFriendUserArrayList));
    }

    @Override
    public void noFriendsFound() {
        Toast.makeText(getActivity(), "No Friends Found", Toast.LENGTH_LONG).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
