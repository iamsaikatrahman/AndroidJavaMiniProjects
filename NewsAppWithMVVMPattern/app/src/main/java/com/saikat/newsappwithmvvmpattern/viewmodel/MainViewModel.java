package com.saikat.newsappwithmvvmpattern.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.saikat.newsappwithmvvmpattern.repository.ArticleRepo;
import com.saikat.newsappwithmvvmpattern.response.MainResponse;

public class MainViewModel extends AndroidViewModel {
    private ArticleRepo articleRepo;
    private LiveData<MainResponse> mainResponseLiveData;
    public MainViewModel(@NonNull Application application) {
        super(application);

        articleRepo = new ArticleRepo();
        this.mainResponseLiveData = articleRepo.getTopHeadlineUs();
    }

    public LiveData<MainResponse> getHomeScreenData(){
        return mainResponseLiveData;
    };
}
