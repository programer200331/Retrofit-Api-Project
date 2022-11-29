package com.example.secondapiproject.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RetrofitManager {

    private static RetrofitManager instance;
    private final static String BASE_URL = "http://demo-api.mr-dev.tech/api";

    public static synchronized RetrofitManager getInstance() {
        if (instance == null) {
            OkHttpClient client = new OkHttpClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
//                    .addConverterFactory()
                    .build();
        }

        return instance;
    }
}
