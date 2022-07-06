package com.saikat.newsappwithmvvmpattern.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saikat.newsappwithmvvmpattern.models.Article;
import com.saikat.newsappwithmvvmpattern.models.TopArticle;

import java.util.List;

public class TopMainArticleResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<TopArticle> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<TopArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<TopArticle> articles) {
        this.articles = articles;
    }
}
