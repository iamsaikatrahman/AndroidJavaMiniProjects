package com.saikat.newsappwithmvvmpattern.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.saikat.newsappwithmvvmpattern.api.ApiRequestInterface;
import com.saikat.newsappwithmvvmpattern.api.RetrofitClient;
import com.saikat.newsappwithmvvmpattern.response.MainResponse;
import com.saikat.newsappwithmvvmpattern.response.TopMainArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepo {
    private final ApiRequestInterface apiRequestInterface;

    public ArticleRepo() {
        apiRequestInterface = RetrofitClient.getRetrofit().create(ApiRequestInterface.class);
    }

    public LiveData<MainResponse> getTopHeadlineUs() {
        final MutableLiveData<MainResponse> data = new MutableLiveData<>();
        apiRequestInterface.getTopHeadline().enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<TopMainArticleResponse> getToArticle() {
        final MutableLiveData<TopMainArticleResponse> data = new MutableLiveData<>();
        apiRequestInterface.getTopArticle().enqueue(new Callback<TopMainArticleResponse>() {
            @Override
            public void onResponse(Call<TopMainArticleResponse> call, Response<TopMainArticleResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TopMainArticleResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
