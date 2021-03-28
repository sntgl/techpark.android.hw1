package com.example.hw1;

import java.util.ArrayList;

public class ItemsModelCreator {
    private final ArrayList<ItemsModel> items = new ArrayList<>();
    private static ItemsModelCreator sInstance;
    static private final int init_size = 100;

    public ItemsModelCreator() {
        for (int i = 1; i <= init_size; ++i) {
            items.add(new ItemsModel(i));
        }
    }


    public static ItemsModel get_by_index(int index) {
        return getInstance().items.get(index);
    }

    public static void make_new() {
        int number = get_count() + 1;
        int color = get_color(number);
        getInstance().items.add(new ItemsModel(number));
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
                    new ItemsModel(++actual_count));
        }
    }

    public static int get_color(int number) {
        return (number % 2 == 0) ? R.color.text_color_primary : R.color.text_color_secondary;
    }
}
