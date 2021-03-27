package com.example.hw1;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ItemsModelCreator {
    private final ArrayList<ItemsModel> items = new ArrayList<>();
    private static ItemsModelCreator sInstance;
    static private final int init_size = 100;

    public ItemsModelCreator() {
        int color;
        for (int i = 1; i <= init_size; ++i) {
            color = get_color(i);
            items.add(new ItemsModel(i, color));
        }
    }


    public static ItemsModel get_by_index(int index){
        return getInstance().items.get(index);
    }

    public static void make_new(){
        int number = get_count() + 1;
        int color = get_color(number);
        getInstance().items.add(new ItemsModel(number, color));
    }

    public static int get_count() {
        return getInstance().items.size();
    }

    private static ItemsModelCreator getInstance() {
        if (sInstance == null) {
            sInstance = new ItemsModelCreator();
        }
        return sInstance;
    }

    public static void recover(int last_count) {
        int actual_count = get_count();
        while (actual_count < last_count) {
            getInstance().items.add(
                    new ItemsModel(++actual_count, get_color(actual_count)));
        }
    }

    private static int get_color(int number){
        return (number % 2 == 0) ? R.color.text_color_primary : R.color.text_color_secondary;
    }
}
