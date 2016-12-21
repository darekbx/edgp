package com.edgp.ui.titles.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.edgp.model.Title;
import com.edgp.ui.titles.TitleFragment;

import java.util.ArrayList;

/**
 * Created by daba on 2016-12-21.
 */

public class TitlesPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Title> titles;

    public TitlesPagerAdapter(FragmentManager fm, ArrayList<Title> titles) {
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