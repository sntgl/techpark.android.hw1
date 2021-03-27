package com.example.hw1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FullFragment extends Fragment {

    public static final String TAG = "FullFragment_tag";
    private static final String RECOVER_KEY = "FullFragment_key";
    private ItemsModel model;

    public FullFragment(){
        super(R.layout.fragment_full);
    }

    public void setModel(ItemsModel in_model) {
        model = in_model;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null)
            model = (ItemsModel) savedInstanceState.getSerializable(RECOVER_KEY);
        View view = inflater.inflate(R.layout.fragment_full, container, false);
        TextView text = view.findViewById(R.id.full_text);
        text.setText("" + (model.number));
        text.setTextColor(ContextCompat.getColor(text.getContext(), model.color));
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(RECOVER_KEY, model);
    }
}