package com.marvel.android.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.ui.fragment.ComicsGridFragment;
import com.marvel.android.app.ui.fragment.SearchComicsGridFragment;
import com.marvel.android.app.util.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ComicsGridFragment.OnGridItemSelected {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.cordinatelayout)
    CoordinatorLayout mCordinateLayout;
    private boolean isDoubleTapped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MarvelComicsApp.getAppComponent(this).inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ComicsGridFragment())
                    .commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.search:
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new SearchComicsGridFragment())
                                    .commit();
                            break;
                        case R.id.comics:
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new ComicsGridFragment())
                                    .commit();
                            break;
                    }
                    return false;
                });

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
    public void onBackPressed() {
        if (this.isDoubleTapped) {
            super.onBackPressed();
            this.finishAffinity();
            return;
        }
        this.isDoubleTapped = true;
        Snackbar.make(mCordinateLayout, getString(R.string.tap_to_close), Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> isDoubleTapped = false, 2000);
    }

    @Override
    public void OnItemClicked(Comic comic) {
        Intent intent = new Intent(this, ComicsDetailActivity.class)
                .putExtra(AppConstants.EXTRA_COMIC_DETAIL, comic);
        startActivity(intent);

    }
}
