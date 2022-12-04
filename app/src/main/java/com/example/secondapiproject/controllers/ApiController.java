package com.example.secondapiproject.controllers;

import com.example.secondapiproject.api.RetrofitManager;
import com.example.secondapiproject.api.RetrofitRequests;

public class ApiController {

    private RetrofitRequests retrofitRequests;
    private static ApiController instance;

    private ApiController() {
        retrofitRequests = RetrofitManager.getRetrofitInstance().create(RetrofitRequests.class);
    }

    public static synchronized ApiController getInstance() {
        if (instance == null) {
            return instance = new ApiController();
        }
        return instance;
    }

}
