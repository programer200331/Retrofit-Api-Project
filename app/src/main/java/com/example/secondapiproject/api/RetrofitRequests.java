package com.example.secondapiproject.api;

import com.example.secondapiproject.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitRequests {

    @GET("/users")
    Call<List<Users>> getAllUsers();

}
