package com.example.lzl.screenshotfloatwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/**
 * Created by lzl on 2017/5/27.
 */

public class MyWindowManager {

    private static FloatWindowSmallView smallWindow;

    private static FloatWindowBigView bigWindow;

    private static LayoutParams smallWindowParams;

    private static LayoutParams bigWindowParams;

    private static WindowManager mWindowManager;

    public static void createSmallWindow(Context context){
        WindowManager windowManager = getWindowManager(context);
        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
//        int screenWidth = WindowManager.LayoutParams.WRAP_CONTENT;
//        int screenHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        if(smallWindow == null){
            smallWindow = new FloatWindowSmallView(context);
            if(smallWindowParams == null){
                smallWindowParams = new LayoutParams();
                smallWindowParams.format = PixelFormat.RGBA_8888;
                smallWindowParams.type = LayoutParams.TYPE_PHONE;
                smallWindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE;
                smallWindowParams.gravity = Gravity.LEFT |Gravity.TOP;
                smallWindowParams.width = FloatWindowSmallView.viewWidth;
                smallWindowParams.height = FloatWindowSmallView.viewHeight;
                smallWindowParams.x = screenWidth;
                smallWindowParams.y = screenHeight/2;
            }
            smallWindow.setmParams(smallWindowParams);
            windowManager.addView(smallWindow, smallWindowParams);
        }
    }

    public static void createBigWindow(Context context) {
        WindowManager windowManager = getWindowManager(context);
        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

//        int screenWidth = WindowManager.LayoutParams.WRAP_CONTENT;
//        int screenHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        if (bigWindow == null) {
            bigWindow = new FloatWindowBigView(context);
            if (bigWindowParams == null) {
                bigWindowParams = new LayoutParams();
                bigWindowParams.x = screenWidth / 2 - FloatWindowBigView.viewWidth / 2;
                bigWindowParams.y = screenHeight / 2 - FloatWindowBigView.viewHeight / 2;
                bigWindowParams.type = LayoutParams.TYPE_PHONE;
                bigWindowParams.format = PixelFormat.RGBA_8888;
                bigWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
                bigWindowParams.width = FloatWindowBigView.viewWidth;
                bigWindowParams.height = FloatWindowBigView.viewHeight;
            }
            windowManager.addView(bigWindow, bigWindowParams);
        }
    }

    public static void removeBigWindow(Context context) {
        if (bigWindow != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(bigWindow);
            bigWindow = null;
        }
    }

    public static  boolean isWindowShowing(){
        return  smallWindow != null || bigWindow !=null;
    }

    public static  WindowManager getWindowManager(Context context){
        if(mWindowManager == null){
            mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

//    public static void screenshot(Context context){
//        // 获取屏幕
//
//        View dView =getWindow().getDecorView();
//        dView.setDrawingCacheEnabled(true);
//        dView.buildDrawingCache();
//        Bitmap bmp = dView.getDrawingCache();
//        Log.i(TAG , "截屏");
//        if (bmp != null) {
//            try {
//                // 获取内置SD卡路径
//                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
//                // 图片文件路径
//                String filePath = sdCardPath + File.separator + "screenshot.png";
//                Log.i(TAG, "保存中");
//
//                File file = new File(filePath);
//                FileOutputStream os = new FileOutputStream(file);
//                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
//                os.flush();
//                os.close();
//            } catch (Exception e) {
//            }
//        }
//    }
}
