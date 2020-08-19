package com.kvolkov.androidlectures;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ServiceExample extends Service {

//    private IBinder mBinder = new ServiceBinder();
//
//    public class ServiceBinder extends Binder {
//
//        public void showMessage(String msg) {
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
//
//            ServiceExample.this.someFun();
//        }
//
//    }

    public Handler mMessengeHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String msgData = (String) msg.obj;
                    Toast.makeText(getApplicationContext(), msgData, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(this.getClass().getName(), "onBind()");

        return new Messenger(mMessengeHandler).getBinder();
    }

    public void someFun() {}

}