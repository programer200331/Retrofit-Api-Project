package com.example.secondapiproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentImages {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("student_id")
    @Expose
    public String studentId;
    @SerializedName("image_url")
    @Expose
    public String imageUrl;

}
