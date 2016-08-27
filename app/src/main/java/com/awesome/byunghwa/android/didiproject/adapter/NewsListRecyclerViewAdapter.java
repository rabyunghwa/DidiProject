package com.awesome.byunghwa.android.didiproject.adapter;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awesome.byunghwa.android.didiproject.data.NewsLoader;
import com.awesome.byunghwa.android.didiproject.R;
import com.awesome.byunghwa.android.didiproject.utils.LogUtil;
import com.bumptech.glide.Glide;



public class NewsListRecyclerViewAdapter extends RecyclerView.Adapter<NewsListRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "NewsListRecyclerViewAdapter";

    private Cursor mCursor;


    public NewsListRecyclerViewAdapter(Cursor cursor) {
        mCursor = cursor;
    }

    @Override
    public long getItemId(int position) {
        mCursor.moveToPosition(position);
        return mCursor.getLong(NewsLoader.Query._ID);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (mCursor != null) {
            return mCursor.getCount();
        }
        return 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView thumbnailView;
        public TextView titleView;
        public TextView subtitleView;

        public ViewHolder(View view) {
            super(view);
            thumbnailView = (ImageView) view.findViewById(R.id.thumbnail);
            titleView = (TextView) view.findViewById(R.id.article_title);
            subtitleView = (TextView) view.findViewById(R.id.article_subtitle);
            view.setOnClickListener(this);
        }

        public void bind(int position) {
            mCursor.moveToPosition(position);
            titleView.setText(mCursor.getString(NewsLoader.Query.NEWS_TITLE));
            LogUtil.log("NewsAdapter", "news type: " + mCursor.getString(NewsLoader.Query.TYPE));
            subtitleView.setSelected(true);
            subtitleView.setText(
                    mCursor.getString(NewsLoader.Query.PUD_DATE).substring(0, 11)
                            + " by "
                            + mCursor.getString(NewsLoader.Query.CHANNEL_NAME));

            LogUtil.log(TAG, "position: " + position + ", photo url: " + mCursor.getString(NewsLoader.Query.IMAGE_URL));
            if (mCursor.getString(NewsLoader.Query.IMAGE_URL) != null) {
                thumbnailView.setVisibility(View.VISIBLE);
                Glide
                        .with(thumbnailView.getContext())
                        .load(mCursor.getString(NewsLoader.Query.IMAGE_URL))
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .crossFade()
                        .into(thumbnailView);
            } else {
                thumbnailView.setVisibility(View.GONE);
            }

            LogUtil.log(TAG, "ArticleListRecyclerViewAdapter thumbnail url: " + mCursor.getString(NewsLoader.Query.IMAGE_URL));
            itemView.setTag(position);
        }

        @Override
        public void onClick(View v) {
//            mIsReentering = false;
//            LogUtil.log_i("startActivity(Intent, Bundle)", false);
//            //Uri uriTag = (Uri) itemView.getTag();
//            int position = (int) itemView.getTag();
//            //long clickedArticleId = ItemsContract.Items.getItemId(uriTag);
//            long clickedArticleId = position;
//            LogUtil.log_i(TAG, "clicked article id: " + clickedArticleId);
//            Intent intent = new Intent(ArticleListActivity.this, ArticleDetailActivity.class);
//            intent.putExtra(EXTRA_CURRENT_ITEM_POSITION, position);
//            LogUtil.log_i(TAG, "extra current item position: " + position); // extra current item position: 0
//            intent.putExtra(ArticleDetailActivity.KEY_CLICKED_IMAGE_ID, clickedArticleId);
//            LogUtil.log_i(TAG, "extra clicked image id: " + position); //extra clicked image id: 2036
//            LogUtil.log_i(TAG, "onClick thumbnail transition name: " + thumbnailView.getTransitionName());// onClick thumbnail transition name: first
//            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
//                    ArticleListActivity.this, thumbnailView, thumbnailView.getTransitionName()).toBundle());
        }
    }

    public void swapCursor(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }
}