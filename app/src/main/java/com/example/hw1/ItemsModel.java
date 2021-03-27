package com.example.hw1;

import java.io.Serializable;

public class ItemsModel implements Serializable {
    int number;
    int color;

    public ItemsModel(int in_number, int in_color) {
        number = in_number;
        color = in_color;
    }

    public interface Callback{
        void call(ItemsModel model);
    }
}
