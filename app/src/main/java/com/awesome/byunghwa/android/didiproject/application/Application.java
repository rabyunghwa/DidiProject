package com.awesome.byunghwa.android.didiproject.application;

import android.content.Context;

import com.awesome.byunghwa.android.didiproject.utils.NetworkStateUtil;
import com.awesome.byunghwa.android.didiproject.utils.StartServiceUtil;


public class Application extends android.app.Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        if (NetworkStateUtil.isNetworkConnectionAvailable(this)) { // only start these services if there is network connection available
            StartServiceUtil.startServices(this, "国内");
            StartServiceUtil.startServices(this, "国际");
            StartServiceUtil.startServices(this, "军事");
            StartServiceUtil.startServices(this, "财经");
            StartServiceUtil.startServices(this, "互联网");
            StartServiceUtil.startServices(this, "房产");
            StartServiceUtil.startServices(this, "汽车");
            StartServiceUtil.startServices(this, "体育");
            StartServiceUtil.startServices(this, "娱乐");
            StartServiceUtil.startServices(this, "游戏");
        }

    }

}
