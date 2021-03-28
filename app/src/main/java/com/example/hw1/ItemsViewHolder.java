package com.example.hw1;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

class ItemsViewHolder extends RecyclerView.ViewHolder {

    public ItemsViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    public void bind(ItemsModel model, ItemsModel.Callback in_callback) {
        TextView text = itemView.findViewById(R.id.text);
        FrameLayout item = itemView.findViewById(R.id.item);

        text.setText(String.valueOf(model.number));
        text.setTextColor(ContextCompat.getColor(item.getContext(), ItemsModelCreator.get_color(model.number)));

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in_callback.call(model);
            }
        });
    }

}