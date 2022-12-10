package com.example.secondapiproject.shpreferances;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.secondapiproject.controllers.AppController;
import com.example.secondapiproject.models.Students;

enum PrefKeys {
    loggedIn, id, fullName, email, token
}

public class AppSharedPrefController {

    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static AppSharedPrefController instance;

    public static synchronized AppSharedPrefController getInstance() {
        if (instance == null) {
            instance = new AppSharedPrefController();
        }
        return instance;
    }

    private AppSharedPrefController() {
        sharedPreferences = AppController.getContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
    }

    public void save(Students student) {
        editor = sharedPreferences.edit();
        editor.putBoolean(PrefKeys.loggedIn.name(), true);
        editor.putInt(PrefKeys.id.name(), student.id);
        editor.putString(PrefKeys.fullName.name(), student.fullName);
        editor.putString(PrefKeys.email.name(), student.email);
        editor.putString(PrefKeys.token.name(), "Bearer " + student.token);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(PrefKeys.loggedIn.name(), false);
    }

    public String getToken() {
        return sharedPreferences.getString(PrefKeys.token.name(), "");
    }

    public void clear() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
