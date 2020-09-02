package com.kvolkov.androidlectures;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class FragmentExampleActivity extends FragmentActivity {

    private int mCounter = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_example);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, RecyclerViewExampleFragment.newInstance("Fragment " + mCounter, ""));
        transaction.addToBackStack("Fragment " + mCounter);
        mCounter++;
        transaction.commit();

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
        transaction.add(R.id.fragment_container, ListExampleFragment.newInstance("Fragment " + mCounter, ""));
        transaction.addToBackStack("Fragment " + mCounter);
        mCounter++;
        transaction.commit();
    }

}
