package com.skylink.androidaoptool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.skylink.aspectj.logger.LogMethodTrace;

public class MainActivity extends AppCompatActivity {

    private String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sum(5,"2",9);
    }


    @LogMethodTrace(isRecord = false)
    private int sum(int i,String s,int f){
        int result=0;
        result=i+ Integer.valueOf(s)+f;
        return result;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
