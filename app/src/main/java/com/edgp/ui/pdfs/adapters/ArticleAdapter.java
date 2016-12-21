package com.edgp.ui.pdfs.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.edgp.R;
import com.edgp.databinding.AdapterArticleBinding;
import com.edgp.model.Article;

/**
 * Created by daba on 2016-12-21.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {

    private LayoutInflater inflater;

    public ArticleAdapter(Context context) {
        super(context, R.layout.adapter_article);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterArticleBinding binding = (convertView == null)
                ? (AdapterArticleBinding) DataBindingUtil.inflate(inflater, R.layout.adapter_article, parent, false)
                : (AdapterArticleBinding) DataBindingUtil.getBinding(convertView);
        binding.setArticle(getItem(position));
        return binding.getRoot();
    }

    @BindingAdapter("tag")
    public static void tag(View view, Article article) {
        view.setTag(article);
    }
}
