package com.example.secondapiproject.api;

import com.example.secondapiproject.models.BaseResponse;
import com.example.secondapiproject.models.Categories;
import com.example.secondapiproject.models.Students;
import com.example.secondapiproject.models.Users;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitRequests {

    @GET("users")
    Call<BaseResponse<Users>> getUsersData();

    @GET("categories")
    Call<BaseResponse<Categories>> getCategoriesData();

    /*------------------------------------Auth Api End Points------------------------------------------*/

    @FormUrlEncoded
    @POST("students/auth/login")
    Call<BaseResponse<Students>> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("students/auth/register")
    Call<BaseResponse> register(@Field("full_name") String fullName, @Field("email") String email
            , @Field("password") String password, @Field("gender") String gender);

    @GET("students/auth/logout")
    Call<BaseResponse> logout();

    /*-------------------------------------------------------------------------------------------------*/

}
