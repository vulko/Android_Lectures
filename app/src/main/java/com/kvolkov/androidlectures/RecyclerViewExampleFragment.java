package com.kvolkov.androidlectures;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class RecyclerViewExampleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private TextView mProgress;
    private List<String> mRandomDataList = new LinkedList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerViewAdapter mAdapter;
    private LoadRecyclerViewDataAsyncTask mAsyncTask;

    interface ILoadProgress {
        void onLoadComplete();
        void onLoadProgress(int progress);
    }

    public RecyclerViewExampleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExampleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerViewExampleFragment newInstance(String param1, String param2) {
        RecyclerViewExampleFragment fragment = new RecyclerViewExampleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recyclerview_example, container, false);
        mRecyclerView = view.findViewById(R.id.recycler);
        mProgress = view.findViewById(R.id.progress);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RecyclerViewAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.addItem("Initial item", 0);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setupListener();
            }
        }, 500);
    }

    private void setupListener() {
        RecyclerViewAdapter.ViewHolder holder = (RecyclerViewAdapter.ViewHolder) mRecyclerView.findViewHolderForAdapterPosition(0);
        final ILoadProgress listener = new ILoadProgress() {
            @Override
            public void onLoadComplete() {
                // show data
                mProgress.setVisibility(View.INVISIBLE);
                mAdapter.setItems(mRandomDataList);
            }

            @Override
            public void onLoadProgress(int progress) {
                mProgress.setText(progress + "%");
            }
        };

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress.setVisibility(View.VISIBLE);
                mAsyncTask = new LoadRecyclerViewDataAsyncTask(mRandomDataList, listener);
                mAsyncTask.execute("param 1", "param 2");
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mAsyncTask.setListener(null);
        mAsyncTask.cancel(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}