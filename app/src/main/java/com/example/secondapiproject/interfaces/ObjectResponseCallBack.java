package com.example.secondapiproject.interfaces;

public interface ObjectResponseCallBack<Model> {

    void onSuccess(Model object);

    void onFailure(String message);

}
