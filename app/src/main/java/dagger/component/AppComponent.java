package dagger.component;

import android.app.Application;

import com.example.m7mdmimo.g2mdx.GmdxApp;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.module.ActivityBuilder;
import dagger.module.AppModule;

/**
 * Created by m7md mimo on 11/21/2017.
 */
@Component(modules = {AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(GmdxApp gmdxApp);
}
