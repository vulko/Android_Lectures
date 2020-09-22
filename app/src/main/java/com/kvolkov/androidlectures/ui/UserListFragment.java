package com.kvolkov.androidlectures.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.kvolkov.androidlectures.R;
import com.kvolkov.androidlectures.db.AppDatabase;
import com.kvolkov.androidlectures.db.User;

import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment {

    private ListView mListView;

    public UserListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExampleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserListFragment newInstance() {
        UserListFragment fragment = new UserListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class,
                        "database-name").build();
                final List<User> data = db.userDao().getAllUsers();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<String> mDataArray = new ArrayList<>();
                        for(int i = 0; i < data.size(); ++i) {
                            mDataArray.add(data.get(i).getName() + " : " + data.get(i).getPassword());
                        }
                        ListAdapter mListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.text_item, mDataArray);
                        mListView.setAdapter(mListAdapter);
                    }
                });
            }
        }).start();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_example, container, false);
        mListView = view.findViewById(R.id.list);

        return view;
    }

}