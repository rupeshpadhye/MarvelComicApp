package com.marvel.android.app.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicsDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_item_detail_activity);
       intializeDependecyInjection();
       /* if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.comic_detail_fragment, new ComicCharacterDetailFragment())
                    .commit();
        }*/

    }

    private void intializeDependecyInjection() {
        MarvelComicsApp.getAppComponent(this).inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }



}
