package com.example.secondapiproject.recyclers.users;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondapiproject.databinding.ItemUsersBinding;
import com.example.secondapiproject.models.Users;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private final List<Users> usersArray;

    public UsersRecyclerAdapter(List<Users> users) {
        this.usersArray = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUsersBinding binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.setData(usersArray.get(position));

    }

    @Override
    public int getItemCount() {
        return usersArray.size();
    }


    protected static class UserViewHolder extends RecyclerView.ViewHolder {

        private final ItemUsersBinding binding;

        public UserViewHolder(@NonNull ItemUsersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setData(Users users) {

            binding.tvUserName.setText(users.firstName);
            binding.tvUserEmail.setText(users.email);

        }

    }

}
