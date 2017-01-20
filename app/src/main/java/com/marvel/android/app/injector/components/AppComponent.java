/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.marvel.android.app.injector.components;


import com.marvel.android.app.injector.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(dependencies = {}, modules = {AppModule.class})
@Singleton
public interface AppComponent  {

}
