package com.example.m7mdmimo.g2mdx;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.component.DaggerAppComponent;
import io.realm.Realm;

/**
 * Created by m7md mimo on 11/21/2017.
 */

public class GmdxApp extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
@Inject
    Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
        Realm.init(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
