package com.marvel.android.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.ui.fragment.ComicsGridFragment;
import com.marvel.android.app.util.AppConstants;

public class MainActivity extends AppCompatActivity implements ComicsGridFragment.OnGridItemSelected {

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

    @Override
    public void OnItemClicked(Comic comic) {
        Intent intent = new Intent(this, ComicsDetailActivity.class)
                .putExtra(AppConstants.EXTRA_COMIC_DETAIL, comic);
        startActivity(intent);

    }
}
