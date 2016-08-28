package com.awesome.byunghwa.android.didiproject.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.awesome.byunghwa.android.didiproject.R;
import com.awesome.byunghwa.android.didiproject.adapter.NewsListRecyclerViewAdapter;
import com.awesome.byunghwa.android.didiproject.data.NewsLoader;
import com.awesome.byunghwa.android.didiproject.utils.LogUtil;
import com.awesome.byunghwa.android.didiproject.utils.NetworkStateUtil;
import com.awesome.byunghwa.android.didiproject.utils.StartServiceUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class DummyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "DummyFrag";

    private RecyclerView recyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private static final int MY_LOADER_ID_NEWS = 0;
    private NewsListRecyclerViewAdapter adapter;

    private AppCompatActivity appCompatActivity;

    public DummyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        appCompatActivity = (AppCompatActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.log("DummyFrag", "onCreateView getting called...");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);

        // recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_dummy);
        int columnCount = getResources().getInteger(R.integer.list_column_count);
        StaggeredGridLayoutManager sglm =
                new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);

        adapter = new NewsListRecyclerViewAdapter(null);
        adapter.setHasStableIds(true);

        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_dummy);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i == MY_LOADER_ID_NEWS) {
            String type = null;
            switch (MainActivityFragment.tabSelection) {
                case 0:
                    type = "国内";
                    break;
                case 1:
                    type = "国际";
                    break;
                case 2:
                    type = "军事";
                    break;
                case 3:
                    type = "财经";
                    break;
                case 4:
                    type = "互联网";
                    break;
                case 5:
                    type = "房产";
                    break;
                case 6:
                    type = "汽车";
                    break;
                case 7:
                    type = "体育";
                    break;
                case 8:
                    type = "娱乐";
                    break;
                case 9:
                    type = "游戏";
                    break;
            }
            LogUtil.log("DummyFrag", "type: " + type);
            return NewsLoader.newAllNewsInstance(getActivity(), type);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        LogUtil.log("DummyFrag", "cursor size: " + cursor.getCount());
        mSwipeRefreshLayout.setRefreshing(false);
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        recyclerView.setAdapter(null);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.log("DummyFrag", "setUserVisibleHint getting called...");
        // we are initting/restarting loader here because this callback is guaranteed to
        // be called when the fragment is visible whereas onResume is not
        if (appCompatActivity != null) {
            if (appCompatActivity.getLoaderManager() != null) {
                if (appCompatActivity.getLoaderManager().getLoader(MY_LOADER_ID_NEWS) == null) {
                    appCompatActivity.getLoaderManager().initLoader(MY_LOADER_ID_NEWS, null, this);
                } else {
                    appCompatActivity.getLoaderManager().restartLoader(MY_LOADER_ID_NEWS, null, this);
                }
            }
        }

    }

    @Override
    public void onRefresh() {
        if (NetworkStateUtil.isNetworkConnectionAvailable(getActivity())) { // only start these services if there is network connection available
            switch (MainActivityFragment.tabSelection) {
                case 0:
                    StartServiceUtil.startServices(getActivity(), "国内");
                    break;
                case 1:
                    StartServiceUtil.startServices(getActivity(), "国际");
                    break;
                case 2:
                    StartServiceUtil.startServices(getActivity(), "军事");
                    break;
                case 3:
                    StartServiceUtil.startServices(getActivity(), "财经");
                    break;
                case 4:
                    StartServiceUtil.startServices(getActivity(), "互联网");
                    break;
                case 5:
                    StartServiceUtil.startServices(getActivity(), "房产");
                    break;
                case 6:
                    StartServiceUtil.startServices(getActivity(), "汽车");
                    break;
                case 7:
                    StartServiceUtil.startServices(getActivity(), "体育");
                    break;
                case 8:
                    StartServiceUtil.startServices(getActivity(), "娱乐");
                    break;
                case 9:
                    StartServiceUtil.startServices(getActivity(), "游戏");
                    break;
            }
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), "Network connection unavailable.", Toast.LENGTH_SHORT).show();
        }

    }
}
