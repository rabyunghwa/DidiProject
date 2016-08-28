package com.awesome.byunghwa.android.didiproject.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.awesome.byunghwa.android.didiproject.service.NewsUpdaterService;

/**
 * Created by ByungHwa on 8/21/2016.
 */

public class StartServiceUtil {

    public static void startServices(Context context, String newsType) {
        String url = buildUrl(newsType);
        launchService(context, url, newsType);
    }

    private static String buildUrl(String type) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("route.showapi.com")
                .appendPath("109-35")
                .appendQueryParameter("title", type)
                .appendQueryParameter("showapi_appid", "23064")
                .appendQueryParameter("showapi_sign", "cc973cf0b3404267b4544d5371875f12");
        return builder.build().toString();
    }

    private static void launchService(Context context, String url, String type) {
        Intent intent = new Intent(context, NewsUpdaterService.class);
        intent.putExtra("url", url);
        intent.putExtra("type", type);
        context.startService(intent);
    }
}
