package com.example.hw1;

import java.io.Serializable;

public class ItemsModel implements Serializable {
    public final int number;

    public ItemsModel(int in_number) {
        number = in_number;
    }

    public interface Callback{
        void call(ItemsModel model);
    }
}
