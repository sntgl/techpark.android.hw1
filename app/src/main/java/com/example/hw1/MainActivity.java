package com.example.hw1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String RECOVER_KEY = "items_count_recover";
    private Fragment activeFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            activeFragment = new ListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_place, activeFragment, ListFragment.TAG)
                    .commit();
        }
        else {
            ItemsModelCreator.recover(savedInstanceState.getInt(RECOVER_KEY));
            activeFragment = loadFragmentIfExists(ListFragment.TAG);
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
        ((ListFragment) activeFragment).setCallback(new ItemsModel.Callback(){
            @Override
            public void call(ItemsModel model) {
                activeFragment = loadFragmentIfExists(FullFragment.TAG);
                ((FullFragment)activeFragment).setModel(model);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_place, activeFragment, FullFragment.TAG)
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