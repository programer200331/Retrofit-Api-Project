package com.example.secondapiproject.controllers;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.secondapiproject.interfaces.ConnectionCheckerCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

class ApiBaseController {

    void internetConnectionChecker(ConnectionCheckerCallback callback) {
        ConnectivityManager connectivityManager = (ConnectivityManager) AppController.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean connected = connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        callback.onConnectionStatus(connected);
    }

    String getErrorResponse(byte[] bytes) {
        String errorResponse = new String(bytes, StandardCharsets.UTF_8);
        try {
            JSONObject jsonObject = new JSONObject(errorResponse);
            return jsonObject.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Something went wrong, try again!";
    }

}
