package com.awesome.byunghwa.android.didiproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.awesome.byunghwa.android.didiproject.R;

public class MainActivity extends AppCompatActivity {

    public static AppCompatActivity mActivityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityContext = this;
    }
}
