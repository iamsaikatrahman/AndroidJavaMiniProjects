package com.saikat.newsappwithmvvmpattern.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Source  implements Serializable {
    @SerializedName("id")
    @Expose
    public Object id;
    @SerializedName("name")
    @Expose
    public String name;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
