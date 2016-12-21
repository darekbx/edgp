package com.edgp.ui.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.edgp.R;
import com.edgp.model.Issue;
import com.edgp.ui.issues.adapters.BookletAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by daba on 2016-12-21.
 */

public class IssueFragment extends Fragment {

    public static final String ISSUE_KEY = "issue_key";

    @BindView(R.id.list_view)
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_issue, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            Issue issue = getArguments().getParcelable(IssueFragment.ISSUE_KEY);
            setAdapter(listView, issue);
        }
    }

    private void setAdapter(ListView listView, Issue issue) {
        BookletAdapter adapter = new BookletAdapter(getActivity());
        adapter.addAll(issue.booklets);
        listView.setAdapter(adapter);
    }
}