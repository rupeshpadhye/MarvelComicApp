package com.marvel.android.app.injector.components;

import android.content.Context;

import com.marvel.android.app.injector.App;
import com.marvel.android.app.injector.modules.ContextModule;

import dagger.Component;

/**
 * Created by RUPESH on 1/21/2017.
 */


@App
@Component(dependencies = AppComponent.class, modules = ContextModule.class)
public interface ContextComponent {
    Context context();
}
