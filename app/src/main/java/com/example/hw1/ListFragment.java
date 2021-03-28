package com.example.hw1;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ListFragment extends Fragment {
    private ItemsModel.Callback callback;
    public static final String TAG = "list_fragment_tag";

    public ListFragment() {
        super(R.layout.fragment_list);
    }

    public void setCallback(ItemsModel.Callback in_callback) {
        this.callback = in_callback;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view == null)
            return null;
        RecyclerView recycler = view.findViewById(R.id.items_list_view);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.recycler_columns_count)));

        ItemsAdapter adapter = new ItemsAdapter();
        adapter.setCallback(this.callback);
        recycler.setAdapter(adapter);

        view.findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addElement();
            }
        });
        return view;
    }

}