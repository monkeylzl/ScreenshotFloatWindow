package com.example.lzl.screenshotfloatwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by lzl on 2017/5/27.
 */

public class FloatWindowSmallView extends LinearLayout{

    public static int viewWidth;

    public static int viewHeight;

    private static int statusBarHeight;

    private WindowManager windowManager;

    private WindowManager.LayoutParams mParams;

    private float xInScreen;

    private float yInScreen;

    private float xDownInScreen;

    private float yDownInScreen;

    private float xInView;

    private float yInView;

    public FloatWindowSmallView(Context context){
        super(context);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.float_window_small,this);
        View view = findViewById(R.id.small_window_layout);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        TextView screenshotView = (TextView)findViewById(R.id.screenshot);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY()-getStatusBarHeight();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY()- getStatusBarHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY()- getStatusBarHeight();
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                if(xDownInScreen == xInScreen && yDownInScreen == yInScreen){
                    openBigWindow();
                }
                break;
            default:
                break;
        }
        return true;
    }

    public void setmParams(WindowManager.LayoutParams params){
        mParams = params;
    }

    public void updateViewPosition(){
        mParams.x = (int)(xInScreen-xInView);
        mParams.y = (int)(yInScreen-yInView);
        windowManager.updateViewLayout(this,mParams);
    }

    public void openBigWindow(){
        MyWindowManager.createBigWindow(getContext());
//        MyWindowManager.removeSmallWindow(getContext());
    }

    private int getStatusBarHeight(){
        if(statusBarHeight == 0){
            try{
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelOffset(x);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }
}
