package com.example.secondapiproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.secondapiproject.R;
import com.example.secondapiproject.controllers.AuthApiController;
import com.example.secondapiproject.databinding.ActivityLoginBinding;
import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding loginBinding;
    AuthApiController apiController = new AuthApiController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        setOnClickListener();
    }

    private void setOnClickListener() {
        loginBinding.butLogin.setOnClickListener(this);
        loginBinding.tvCreateNow.setOnClickListener(this);
    }

    private void performLogin() {
        if (checkData()) {
            login();
        }
    }

    private boolean checkData() {
        if (!Objects.requireNonNull(loginBinding.loginEmail.getText()).toString().isEmpty()) {
            if (!Objects.requireNonNull(loginBinding.loginPassword.getText()).toString().isEmpty()) {
                return true;
            } else {
                Toast.makeText(this, R.string.toast_enter_password, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.toast_enter_email, Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    private void login() {
        apiController.login(Objects.requireNonNull(loginBinding.loginEmail.getText()).toString(), Objects.requireNonNull(loginBinding.loginPassword.getText()).toString(), new ApiProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                goToMainActivity();
                Toast.makeText(LoginActivity.this, "" + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(loginBinding.getRoot(), "" + message, BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
    }

    private void goToMainActivity() {
        Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentMain);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.but_login) {
            performLogin();
        } else if (view.getId() == R.id.tv_create_now) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
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