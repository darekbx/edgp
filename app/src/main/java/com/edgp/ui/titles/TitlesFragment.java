package com.edgp.ui.titles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgp.R;
import com.edgp.model.Title;
import com.edgp.ui.titles.adapters.TitlesPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by daba on 2016-12-20.
 */

public class TitlesFragment extends Fragment {

    @BindView(R.id.pager)
    ViewPager pager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        ArrayList<Title> titles = getArguments().getParcelableArrayList(TitlesActivity.TITLES_PARCEL_KEY);
        pager.setAdapter(new TitlesPagerAdapter(getChildFragmentManager(), titles));
    }
}
