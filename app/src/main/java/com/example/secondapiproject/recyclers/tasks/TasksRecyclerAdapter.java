package com.example.secondapiproject.recyclers.tasks;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondapiproject.databinding.ItemTasksBinding;
import com.example.secondapiproject.interfaces.ViewHolderListener;
import com.example.secondapiproject.models.Tasks;

import java.util.List;

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.TasksViewHolder> {

    private final List<Tasks> tasksList;
    //    private Tasks tasks;
    private ViewHolderListener listener;

    public TasksRecyclerAdapter(List<Tasks> technicalArray) {
        this.tasksList = technicalArray;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemTasksBinding tasksBinding = ItemTasksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new TasksViewHolder(tasksBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {

        holder.setData(tasksList.get(position), position, tasksList);
        holder.setListener(listener);

    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public void setListener(ViewHolderListener listener) {
        if (listener != null) {
            this.listener = listener;
        } else {
            Log.e("RecyclerAdapter", "setListener: Listener is null");
        }
    }


    protected static class TasksViewHolder extends RecyclerView.ViewHolder {

        ItemTasksBinding tasksBinding;
        private ViewHolderListener listener;

        public TasksViewHolder(@NonNull ItemTasksBinding binding) {
            super(binding.getRoot());
            tasksBinding = binding;
        }

        private void setData(Tasks tasks, int position, List<Tasks> arrayList) {
            tasksBinding.tvTaskName.setText(tasks.title);
            tasksBinding.imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onDeleteAction(position);

                }
            });

            tasksBinding.imageUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onUpdateAction(position);

                }
            });

        }

        private void setListener(ViewHolderListener listener) {
            this.listener = listener;
        }

    }

}
