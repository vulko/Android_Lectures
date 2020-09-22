package com.kvolkov.androidlectures;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.room.Room;

import com.kvolkov.androidlectures.db.AppDatabase;
import com.kvolkov.androidlectures.db.User;
import com.kvolkov.androidlectures.ui.UserListFragment;
import com.kvolkov.androidlectures.ui.RecyclerViewExampleFragment;

public class FragmentExampleActivity extends FragmentActivity {

    private int mCounter = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_example);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // init DB
                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                        "database-name").build();
                db.userDao().addUser(new User("admin", "123456"));
                db.userDao().addUser(new User("user", "pass"));
                db.userDao().addUser(new User("guest", ""));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.add(R.id.fragment_container, UserListFragment.newInstance());
                        transaction.addToBackStack("Fragment " + mCounter);
                        mCounter++;
                        transaction.commit();
                    }
                });
            }
        }).start();

        findViewById(R.id.add_fragment_btn).setVisibility(View.GONE);
        findViewById(R.id.add_fragment_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextFragment();
            }
        });
    }

    @Override
    public void onBackPressed() {
        mCounter--;
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            super.onBackPressed();
        }
    }

    private void showNextFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, UserListFragment.newInstance());
        transaction.addToBackStack("Fragment " + mCounter);
        mCounter++;
        transaction.commit();
    }

}
