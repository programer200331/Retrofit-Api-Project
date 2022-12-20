package com.example.secondapiproject.api;

import androidx.annotation.NonNull;

import com.example.secondapiproject.shpreferances.AppSharedPrefController;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static Retrofit retrofitInstance;
    private final static String BASE_URL = "http://demo-api.mr-dev.tech/api/";

    public static synchronized Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            OkHttpClient client = getClient();
            return retrofitInstance = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }

    private static synchronized OkHttpClient getClient() {

        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {

                Request.Builder builder = chain.request().newBuilder();
                String token = AppSharedPrefController.getInstance().getToken();
                if (token != null) {
                    builder.addHeader("Authorization", token);
                }
                builder.addHeader("Accept", "application/json");
                builder.addHeader("Content_Type", "application/x-www-form-urlencoded");

                return chain.proceed(builder.build());
            }

        }).build();

    }

}
