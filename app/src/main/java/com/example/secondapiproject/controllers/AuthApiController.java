package com.example.secondapiproject.controllers;

import android.widget.Toast;

import com.example.secondapiproject.R;
import com.example.secondapiproject.api.RetrofitRequests;
import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.example.secondapiproject.interfaces.ConnectionCheckerCallback;
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
        internetConnectionChecker(new ConnectionCheckerCallback() {
            @Override
            public void onConnectionStatus(boolean status) {
                if (status) {
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
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<BaseResponse<Students>> call, Throwable t) {
                            callBack.onFailure(t.getMessage());
                        }
                    });

                } else {
                    Toast.makeText(AppController.getContext(), R.string.toast_no_internet, Toast.LENGTH_SHORT).show();
                }

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
        internetConnectionChecker(new ConnectionCheckerCallback() {
            @Override
            public void onConnectionStatus(boolean status) {
                if (status) {
                    Call<BaseResponse> call = retrofitRequests.logout();
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.isSuccessful() || response.code() == 401) {
                                AppSharedPrefController.getInstance().clear();
                                String message = response.body() != null ? response.body().message : "Logged out Successfully";
                                callBack.onSuccess(message);
                            } else {
                                callBack.onFailure("Failed, something went wrong!");
                            }

                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            callBack.onFailure(t.getMessage());
                        }
                    });

                } else {
                    Toast.makeText(AppController.getContext(), R.string.toast_no_internet, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
