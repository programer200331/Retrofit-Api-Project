package com.example.secondapiproject.interfaces;

import java.util.List;

public interface ArrayResponseCallBack<Model> {

    void onSuccess(List<Model> list);

    void onFailure(String message);

}
