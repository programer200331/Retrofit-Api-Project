package com.example.secondapiproject.controllers;

import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.example.secondapiproject.models.Tasks;

public class TasksApiController extends ApiBaseController {

    public void createTask(String title, ApiProcessCallBack callback) {
        if (!title.trim().isEmpty()) {
            Tasks task = new Tasks();
            task.title = title;
            task.createTask(callback);
        } else {
            callback.onFailure("Enter required data!");
        }
    }

    public void updateTask(Tasks tasks, ApiProcessCallBack callback) {
        if (tasks != null && tasks.title != null) {
            tasks.updateTask(callback);
        } else {
            callback.onFailure("Enter required data!");
        }
    }

    public void getTasksData(ArrayResponseCallBack<Tasks> callback) {
        Tasks.readTasksData(callback);
    }

    public void deleteTask(Tasks task, ApiProcessCallBack callback) {
        task.deleteTask(callback);
    }

}
