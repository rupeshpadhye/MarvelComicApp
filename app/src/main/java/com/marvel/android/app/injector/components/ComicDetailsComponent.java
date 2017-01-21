package com.marvel.android.app.injector.components;

import com.marvel.android.app.injector.App;
import com.marvel.android.app.injector.modules.ComicDetailsModule;
import com.marvel.android.app.injector.modules.ContextModule;
import com.marvel.android.app.ui.activity.ComicsDetailActivity;
import com.marvel.android.app.ui.fragment.ComicCharacterDetailFragment;
import com.marvel.android.app.ui.fragment.ComicCreatorDetailFragment;

import dagger.Component;

/**
 * Created by RUPESH on 1/21/2017.
 */

@App
@Component(dependencies = AppComponent.class ,modules = {ComicDetailsModule.class, ContextModule.class})
public interface ComicDetailsComponent extends ContextComponent {
    ComicsDetailActivity inject(ComicsDetailActivity comicsDetailActivity);
    ComicCharacterDetailFragment inject(ComicCharacterDetailFragment comicDetailFragment);
    ComicCreatorDetailFragment inject(ComicCreatorDetailFragment comicCreatorDetailFragment);
}

