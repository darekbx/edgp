package com.edgp.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edgp.EdgpApplication;
import com.edgp.R;
import com.edgp.events.FragmentReadyEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by daba on 2016-12-20.
 */

public class LoadingFragment extends Fragment {

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.progress_description)
    TextView progressDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_titles_loading, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        EdgpApplication.getBusInstance().send(new FragmentReadyEvent());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void updateProgress(int value, int max, String description) {
        if (isAdded()) {
            progress.setMax(max);
            progress.setProgress(value);
            progressDescription.setText(description);
        }
    }
}
