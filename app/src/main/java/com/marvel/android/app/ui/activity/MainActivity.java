package com.marvel.android.app.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;
import com.marvel.android.app.ui.fragment.ComicsGridFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MarvelComicsApp.getAppComponent(this).inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ComicsGridFragment())
                    .commit();
        }


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
