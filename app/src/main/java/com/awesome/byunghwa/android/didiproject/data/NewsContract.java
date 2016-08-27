package com.awesome.byunghwa.android.didiproject.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


public class NewsContract {

	public static final String CONTENT_AUTHORITY = "com.awesome.byunghwa.android.didiproject";
	public static final Uri BASE_URI = Uri.parse("content://com.awesome.byunghwa.android.didiproject");

	public static final String PATH_NEWS = "news";

	private NewsContract() {
	}

	public static final class NewsEntry implements BaseColumns {

		public static final String TABLE_NAME = PATH_NEWS;

		public static final Uri CONTENT_URI =
				BASE_URI.buildUpon().appendPath(PATH_NEWS).build();

		public static final String CONTENT_TYPE =
				ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;
		public static final String CONTENT_ITEM_TYPE =
				ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;

		public static final String _ID = "_id";
		/** Type: TEXT */
		public static final String NEWS_ID = "news_id";
		/** Type: TEXT */
		public static final String NEWS_TITLE = "news_title";
		/** Type: TEXT NOT NULL */
		public static final String PUB_DATE = "pub_date";
		/** Type: TEXT NOT NULL */
		public static final String DESC = "desc";
		/** Type: TEXT NOT NULL */
		public static final String CHANNEL_NAME = "channel_name";
		/** Type: TEXT NOT NULL */
		public static final String CHANNEL_ID = "channel_id";
		/** Type: TEXT NOT NULL */
		public static final String LINK = "link";
		/** Type: TEXT NOT NULL */
		public static final String IMAGE_URL= "url";
		/** Type: TEXT NOT NULL */
		public static final String TYPE = "type";
		/** Type: TEXT NOT NULL 足球，娱乐。。。分类*/


//		/** Matches: /items/ */
//		public static Uri buildDirUri(String newsType) {
//			Uri.Builder builder = new Uri.Builder();
//			builder.scheme("content")
//					.authority(CONTENT_AUTHORITY)
//					.appendPath(TABLE_NAME)
//					.appendPath(newsType);
//			return builder.build();
//		}

		public static Uri buildDirUri() {
			return CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
		}

		/** Matches: /items/[_id]/ */
		public static Uri buildItemUri(long news_id) {
			Uri uri = ContentUris.withAppendedId(CONTENT_URI, news_id);
			return uri;
		}

	}

}
