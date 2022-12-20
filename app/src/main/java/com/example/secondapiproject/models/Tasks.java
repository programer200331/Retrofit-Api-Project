package com.example.secondapiproject.models;

import com.example.secondapiproject.controllers.ApiController;
import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*Model : Tasks */
public class Tasks extends BaseModel {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("student_id")
    @Expose
    public String studentId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("is_done")
    @Expose
    public Boolean isDone;


    public static void readTasksData(ArrayResponseCallBack<Tasks> callBack) {

        Call<BaseResponse<Tasks>> call = ApiController.getInstance().getRetrofitRequests().getTasksData();
        call.enqueue(new Callback<BaseResponse<Tasks>>() {
            @Override
            public void onResponse(Call<BaseResponse<Tasks>> call, Response<BaseResponse<Tasks>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    callBack.onSuccess(response.body().data);

                } else {

                    callBack.onFailure("No Data Exists!");

                }

            }

            @Override
            public void onFailure(Call<BaseResponse<Tasks>> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });

    }

    public void createTask(ApiProcessCallBack callBack) {

        final Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().createTask(title);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

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
            public void onFailure(Call<BaseResponse> call, Throwable throwable) {
                callBack.onFailure(throwable.getMessage());
            }
        });

    }

    public void updateTask(ApiProcessCallBack callBack) {

        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().updateTask(id, title);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

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
                callBack.onFailure(t.getMessage());
            }
        });

    }

    public void deleteTask(ApiProcessCallBack callBack) {

        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().deleteTask(id);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

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
                callBack.onFailure(t.getMessage());
            }
        });

    }

}
