package com.marvel.android.app.injector.modules;

import android.content.Context;

import com.marvel.android.app.injector.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RUPESH on 1/21/2017.
 */
@Module
public class ContextModule {
    private final Context mContext;

    public ContextModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @App
    Context provideContextModule() {
        return mContext;
    }
}

