package com.example.secondapiproject.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

class BaseControllerModels {

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
