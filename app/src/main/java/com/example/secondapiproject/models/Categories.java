package com.example.secondapiproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("visible")
    @Expose
    public String visible;
    @SerializedName("products_count")
    @Expose
    public String productsCount;

}
