package com.marvel.android.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.util.AppConstants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicsDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_layout)
     CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.image_id)
     ImageView mComicImage;


    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;

    private  Comic mComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_detail_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        intializeDependecyInjection();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(AppConstants.EXTRA_COMIC_DETAIL)) {
            mComic = (Comic) intent.getSerializableExtra(AppConstants.EXTRA_COMIC_DETAIL);
        }
        initToolbar();
    }

    private void initToolbar() {
        mCollapsingToolbarLayout.setTitle(mComic.getTitle());
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
       Picasso.with(this).load(mComic.getThumbnail().getImageUrl()).placeholder(R.mipmap.ic_launcher).into(mComicImage);
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
