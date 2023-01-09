package com.example.secondapiproject.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.secondapiproject.R;
import com.example.secondapiproject.controllers.TasksApiController;
import com.example.secondapiproject.databinding.ActivityTasksBinding;
import com.example.secondapiproject.interfaces.ApiProcessCallBack;
import com.example.secondapiproject.interfaces.ArrayResponseCallBack;
import com.example.secondapiproject.interfaces.ViewHolderListener;
import com.example.secondapiproject.models.Tasks;
import com.example.secondapiproject.recyclers.tasks.TasksRecyclerAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TasksActivity extends AppCompatActivity implements ViewHolderListener {

    protected ActivityTasksBinding tasksBinding;
    protected TasksRecyclerAdapter adapter;
    protected TasksApiController tasksApiController = new TasksApiController();
    private final List<Tasks> tasksArray = new ArrayList<>();
    private final String TAG = "TasksActivity";
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasksBinding = ActivityTasksBinding.inflate(getLayoutInflater());
        setContentView(tasksBinding.getRoot());

    }

    @Override
    protected void onStart() {
        /* super : It is a reserved word that represents the father's class and what follows it is anything
        or any function we want to access in the father and usually it is the Constructor of the father */
        super.onStart();
        initializedRecyclerView();
    }

    public void initializedRecyclerView() {
        adapter = new TasksRecyclerAdapter(tasksArray);
        adapter.setListener(this);
        tasksBinding.recyclerTasks.setAdapter(adapter);
        getTasks();
    }

    private void getTasks() {
        tasksBinding.progressBarTasks.setVisibility(View.VISIBLE);
        tasksApiController.getTasksData(new ArrayResponseCallBack<Tasks>() {
            @Override
            public void onSuccess(List<Tasks> tasksList) {
                tasksBinding.progressBarTasks.setVisibility(View.GONE);
                tasksArray.clear();
                tasksArray.addAll(tasksList);
                adapter.notifyItemRangeInserted(0, tasksArray.size());
            }

            @Override
            public void onFailure(String message) {
                tasksBinding.progressBarTasks.setVisibility(View.GONE);
                Log.e(TAG, "onFailure: " + message);
            }
        });

        tasksBinding.butAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCreateTask();
            }
        });
    }

    private void createTask(String title) {
        tasksApiController.createTask(title, new ApiProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(TasksActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                getTasks();
            }

            @Override
            public void onFailure(String message) {
//                createFailureMessage = message;
                Log.e(TAG, "onFailure: " + message);
                Toast.makeText(TasksActivity.this, "" + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTask(Tasks task) {
//        task.title = newTitle;
//        Tasks newTask = tasksArray.get(position);
        tasksApiController.updateTask(task, new ApiProcessCallBack() {
            @Override
            public void onSuccess(String message) {
//                Toast.makeText(TasksActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                adapter.notifyItemRangeInserted(0, tasksArray.size());
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(TasksActivity.this, "" + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDeleteAction(int position) {
        tasksApiController.deleteTask(tasksArray.get(position), new ApiProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                tasksArray.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeRemoved(position, tasksArray.size());
                adapter.notifyItemRangeChanged(position, tasksArray.size());
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(TasksActivity.this, "" + message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onUpdateAction(int position) {
        this.position = position;
        dialogUpdateTask(tasksArray.get(position));
    }

    private void dialogUpdateTask(Tasks tasks) {
        Dialog dialog = new Dialog(TasksActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.coustom_update_dialog);

        EditText editTaskName = dialog.findViewById(R.id.edit_new_name);
        Button butUpdate = dialog.findViewById(R.id.but_update);

        butUpdate.setOnClickListener(view -> {
            if (!editTaskName.getText().toString().isEmpty()) {
                tasks.title = editTaskName.getText().toString();
                updateTask(tasks);
                dialog.dismiss();
            } else {
                Toast.makeText(TasksActivity.this, R.string.toast_new_title, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void dialogCreateTask() {
        Dialog dialog = new Dialog(TasksActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.coustom_dialog);

        TextInputEditText editTaskName = dialog.findViewById(R.id.edit_task_name);
        Button addTask = dialog.findViewById(R.id.but_add);

        addTask.setOnClickListener(view -> {
            if (!Objects.requireNonNull(editTaskName.getText()).toString().isEmpty()) {
//                if (editTaskName.length() >= 3 && editTaskName.length() <= 15) {
                createTask(editTaskName.getText().toString());
                dialog.dismiss();
//                } else {
//                    editTaskName.setError("Length Error");
//                }
            } else {
                Toast.makeText(TasksActivity.this, R.string.toast_enter_title, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
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