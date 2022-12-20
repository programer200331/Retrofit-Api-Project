package com.example.secondapiproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.secondapiproject.controllers.ContentApiController;
import com.example.secondapiproject.databinding.ActivityCountriesBinding;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.example.secondapiproject.models.Countries;
import com.example.secondapiproject.recyclers.countries.CountriesRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CountriesActivity extends AppCompatActivity {

    private static final String TAG = "CountriesActivity";
    private ActivityCountriesBinding binding;
    private final List<Countries> countriesArray = new ArrayList<>();
    private CountriesRecyclerAdapter recyclerAdapter;
    private ContentApiController apiController = new ContentApiController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCountriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializedViews();
    }

    private void initializedViews() {
        recyclerAdapter = new CountriesRecyclerAdapter(countriesArray);
        binding.recyclerCountries.setAdapter(recyclerAdapter);
        getCountries();
    }

    private void getCountries() {
        binding.countriesProgress.setVisibility(View.VISIBLE);
        apiController.getCountriesData(new ArrayResponseCallBack<Countries>() {
            @Override
            public void onSuccess(List<Countries> countriesList) {
                Log.e(TAG, "onSuccess: " + countriesList.size());
                binding.countriesProgress.setVisibility(View.GONE);
                countriesArray.clear();
                countriesArray.addAll(countriesList);
                recyclerAdapter.notifyItemRangeInserted(0, countriesArray.size());
            }

            @Override
            public void onFailure(String message) {
                Log.e(TAG, "onFailure: " + message);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}