package com.example.secondapiproject.controllers;

import com.example.secondapiproject.api.RetrofitRequests;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.example.secondapiproject.models.BaseResponse;
import com.example.secondapiproject.models.Categories;
import com.example.secondapiproject.models.Countries;
import com.example.secondapiproject.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentApiController {

    private final RetrofitRequests retrofitRequests;

    public ContentApiController() {
        this.retrofitRequests = ApiController.getInstance().getRetrofitRequests();
    }

    public void getUsersData(ArrayResponseCallBack<Users> callBack) {

        Call<BaseResponse<Users>> call = retrofitRequests.getUsersData();
        call.enqueue(new Callback<BaseResponse<Users>>() {
            @Override
            public void onResponse(Call<BaseResponse<Users>> call, Response<BaseResponse<Users>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    callBack.onSuccess(response.body().data);

                } else {
                    callBack.onFailure("No Data");
                }

            }

            @Override
            public void onFailure(Call<BaseResponse<Users>> call, Throwable t) {

                //

            }
        });

    }

    public void getCategoriesData(ArrayResponseCallBack<Categories> callBack) {

        Call<BaseResponse<Categories>> call = retrofitRequests.getCategoriesData();
        call.enqueue(new Callback<BaseResponse<Categories>>() {
            @Override
            public void onResponse(Call<BaseResponse<Categories>> call, Response<BaseResponse<Categories>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body().data);
                } else {
                    callBack.onFailure("No Data");
                }

            }

            @Override
            public void onFailure(Call<BaseResponse<Categories>> call, Throwable t) {

                //

            }
        });

    }

    public void getCountriesData(ArrayResponseCallBack<Countries> callBack) {

        Call<List<Countries>> call = retrofitRequests.getCountriesData();
        call.enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    callBack.onSuccess(response.body());

                } else {

                    callBack.onFailure("No Data Exists");

                }

            }

            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });

    }

}
