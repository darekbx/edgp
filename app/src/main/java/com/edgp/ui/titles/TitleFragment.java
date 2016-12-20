package com.edgp.ui.titles;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edgp.Constans;
import com.edgp.R;
import com.edgp.api.UriBuilder;
import com.edgp.databinding.FragmentTitleBinding;
import com.edgp.model.Title;

/**
 * Created by daba on 2016-12-20.
 */
public class TitleFragment extends Fragment {

    public static final String TITLE_KEY = "title_key";

    private FragmentTitleBinding fragmentTitleBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false);
        return fragmentTitleBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Title title = getArguments().getParcelable(TITLE_KEY);
        fragmentTitleBinding.setTitle(title);
    }

    @BindingAdapter({"loadImage"})
    public static void loadImage(ImageView imageView, int coverId) {
        Context context = imageView.getContext();
        Log.v("----------", UriBuilder.getInstance(context).coverUri(coverId, Constans.IMAGE_SIZE).toString());
        Glide
                .with(context)
                .load(UriBuilder.getInstance(context).coverUri(coverId, Constans.IMAGE_SIZE))
                .into(imageView);
    }
}