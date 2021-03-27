package com.example.hw1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {

    private ItemsModel.Callback callback;

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        ItemsModel model = ItemsModelCreator.get_by_index(position);
        holder.bind(model, callback);
    }

    @Override
    public int getItemCount() {
        return ItemsModelCreator.get_count();
    }

    public void addElement() {
        ItemsModelCreator.make_new();
        notifyItemInserted(getItemCount() - 1);
    }

    public void setCallback(ItemsModel.Callback in_callback) {
        callback = in_callback;
    }

}
