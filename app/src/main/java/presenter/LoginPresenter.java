package presenter;

import android.os.Bundle;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import business.manger.FaceBookUserManger;
import business.manger.SharedManger;
import concrete.ILoginConcrete;

import static business.GmdxUtil.KEY_REMEMBER;
import static business.GmdxUtil.USER_REMEMBER;

/**
 * Created by m7md mimo on 11/22/2017.
 */

public class LoginPresenter implements ILoginConcrete.ILoginPresenter {
    private ILoginConcrete.iLoginView loginView;
    private SharedManger sharedManger;
    FaceBookUserManger faceBookUserManger;

    @Inject
    public LoginPresenter(ILoginConcrete.iLoginView loginView, SharedManger sharedManger, FaceBookUserManger faceBookUserManger) {
        this.loginView = loginView;
        this.sharedManger = sharedManger;
        this.faceBookUserManger = faceBookUserManger;
    }


    @Override
    public void setup(ILoginConcrete.iLoginView loginView) {
        this.loginView = loginView;
        int remembered = sharedManger.getData(KEY_REMEMBER);
        if (remembered == USER_REMEMBER)
            loginView.navigateToHome();
    }

    @Override
    public void faceBookLogin(final int remembered, final LoginResult loginResult) {


        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        try {
                            if (remembered == USER_REMEMBER)
                                sharedManger.putData(KEY_REMEMBER, USER_REMEMBER);
                            String userID = (String) object.get("id");
                            String userName = (String) object.get("name");
                            String imagePath = "https://graph.facebook.com/" + userID + "/picture?type=large";
                            faceBookUserManger.createFaceBookUser(userID, userName, imagePath);
                            loginView.navigateToHome();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
