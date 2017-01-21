/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.marvel.android.app.injector.components;


import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.injector.modules.AppModule;
import com.marvel.android.app.model.repository.rest.RestDataSource;
import com.marvel.android.app.model.repository.rest.endpoints.Endpoint;
import com.marvel.android.app.model.repository.rest.endpoints.MarvelAuthorizer;
import com.marvel.android.app.ui.activity.ComicsDetailActivity;
import com.marvel.android.app.ui.activity.MainActivity;
import com.marvel.android.app.ui.activity.SplashActivity;
import com.marvel.android.app.ui.fragment.ComicsGridFragment;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Component(dependencies = {}, modules = {AppModule.class})
@Singleton
public interface AppComponent  {

    // exported for child-components
    MarvelComicsApp app();
    RestDataSource RestDataSource();
    Endpoint restEndpoint();
    MarvelAuthorizer marvelAuthorizer();


    @Named("ui_thread")
    Scheduler uiThread();
    @Named("executor_thread") Scheduler executorThread();

    SplashActivity inject(SplashActivity splashActivity);
    MainActivity inject(MainActivity activity);
    ComicsGridFragment  inject(ComicsGridFragment comicsGridFragment);
    ComicsDetailActivity inject(ComicsDetailActivity comicsDetailActivity);
}
