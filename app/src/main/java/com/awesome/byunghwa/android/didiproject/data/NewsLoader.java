package com.awesome.byunghwa.android.didiproject.data;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import com.awesome.byunghwa.android.didiproject.utils.LogUtil;

/**
 * Helper for loading a list of articles or a single article.
 */
public class NewsLoader extends CursorLoader {

    public static NewsLoader newAllNewsInstance(Context context, String newsType) {
        LogUtil.log("NewsLoader", "uri: " + NewsContract.NewsEntry.buildDirUri());
        LogUtil.log("NewsLoader", "news type: " + newsType);
        return new NewsLoader(context, NewsContract.NewsEntry.buildDirUri(), newsType);
    }

    public static NewsLoader newInstanceForItemId(Context context, long itemId) {
        return new NewsLoader(context, NewsContract.NewsEntry.buildItemUri(itemId), null);
    }

    private NewsLoader(Context context, Uri uri, String newsType) {
        super(context, uri, Query.PROJECTION, Query.COL_NAME_TYPE + "=?", new String[]{newsType}, null);
    }

    public interface Query {
        String[] PROJECTION = {
                NewsContract.NewsEntry._ID,
                NewsContract.NewsEntry.NEWS_ID,
                NewsContract.NewsEntry.PUB_DATE,
                NewsContract.NewsEntry.NEWS_TITLE,
                NewsContract.NewsEntry.LINK,
                NewsContract.NewsEntry.DESC,
                NewsContract.NewsEntry.CHANNEL_ID,
                NewsContract.NewsEntry.CHANNEL_NAME,
                NewsContract.NewsEntry.IMAGE_URL,
                NewsContract.NewsEntry.TYPE
        };

        String COL_NAME_ID = NewsContract.NewsEntry._ID;
        String COL_NAME_NEWS_ID = NewsContract.NewsEntry.NEWS_ID;
        String COL_NAME_PUB_DATE = NewsContract.NewsEntry.PUB_DATE;
        String COL_NAME_NEWS_TITLE = NewsContract.NewsEntry.NEWS_TITLE;
        String COL_NAME_LINK = NewsContract.NewsEntry.LINK;
        String COL_NAME_DESC = NewsContract.NewsEntry.DESC;
        String COL_NAME_CHANNEL_ID = NewsContract.NewsEntry.CHANNEL_ID;
        String COL_NAME_CHANNEL_NAME = NewsContract.NewsEntry.CHANNEL_NAME;
        String COL_NAME_IMAGE_URL = NewsContract.NewsEntry.IMAGE_URL;
        String COL_NAME_TYPE = NewsContract.NewsEntry.TYPE;

        int _ID = 0;
        int NEWS_ID = 1;
        int PUD_DATE = 2;
        int NEWS_TITLE = 3;
        int LINK = 4;
        int DESC = 5;
        int CHANNEL_ID = 6;
        int CHANNEL_NAME = 7;
        int IMAGE_URL = 8;
        int TYPE = 9;
    }

}
