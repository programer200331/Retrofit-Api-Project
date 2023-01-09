package com.example.secondapiproject.models;

import com.example.secondapiproject.controllers.ApiController;
import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentImages extends BaseControllerModels {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("student_id")
    @Expose
    public String studentId;
    @SerializedName("image_url")
    @Expose
    public String imageUrl;

    public byte[] imageBytesArray;

    public void uploadImage(ApiProcessCallBack callBack) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageBytesArray);
        MultipartBody.Part imageFile = MultipartBody.Part.createFormData("image", "image-file", requestBody);

        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().uploadImage(imageFile);
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

    public void deleteImage(ApiProcessCallBack callBack) {

        Call<BaseResponse> call = ApiController.getInstance().getRetrofitRequests().deleteImage(id);
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

    public static void getImages(ArrayResponseCallBack<StudentImages> callBack) {

        Call<BaseResponse<StudentImages>> call = ApiController.getInstance().getRetrofitRequests().getStudentImages();
        call.enqueue(new Callback<BaseResponse<StudentImages>>() {
            @Override
            public void onResponse(Call<BaseResponse<StudentImages>> call, Response<BaseResponse<StudentImages>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body().data);
                } else {

                    callBack.onFailure("Something went wrong, try again");

                }

            }

            @Override
            public void onFailure(Call<BaseResponse<StudentImages>> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });

    }

}
