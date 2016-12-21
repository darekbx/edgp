package com.edgp.ui.pdfs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.edgp.R;
import com.edgp.model.Article;
import com.edgp.model.Pdf;
import com.edgp.ui.pdfs.adapters.ArticleAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by daba on 2016-12-21.
 */

public class ArticlesFragment extends Fragment {

    @BindView(R.id.list_view)
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            Pdf pdf = getArguments().getParcelable(PdfActivity.PDF_PARCEL_KEY);
            setAdapter(listView, pdf);
        }
    }

    private void setAdapter(final ListView listView, Pdf pdf) {
        final ArticleAdapter adapter = new ArticleAdapter(getActivity());
        filterArticles(pdf)
                .subscribe(new Action1<List<Article>>() {
                    @Override
                    public void call(List<Article> articles) {
                        adapter.addAll(articles);
                        listView.setAdapter(adapter);
                    }
                });
    }

    private Observable<List<Article>> filterArticles(Pdf pdf) {
        return Observable
                .from(pdf.articles)
                .filter(new Func1<Article, Boolean>() {
                    @Override
                    public Boolean call(Article article) {
                        return article.type.contains("text");
                    }
                })
                .toList();
    }
}
