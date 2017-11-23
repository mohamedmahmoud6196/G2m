package adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.m7mdmimo.g2mdx.R;

import java.util.List;

import business.model.FaceBookFriendUser;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFaceBookFriendUserRecyclerViewAdapter extends RecyclerView.Adapter<MyFaceBookFriendUserRecyclerViewAdapter.ViewHolder> {

    private final List<FaceBookFriendUser> faceBookFriendUserList;
    private Activity activity;

    public MyFaceBookFriendUserRecyclerViewAdapter(Activity activity, List<FaceBookFriendUser> items) {
        faceBookFriendUserList = items;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_facebook_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        FaceBookFriendUser faceBookFriendUser = faceBookFriendUserList.get(position);
        holder.textView_friend_name.setText(faceBookFriendUserList.get(position).getFullName());
        Glide.with(activity).load(faceBookFriendUser.getImagePath())
                .into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return faceBookFriendUserList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_friend_fullName)
        public TextView textView_friend_name;
        @BindView(R.id.image_friend)
        public ImageView circleImageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

    }
}
