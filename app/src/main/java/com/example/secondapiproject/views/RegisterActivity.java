package com.example.secondapiproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.secondapiproject.R;
import com.example.secondapiproject.controllers.AuthApiController;
import com.example.secondapiproject.databinding.ActivityRegisterBinding;
import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding registerBinding;
    private final AuthApiController apiController = new AuthApiController();
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializedView();
    }

    private void initializedView() {
        controllerGenderSelection();
        setOnClickListener();
    }

    private void setOnClickListener() {
        registerBinding.butRegister.setOnClickListener(this);
    }

    private void performRegister() {
        if (checkData()) {
            register();
        }
    }

    private boolean checkData() {
        if (!registerBinding.etRUserName.getText().toString().isEmpty() &&
                !registerBinding.etRUserEmail.getText().toString().isEmpty() &&
                !registerBinding.etRUserPassword.getText().toString().isEmpty() &&
                gender != null) {
            return true;
        }

        Snackbar.make(registerBinding.getRoot(), R.string.toast_enter_data, Snackbar.LENGTH_SHORT).show();
        return false;

    }


    private void register() {
        apiController.register(registerBinding.etRUserName.getText().toString()
                , registerBinding.etRUserEmail.getText().toString()
                , registerBinding.etRUserPassword.getText().toString(), gender
                , new ApiProcessCallBack() {
                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(RegisterActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                });

    }

    private void goToMainActivity() {
        Intent intentMain = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intentMain);
    }

    private void controllerGenderSelection() {

        registerBinding.radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                gender = i == R.id.radio_male ? "M" : "F";
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.but_register) {
            performRegister();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}