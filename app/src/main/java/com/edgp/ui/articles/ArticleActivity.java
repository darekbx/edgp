package com.edgp.ui.articles;

import android.os.Bundle;
import android.widget.TextView;

import com.edgp.R;
import com.edgp.model.Article;
import com.edgp.ui.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by daba on 2016-12-21.
 */

public class ArticleActivity extends BaseActivity {

    public static final String ARTICLE_KEY = "article_key";

    @BindView(R.id.text_view)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);

        Article article = getIntent().getParcelableExtra(ARTICLE_KEY);
        textView.setText(article.content);
    }
}
