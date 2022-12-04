package com.example.secondapiproject.api;

import com.example.secondapiproject.models.BaseResponse;
import com.example.secondapiproject.models.Users;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitRequests {

    @GET("users")
    Call<BaseResponse<Users>> getAllUsers();

}
