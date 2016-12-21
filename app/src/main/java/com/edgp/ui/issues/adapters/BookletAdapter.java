package com.edgp.ui.issues.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.edgp.R;
import com.edgp.databinding.AdapterBookletBinding;
import com.edgp.model.Booklet;
import com.edgp.ui.issues.Utils;

/**
 * Created by daba on 2016-12-21.
 */

public class BookletAdapter extends ArrayAdapter<Booklet> {

    private LayoutInflater inflater;

    public BookletAdapter(Context context) {
        super(context, R.layout.adapter_booklet);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterBookletBinding binding = (convertView == null)
                ? (AdapterBookletBinding) DataBindingUtil.inflate(inflater, R.layout.adapter_booklet, parent, false)
                : (AdapterBookletBinding) DataBindingUtil.getBinding(convertView);
        binding.setBooklet(getItem(position));
        return binding.getRoot();
    }

    @BindingAdapter("tag")
    public static void tag(View view, Booklet booklet) {
        if (Utils.hasValidBooklet(booklet)) {
            view.setTag(booklet.bookletPdfs.get(0).id);
        }
    }
}