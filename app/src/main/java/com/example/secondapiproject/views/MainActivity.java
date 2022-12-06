package com.example.secondapiproject.views;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.secondapiproject.R;
import com.example.secondapiproject.controllers.ContentApiController;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.example.secondapiproject.models.Users;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getUsersData();
    }


    private void getUsersData(){

        ContentApiController apiController = new ContentApiController();
        apiController.getUsersData(new ArrayResponseCallBack<Users>() {
            @Override
            public void onSuccess(List<Users> list) {

                Log.e("MainActivity", "onSuccess: "+list.size());

            }

            @Override
            public void onFailure(String message) {

            }
        });

    }

}