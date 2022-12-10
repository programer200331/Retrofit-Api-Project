package com.example.secondapiproject.controllers;

import com.example.secondapiproject.api.RetrofitRequests;
import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.example.secondapiproject.models.BaseResponse;
import com.example.secondapiproject.models.Students;
import com.example.secondapiproject.shpreferances.AppSharedPrefController;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthApiController extends ApiBaseController {

    private static final String TAG = "AuthApiController";
    private final RetrofitRequests retrofitRequests;

    public AuthApiController() {
        retrofitRequests = ApiController.getInstance().getRetrofitRequests();
    }

    public void login(String email, String password, ApiProcessCallBack callBack) {

        Call<BaseResponse<Students>> call = retrofitRequests.login(email, password);
        call.enqueue(new Callback<BaseResponse<Students>>() {
            @Override
            public void onResponse(Call<BaseResponse<Students>> call, Response<BaseResponse<Students>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AppSharedPrefController.getInstance().save(response.body().object);
                    callBack.onSuccess(response.body().message);
                } else {
                    try {
                        if (response.errorBody() != null) {
                            callBack.onFailure(getErrorResponse(response.errorBody().bytes()));
//                            Log.e(TAG, "onResponse: "+getErrorResponse(response.errorBody().bytes()) );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<BaseResponse<Students>> call, Throwable t) {
                callBack.onFailure("");
            }
        });

    }

    public void register(String fullName, String email, String password, String gender, ApiProcessCallBack callBack) {

        Call<BaseResponse> call = retrofitRequests.register(fullName, email, password, gender);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body().message);
                } else {
                    try {
                        callBack.onFailure(getErrorResponse(response.errorBody().bytes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callBack.onFailure("");
            }
        });
    }

    public void logout(ApiProcessCallBack callBack) {

        Call<BaseResponse> call = retrofitRequests.logout();
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    AppSharedPrefController.getInstance().clear();
                    callBack.onSuccess(response.body().message);
                } else {
                    try {
                        if (response.errorBody() != null) {
                            callBack.onFailure(getErrorResponse(response.errorBody().bytes()));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callBack.onFailure("");
            }
        });

    }

}
