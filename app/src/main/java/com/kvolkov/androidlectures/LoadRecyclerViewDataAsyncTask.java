package com.kvolkov.androidlectures;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class LoadRecyclerViewDataAsyncTask extends AsyncTask<Void, Void, Void> {

    List<String> randomDataList;
    RecyclerViewExampleFragment.ILoadComplete mListener;

    public LoadRecyclerViewDataAsyncTask(List<String> data, RecyclerViewExampleFragment.ILoadComplete listener) {
        randomDataList = data;
        mListener = listener;
    }

    public void setListener(RecyclerViewExampleFragment.ILoadComplete listener) {
        mListener = listener;
    }

    @Override
    @MainThread
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e(this.getClass().getName(), "onPreExecute()");
        Random rnd = new Random();
        for (int i = 0; i < 100000; ++i) {
            randomDataList.add("Item #" + rnd.nextInt(10000000));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    @WorkerThread
    protected Void doInBackground(Void... voids) {
        Log.e(this.getClass().getName(), "doInBackground() starting sort...");
        randomDataList.sort(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareTo(t1);
            }
        });

        Log.e(this.getClass().getName(), "doInBackground() DONE sorting!");
        return null;
    }

    @Override
    @MainThread
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.e(this.getClass().getName(), "onPostExecute()");

        if (mListener != null) {
            mListener.onLoadComplete();
        }
    }
}