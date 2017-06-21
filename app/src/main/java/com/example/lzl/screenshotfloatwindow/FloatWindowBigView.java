package com.example.lzl.screenshotfloatwindow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by lzl on 2017/6/1.
 */

public class FloatWindowBigView extends LinearLayout{

    public static int viewWidth;

    public static int viewHeight;

    public FloatWindowBigView(final Context context){
        super(context);
        LayoutInflater.from(context).inflate(R.layout.float_window_big, this);
        View view = findViewById(R.id.big_window_layout);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        Button shot = (Button) findViewById(R.id.shot);
        Button shotLong = (Button) findViewById(R.id.shotLong);
        shot.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
//                screenshot();
                Intent intent = new Intent(getContext(), ScreenShot.class);
                getContext().startActivity(intent);
//                MyWindowManager.screenshot(context);
                MyWindowManager.removeBigWindow(context);

            }

        });
    }



//    private void screenshot(Activity activity) {
//        // 获取屏幕
//        View dView =activity.getWindow().getDecorView();
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
//                Log.i(TAG , "保存中");
//
//                File file = new File(filePath);
//                FileOutputStream os = new FileOutputStream(file);
//                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
//                os.flush();
//                os.close();
//            } catch (Exception e) {
//            }
//        }




}
