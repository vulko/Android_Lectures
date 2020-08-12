package com.kvolkov.androidlectures;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServiceExample extends Service {

    private IBinder mBinder = new ServiceBinder();

    public class ServiceBinder extends Binder {

        public void showMessage(String msg) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(this.getClass().getName(), "onBind()");

        return mBinder;
    }

    public void someFun() {}

}
