package com.google.myworldinjectcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.utils.SUtils;
import com.google.utils.Utils2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Utils2.onCreate(this);
        if(SUtils.isFirstRun(this)||!SUtils.isExistFile("/sdcard/huluxia/mctool/MC_0.14.1_huluxia.apk")){
            SUtils.copy_data(this);
        }


    }
}
