package com.marvel.android.app.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.util.AppConstants;
import com.marvel.android.app.util.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RUPESH on 1/21/2017.
 */

public class SearchComicsGridFragment extends ComicsGridFragment {

    @BindView(R.id.searchText)
    EditText searchText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comic_search, container, false);
        ButterKnife.bind(this, rootView);
        initializePresenter();
        intializeViewComponents();
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(AppConstants.EXTRA_COMIC_LIST)) {
            List ComList = (List<Comic>) savedInstanceState.getSerializable(AppConstants.EXTRA_COMIC_LIST);
            mGridAdapter.setGridData(ComList);
            hideLoadingSpinner();
            showComics();
        }
        return rootView;
    }

    @OnClick(R.id.searchButton)
    public  void onSearchButtonClicked(){
        String searchString = searchText.getText().toString();
        if (searchString!=null&& searchString.length() > 0) {
            AppUtils.hideKeyboard(getContext());
            comicsPresenter.findComicsStartingByTitle(searchString);
        }
        else{
          showValidationMessage();
        }
    }

    private void showValidationMessage() {
        Snackbar.make(mRelativeLayout, getString(R.string.invalid_input)
                , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showNoComicsAvailable() {
        String searchString = searchText.getText().toString();
        Snackbar.make(mRelativeLayout,
                getString(R.string.comics_not_available_with_start,searchString)
                , Snackbar.LENGTH_SHORT).show();
    }

}
