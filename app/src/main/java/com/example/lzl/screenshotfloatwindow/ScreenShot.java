package com.example.lzl.screenshotfloatwindow;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by lzl on 2017/6/1.
 */

public class ScreenShot extends Activity{
    private String TAG = "ShotActivity";
    private static Activity secondactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shot);
//        Intent intent = new Intent(ShotActivity.this, FxService.class);
//        stopService(intent);
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String ss = mActivityManager.getRunningTasks(2).get(1).topActivity.toString();;;
        String s = mActivityManager.getRunningAppProcesses().get(1).processName;
        System.out.println(ss);
        System.out.println(s);
//        try {
//            secondactivity = (Activity) Class.forName(s).newInstance();
//            System.out.println(secondactivity);
//            System.out.println();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        screenshot(secondactivity);

    }

    private void screenshot(Activity activity) {

        // 获取屏幕
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        View dView =activity.getWindow().getDecorView();
//        View dView =getApplicationContext().getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bmp = dView.getDrawingCache();
        Log.i(TAG , "截屏");
        if (bmp != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                String filePath = sdCardPath + File.separator + "screenshot.png";
                Log.i(TAG , "保存中");

                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
            }
        }
    }
}
