package com.example.secondapiproject.controllers;

import com.example.secondapiproject.api.RetrofitManager;
import com.example.secondapiproject.api.RetrofitRequests;

public class ApiController {

    private static ApiController instance;
    private final RetrofitRequests retrofitRequests;

    private ApiController() {
        retrofitRequests = RetrofitManager.getRetrofitInstance().create(RetrofitRequests.class);
    }

    public static synchronized ApiController getInstance() {
        if (instance == null) {
            return instance = new ApiController();
        }
        return instance;
    }

    public RetrofitRequests getRetrofitRequests() {
        return retrofitRequests;
    }

}
