package com.example.lzl.screenshotfloatwindow;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/**
 * Created by lzl on 2017/5/27.
 */

public class FlaotWindowService extends Service {

    Handler handler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    @Override
//    public void onCreate(){
//        super.onCreate();
//        MyWindowManager.createSmallWindow(getApplicationContext());
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!MyWindowManager.isWindowShowing()){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    MyWindowManager.createSmallWindow(getApplicationContext());
                }
            });
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
