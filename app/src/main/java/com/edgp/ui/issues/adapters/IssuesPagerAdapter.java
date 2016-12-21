package com.edgp.ui.issues.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.edgp.model.Issue;
import com.edgp.ui.issues.IssueFragment;

import java.util.ArrayList;

/**
 * Created by daba on 2016-12-21.
 */

public class IssuesPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Issue> issues;

    public IssuesPagerAdapter(FragmentManager fm, ArrayList<Issue> issues) {
        super(fm);
        this.issues = issues;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return issues.get(position).name;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(IssueFragment.ISSUE_KEY, issues.get(position));
        IssueFragment fragment = new IssueFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return issues.size();
    }
}