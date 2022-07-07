package com.saikat.newsappwithmvvmpattern.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.saikat.newsappwithmvvmpattern.R;
import com.saikat.newsappwithmvvmpattern.adapters.TopArticleAdapter;
import com.saikat.newsappwithmvvmpattern.adapters.TopHeadlineAdapter;
import com.saikat.newsappwithmvvmpattern.models.Article;
import com.saikat.newsappwithmvvmpattern.models.TopArticle;
import com.saikat.newsappwithmvvmpattern.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView, recyclerView2;
    List<Article> articleList;
    List<TopArticle> topArticleList;
    MainViewModel mainViewModel;
    TopHeadlineAdapter adapter;
    TopArticleAdapter adapter2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.loadingBar);
        initialize();
        getNews();

        getTopArticleNews();
    }

    private void getTopArticleNews() {
        mainViewModel.getTopArticle().observe(this, mainResponse -> {
            if(mainResponse != null && mainResponse.getArticles() != null && !mainResponse.getArticles().isEmpty()){
                List<TopArticle> article = mainResponse.getArticles();
                topArticleList.addAll(article);
                adapter2.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void getNews() {
        mainViewModel.getHomeScreenData().observe(this, mainResponse -> {
            if(mainResponse != null && mainResponse.getArticles() != null && !mainResponse.getArticles().isEmpty()){
                List<Article> articles = mainResponse.getArticles();
                articleList.addAll(articles);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initialize() {
        recyclerView = findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        recyclerView2 = findViewById(R.id.rec2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView2.setHasFixedSize(true);

        articleList = new ArrayList<>();
        adapter = new TopHeadlineAdapter(articleList, this);
        recyclerView.setAdapter(adapter);

        topArticleList = new ArrayList<>();
        adapter2 = new TopArticleAdapter(topArticleList, this);
        recyclerView2.setAdapter(adapter2);

//        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }
}