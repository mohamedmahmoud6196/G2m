package com.example.m7mdmimo.g2mdx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import concrete.IHomeConcrete;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements IHomeConcrete.IHomeView, HasSupportFragmentInjector {
    private static final String STACK_FB = "facebook";
    private static final String STACK_MAP = "map";
    private static final String STACK_CONTACT = "contact";
    private static final String VISIBLE_FRAGMENT = "visible fragment";
    private static final String FB_FRIENDS = "facebook_friends";


    @BindView(R.id.nav_bottom_view)
    BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    @BindView(R.id.btn_calender)
    Button button_toCalender;
    @BindView(R.id.jerb_app_bar)
    Toolbar toolbar;
    @BindView(R.id.btn_to_map)
    Button button;
    @Inject
    HomePresenter homePresenter;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        AndroidInjection.inject(this);
        homePresenter.setup(this);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.nav_bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_fb_friends:
                        FaceBookFriendUserFragment selfRepairFragment = new FaceBookFriendUserFragment();
                        replaceFragment(selfRepairFragment).addToBackStack(STACK_FB).commit();
                        return true;
                    case R.id.menu_contacts:
                        ContactFragment contactFragment = new ContactFragment();
                        replaceFragment(contactFragment).addToBackStack(STACK_CONTACT).commit();
                        return true;
                    case R.id.menu_country:
                        CountryFragment myCustomMap = new CountryFragment();
                        replaceFragment(myCustomMap).addToBackStack(STACK_MAP).commit();
                        return true;
                    default:
                        return true;
                }
            }
        });
        fragmentManager = getSupportFragmentManager();
        FaceBookFriendUserFragment faceBookFriendUserFragment = new FaceBookFriendUserFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, faceBookFriendUserFragment, VISIBLE_FRAGMENT);
        fragmentTransaction.commit();

    }


    private FragmentTransaction replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, VISIBLE_FRAGMENT);
        return fragmentTransaction;
    }

    @OnClick(R.id.btn_logout)
    public void signOut(View view) {
        homePresenter.signOut();

    }

    @OnClick(R.id.btn_calender)
    public void toCalenderActivity(View v) {
        Intent intent = new Intent(HomeActivity.this, CalenderActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_to_map)
    public void ToMapActivity(View v) {
        Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
        startActivity(intent);
    }


    @Override
    public void navigagetToLogin() {
        Intent login = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.removeView();
        if (!homePresenter.isRembembered())
            homePresenter.signOut();

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;

    }
}
