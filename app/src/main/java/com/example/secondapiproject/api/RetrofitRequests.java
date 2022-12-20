package com.example.secondapiproject.api;

import com.example.secondapiproject.models.BaseResponse;
import com.example.secondapiproject.models.Categories;
import com.example.secondapiproject.models.Countries;
import com.example.secondapiproject.models.StudentImages;
import com.example.secondapiproject.models.Students;
import com.example.secondapiproject.models.Tasks;
import com.example.secondapiproject.models.Users;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitRequests {

    @GET("users")
    Call<BaseResponse<Users>> getUsersData();

    @GET("categories")
    Call<BaseResponse<Categories>> getCategoriesData();

    @GET("countries")
    Call<List<Countries>> getCountriesData();


    /******************************************************
     **************** Auth Student Requests **************
     ******************************************************/

    @FormUrlEncoded
    @POST("students/auth/login")
    Call<BaseResponse<Students>> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("students/auth/register")
    Call<BaseResponse> register(@Field("full_name") String fullName, @Field("email") String email
            , @Field("password") String password, @Field("gender") String gender);

    @GET("students/auth/logout")
    Call<BaseResponse> logout();

    /******************************************************
     **************** Student Images Request **************
     ******************************************************/

    @Multipart
    @POST("student/images")
    Call<BaseResponse> uploadImage(@Part MultipartBody.Part image);

    @GET("student/images")
    Call<BaseResponse<StudentImages>> getStudentImages();

    @FormUrlEncoded
    @DELETE("student/images/{id}")
    Call<BaseResponse> deleteImage(@Path("id") int id);

    /******************************************************
     **************** Tasks Api Request *******************
     ******************************************************/

    @GET("tasks")
    Call<BaseResponse<Tasks>> getTasksData();

    @FormUrlEncoded
    @POST("tasks")
    Call<BaseResponse> createTask(@Field("title") String title);

    @FormUrlEncoded
    @PUT("tasks/{id}")
    Call<BaseResponse> updateTask(@Path("id") int id, @Field("title") String title);

    @DELETE("tasks/{id}")
    Call<BaseResponse> deleteTask(@Path("id") int id);

}
