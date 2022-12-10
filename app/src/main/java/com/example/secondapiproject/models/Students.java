package com.example.secondapiproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Students {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("full_name")
    @Expose
    public String fullName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("verification_code")
    @Expose
    public Object verificationCode;
    @SerializedName("fcm_token")
    @Expose
    public Object fcmToken;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;
    @SerializedName("is_active")
    @Expose
    public Boolean isActive;

}

