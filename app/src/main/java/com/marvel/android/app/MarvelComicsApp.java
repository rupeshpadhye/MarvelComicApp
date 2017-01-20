package com.marvel.android.app;

import android.app.Application;
import android.content.Context;

import com.marvel.android.app.injector.components.AppComponent;
import com.marvel.android.app.injector.components.DaggerAppComponent;
import com.marvel.android.app.injector.modules.AppModule;


/**
 * Created by RUPESH on 7/30/2016.
 */

public class MarvelComicsApp extends Application {
    private AppComponent component;

    protected AppModule getApplicationModule() {
        return new AppModule(this);
    }

    public static AppComponent getAppComponent(Context context) {
        MarvelComicsApp app = (MarvelComicsApp) context.getApplicationContext();
        if (app.component == null) {
            app.component = DaggerAppComponent.builder()
                    .appModule(app.getApplicationModule())
                    .build();
        }
        return app.component;
    }

    public static void clearAppComponent(Context context) {
        MarvelComicsApp app = (MarvelComicsApp) context.getApplicationContext();
        app.component = null;
    }
}