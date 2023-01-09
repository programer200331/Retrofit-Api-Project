package com.example.secondapiproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondapiproject.R;
import com.example.secondapiproject.controllers.AuthApiController;
import com.example.secondapiproject.controllers.ContentApiController;
import com.example.secondapiproject.databinding.ActivityMainBinding;
import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.example.secondapiproject.models.Users;
import com.example.secondapiproject.recyclers.users.UsersRecyclerAdapter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private ActivityMainBinding mainBinding;
    private UsersRecyclerAdapter adapter;
    private final ContentApiController apiController = new ContentApiController();
    private final AuthApiController authApiController = new AuthApiController();
    private final List<Users> usersArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
    }

    @Override
    protected void onStart() {
        /* super : It is a reserved word that represents the father's class and what follows it is anything
        or any function we want to access in the father and usually it is the Constructor of the father */
        super.onStart();
        initializedRecyclerView();
    }

    public void initializedRecyclerView() {
        adapter = new UsersRecyclerAdapter(usersArray);
        mainBinding.recyclerUsers.setAdapter(adapter);
        getUsers();
    }

    public void getUsers() {
        mainBinding.progressBar.setVisibility(View.VISIBLE);
        apiController.getUsersData(new ArrayResponseCallBack<Users>() {
            @Override
            public void onSuccess(List<Users> usersList) {
                mainBinding.progressBar.setVisibility(View.GONE);
                usersArray.clear();
                usersArray.addAll(usersList);
                adapter.notifyItemRangeInserted(0, usersArray.size());
            }

            @Override
            public void onFailure(String message) {
                mainBinding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void logout() {
        Intent logoutIntent = new Intent(getApplication(), LoginActivity.class);
        authApiController.logout(new ApiProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(MainActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                performIntent(logoutIntent);
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(mainBinding.getRoot(), "" + message, BaseTransientBottomBar.LENGTH_SHORT).show();
//                if (message.equals("Unauthenticated.")) {
//                    performIntent(logoutIntent);
//                }
            }
        });

    }

    private void performIntent(Intent intent) {
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.log_out) {
            logout();
        } else if (item.getItemId() == R.id.to_tasks) {
            performIntent(new Intent(getApplicationContext(), TasksActivity.class));
        } else if (item.getItemId() == R.id.to_countries) {
            performIntent(new Intent(getApplicationContext(), CountriesActivity.class));
        } else {
            performIntent(new Intent(getApplicationContext(), UploadImagesActivity.class));
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}