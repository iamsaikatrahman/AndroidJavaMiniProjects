package com.saikat.newsappwithmvvmpattern.api;

import static com.saikat.newsappwithmvvmpattern.constant.NewsAppConstant.API_KEY;

import com.saikat.newsappwithmvvmpattern.response.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequestInterface {
    @GET("top-headlines?country=us&category=business&apiKey=" + API_KEY)
    Call<MainResponse> getTopHeadline();
}
