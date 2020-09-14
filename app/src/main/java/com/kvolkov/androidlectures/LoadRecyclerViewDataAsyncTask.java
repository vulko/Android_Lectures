package com.kvolkov.androidlectures;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LoadRecyclerViewDataAsyncTask extends AsyncTask<String, Integer, List<String>> {

    private int counter = 0;
    private List<String> mRandomDataList;
    private RecyclerViewExampleFragment.ILoadProgress mListener;

    public LoadRecyclerViewDataAsyncTask(List<String> data, RecyclerViewExampleFragment.ILoadProgress listener) {
        mRandomDataList = data;
        mListener = listener;
    }

    public void setListener(RecyclerViewExampleFragment.ILoadProgress listener) {
        mListener = listener;
    }

    @Override
    @MainThread
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e(this.getClass().getName(), "onPreExecute()");
        Random rnd = new Random();
        for (int i = 0; i < 100000; ++i) {
            mRandomDataList.add("Item #" + rnd.nextInt(10000000));
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        if (mListener != null) {
            int progress = (int) (100.f * (float) values[0] / (float) mRandomDataList.size());
            Log.e(this.getClass().getName(), "onProgressUpdate(" + progress + "%). counter = " + values[0] + "  size = " + mRandomDataList.size());
            if (progress > 100) {
                progress = 100;
            }
            mListener.onLoadProgress(progress);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    @WorkerThread
    protected List<String> doInBackground(String... params) {
        Log.e(this.getClass().getName(), "doInBackground() starting sort...");
        Log.e(this.getClass().getName(), "incoming params size = " + params.length);
        List<String> retVal = new LinkedList<>();
        for (int i = 0; i < params.length; ++i) {
            retVal.add(params[i] + " DONE!");
            Log.e(this.getClass().getName(), "param[" + i + "]" + " = " + params[i]);
        }

        mRandomDataList.sort(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                publishProgress(++counter);
                return s.compareTo(t1);
            }
        });

        Log.e(this.getClass().getName(), "doInBackground() DONE sorting!");
        return retVal;
    }

    @Override
    @MainThread
    protected void onPostExecute(List<String> data) {
        super.onPostExecute(data);
        Log.e(this.getClass().getName(), "onPostExecute()");
        for (int i = 0; i < data.size(); ++i) {
            Log.e(this.getClass().getName(), data.get(i));
        }

        if (mListener != null) {
            mListener.onLoadComplete();
        }
    }
}