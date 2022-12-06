package com.example.secondapiproject.controllers;

import com.example.secondapiproject.api.RetrofitRequests;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.example.secondapiproject.models.BaseResponse;
import com.example.secondapiproject.models.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentApiController {

    private final RetrofitRequests retrofitRequests;

    public ContentApiController() {
        this.retrofitRequests = ApiController.getInstance().getRetrofitRequests();
    }


    public void getUsersData(ArrayResponseCallBack<Users> callBack) {

        Call<BaseResponse<Users>> call = retrofitRequests.getAllUsers();
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

}
