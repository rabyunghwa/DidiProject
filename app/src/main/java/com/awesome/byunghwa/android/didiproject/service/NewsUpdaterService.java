package com.awesome.byunghwa.android.didiproject.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;

import com.awesome.byunghwa.android.didiproject.data.NewsContract;
import com.awesome.byunghwa.android.didiproject.data.NewsEntity;
import com.awesome.byunghwa.android.didiproject.data.NewsLoader;
import com.awesome.byunghwa.android.didiproject.application.Application;
import com.awesome.byunghwa.android.didiproject.utils.GlobalConsts;
import com.awesome.byunghwa.android.didiproject.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsUpdaterService extends IntentService {

    private static final String TAG = "NewsUpdaterService";

    private static OkHttpClient client;

    public NewsUpdaterService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        LogUtil.log(TAG, "NewsUpdaterService Started");

        client = new OkHttpClient();

        String url = null;
        String type = null;
        if (intent.hasExtra("url")) {
            url = intent.getStringExtra("url");
        }
        if (intent.hasExtra("type")) {
            type = intent.getStringExtra("type");
        }

        Intent intentNews = new Intent(GlobalConsts.NEWS_FETCHED);

        try {
            // Delete old items so that we dont overload the layout with same data
            Uri dirUriItemsContract = NewsContract.NewsEntry.buildDirUri();

            getContentResolver().delete(dirUriItemsContract,
                    NewsContract.NewsEntry.TYPE + " = ?",
                    new String[]{type});

            String result = run(url);
            ArrayList<NewsEntity> newsList = newsJsonStringToNewsEntity(result);
            LogUtil.log("NewsUpdaterService", "url: " + url);
            LogUtil.log("NewsUpdaterService", "result: " + result);
            LogUtil.log("NewsUpdaterService", "newslist size: " + newsList.size());
            insertNewsListIntoDatabase(newsList, type);

        } catch (Exception e) {
            //Log.e(TAG, "Error updatin");
        }

        // send a broadcast to update refreshing ui and notify the adapter of the range of items inserted
        sendBroadcast(intentNews);
    }

    private static String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private void insertNewsListIntoDatabase(ArrayList<NewsEntity> newsList, String type) {
        NewsEntity newsEntity;
        Vector<ContentValues> cVVector = new Vector<ContentValues>(newsList.size());
        ContentValues values;
        for (int i=0;i<newsList.size();i++) {
            values = new ContentValues();
            newsEntity = newsList.get(i);

            values.put(NewsLoader.Query.COL_NAME_CHANNEL_ID, newsEntity.getChannelId());
            values.put(NewsLoader.Query.COL_NAME_CHANNEL_NAME, newsEntity.getChannelName());
            values.put(NewsLoader.Query.COL_NAME_DESC, newsEntity.getDesc());
            values.put(NewsLoader.Query.COL_NAME_LINK, newsEntity.getLink());
            values.put(NewsLoader.Query.COL_NAME_NEWS_ID, newsEntity.getNewsId());
            values.put(NewsLoader.Query.COL_NAME_NEWS_TITLE, newsEntity.getTitle());
            values.put(NewsLoader.Query.COL_NAME_PUB_DATE, newsEntity.getPubDate());
            values.put(NewsLoader.Query.COL_NAME_TYPE, type);
            values.put(NewsLoader.Query.COL_NAME_IMAGE_URL, newsEntity.getImageUrl());

            cVVector.add(values);
        }

        if (cVVector.size() > 0) {
            ContentValues[] cvArray = new ContentValues[cVVector.size()];
            cVVector.toArray(cvArray);
            int result = Application.mContext.getContentResolver().bulkInsert(NewsContract.NewsEntry.buildDirUri(), cvArray);

            LogUtil.log(TAG, "News Bulk Insert Result: " + result);
        }
    }

    private static ArrayList<NewsEntity> newsJsonStringToNewsEntity(String newsJson) {
        JSONObject newsJsonObject;
        ArrayList<NewsEntity> newsEntityArrayList = null;
        try {
            newsJsonObject = new JSONObject(newsJson)
                    .getJSONObject("showapi_res_body")
                    .getJSONObject("pagebean");
            JSONArray newsArray = newsJsonObject.getJSONArray("contentlist");

            JSONObject obj;
            NewsEntity newsEntity;
            newsEntityArrayList = new ArrayList<>();
            JSONArray imageArray;
            for (int i=0;i<newsArray.length();i++) {
                obj = newsArray.getJSONObject(i);
                LogUtil.log("info", "JSONObject: " + obj);
                newsEntity = new NewsEntity();
                if (obj.has("imageurls")) {
                    imageArray = obj.getJSONArray("imageurls");
                    if (imageArray != null && imageArray.length()>0) {
                        newsEntity.setImageUrl(obj.getJSONArray("imageurls").getJSONObject(0).getString("url"));
                        LogUtil.log("DummyFrag", "url: " + obj.getJSONArray("imageurls").getJSONObject(0).getString("url"));
                    }
                }
                if (obj.has("channelId")) {
                    LogUtil.log("DummyFrag", "channel id: " + obj.getString("channelId"));
                    newsEntity.setChannelId(obj.getString("channelId"));
                }
                if (obj.has("channelName")) {
                    newsEntity.setChannelName(obj.getString("channelName"));
                }
                if (obj.has("desc")) {
                    newsEntity.setDesc(obj.getString("desc"));
                }
                if (obj.has("link")) {
                    newsEntity.setLink(obj.getString("link"));
                }
                if (obj.has("pubDate")) {
                    newsEntity.setPubDate(obj.getString("pubDate"));
                }
                if (obj.has("title")) {
                    newsEntity.setTitle(obj.getString("title"));
                }
                if (obj.has("nid")) {
                    newsEntity.setNewsId(obj.getString("nid"));
                }

                newsEntityArrayList.add(newsEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsEntityArrayList;
    }
}
