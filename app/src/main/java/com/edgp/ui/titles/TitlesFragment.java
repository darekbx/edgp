package com.edgp.ui.titles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgp.R;
import com.edgp.model.Title;

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
        return inflater.inflate(R.layout.fragment_titles, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        ArrayList<Title> titles = getArguments().getParcelableArrayList(TitlesActivity.TITLES_PARCEL_KEY);
        pager.setAdapter(new PagerAdapter(getChildFragmentManager(), titles));
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Title> titles;

        public PagerAdapter(FragmentManager fm, ArrayList<Title> titles) {
            super(fm);
            this.titles = titles;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position).name;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle(1);
            bundle.putParcelable(TitleFragment.TITLE_KEY, titles.get(position));
            TitleFragment fragment = new TitleFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return titles.size();
        }
    }
}
