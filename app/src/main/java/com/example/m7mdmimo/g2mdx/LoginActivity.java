package com.example.m7mdmimo.g2mdx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import concrete.ILoginConcrete;
import dagger.android.AndroidInjection;
import presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginConcrete.iLoginView {

    private static final String TAG = "Facebook";
    @BindView(R.id.radio_remember)
    CheckBox checkBox;
    @Inject
    LoginPresenter loginPresenter;
    @BindView(R.id.login_button)
    LoginButton loginButton;
    private int radio_remembered = 0;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AndroidInjection.inject(this);
        loginPresenter.setup(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("public_profile,user_friends,publish_actions");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // handleFacebookAccessToken(loginResult.getAccessToken());
                LoginManager.getInstance().logInWithReadPermissions(
                        LoginActivity.this,
                        Arrays.asList("user_friends", "public_profile,"));
                if (checkBox.isChecked())
                    radio_remembered = 1;
                loginPresenter.faceBookLogin(radio_remembered, loginResult);
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Cancel", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {
                FacebookException exception = error;
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @OnClick(R.id.login_button)
    public void faceBookLogin(View v) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
