package com.example.secondapiproject.recyclers.countries;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondapiproject.databinding.ItemCountriesBinding;
import com.example.secondapiproject.models.Countries;

import java.util.List;

public class CountriesRecyclerAdapter extends RecyclerView.Adapter<CountriesRecyclerAdapter.CountriesViewHolder> {

    private final List<Countries> countriesList;

    public CountriesRecyclerAdapter(List<Countries> countriesList) {
        this.countriesList = countriesList;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCountriesBinding binding = ItemCountriesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CountriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {

        holder.setData(countriesList.get(position));

    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    protected static class CountriesViewHolder extends RecyclerView.ViewHolder {

        private final ItemCountriesBinding binding;

        public CountriesViewHolder(@NonNull ItemCountriesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setData(Countries countriesList) {

            binding.tvCountryId.setText(String.valueOf(countriesList.id));
            binding.tvCountryName.setText(countriesList.name);

        }

    }

}
