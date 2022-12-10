package com.example.secondapiproject.views;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.secondapiproject.R;
import com.example.secondapiproject.controllers.AuthApiController;
import com.example.secondapiproject.controllers.ContentApiController;
import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.example.secondapiproject.models.Categories;
import com.example.secondapiproject.models.Users;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final ContentApiController apiController = new ContentApiController();
    private final AuthApiController authApiController = new AuthApiController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        register();
        login();
//        logout();
//        getUsersData();
//        getCategoriesData();
    }

    private void getUsersData() {

        apiController.getUsersData(new ArrayResponseCallBack<Users>() {
            @Override
            public void onSuccess(List<Users> list) {

                Log.e("MainActivity", "onSuccess: " + list.size());

            }

            @Override
            public void onFailure(String message) {

            }
        });

    }

    private void getCategoriesData() {

        apiController.getCategoriesData(new ArrayResponseCallBack<Categories>() {
            @Override
            public void onSuccess(List<Categories> list) {
                Log.e(TAG, "onSuccess: " + list.size());
            }

            @Override
            public void onFailure(String message) {

            }
        });

    }

    private void login() {

        authApiController.login("joker@api.com", "joker123", new ApiProcessCallBack() {
            @Override
            public void onSuccess(String message) {

                Log.e(TAG, "onSuccess: "+message );

            }

            @Override
            public void onFailure(String message) {
                Log.e(TAG, "onFailure: " + message);
            }
        });

    }

    private void register() {

        authApiController.register("Joker", "joker@api.com", "joker123", "M"
                , new ApiProcessCallBack() {
                    @Override
                    public void onSuccess(String message) {

                        Log.e(TAG, "onSuccess: " + message);

                    }

                    @Override
                    public void onFailure(String message) {

                        Log.e(TAG, "onFailure: " + message);

                    }
                });
    }

    private void logout() {

        authApiController.logout(new ApiProcessCallBack() {
            @Override
            public void onSuccess(String message) {

                Log.e(TAG, "onSuccess: " + message);

            }

            @Override
            public void onFailure(String message) {
                Log.e(TAG, "onFailure: " + message);
            }
        });

    }

}