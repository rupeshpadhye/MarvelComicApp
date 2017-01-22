package com.marvel.android.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.util.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RUPESH on 1/21/2017.
 */

public class ComicDetailsFragment extends Fragment {


    @BindView(R.id.description)  TextView description;
    private Comic mComic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comic_detail, container, false);
        ButterKnife.bind(this, rootView);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(AppConstants.EXTRA_COMIC_DETAIL)) {
            mComic = (Comic) intent.getSerializableExtra(AppConstants.EXTRA_COMIC_DETAIL);
        } else if (getArguments() != null) {
            Bundle bundle = getArguments();
            mComic = (Comic) bundle.getSerializable(AppConstants.EXTRA_COMIC_DETAIL);
        }
        updateView(mComic);
        return  rootView;
    }

    private void updateView(Comic mComic) {
       // ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mComic.getTitle());
        description.setText(mComic.getDescription());
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
