package com.example.hw1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String RECOVER_KEY = "items_count_recover";
    private FullFragment fullFragment;
    private ListFragment listFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            listFragment = new ListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_place, listFragment, ListFragment.TAG)
                    .commit();
        }
        else {
            ItemsModelCreator.recover(savedInstanceState.getInt(RECOVER_KEY));
            listFragment = (ListFragment) loadFragmentIfExists(ListFragment.TAG);
        }
        setListCallback();
    }

    private Fragment loadFragmentIfExists(String tag){
        Fragment returnFragment;
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null)
            returnFragment = new FullFragment();
        else
            returnFragment = fragment;
        return returnFragment;
    }

    private void setListCallback(){
        listFragment.setCallback(new ItemsModel.Callback(){
            @Override
            public void call(ItemsModel model) {
                fullFragment = (FullFragment) loadFragmentIfExists(FullFragment.TAG);
                fullFragment.setModel(model);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_place, fullFragment, FullFragment.TAG)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(RECOVER_KEY, ItemsModelCreator.get_count());
    }

}