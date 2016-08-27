package com.awesome.byunghwa.android.didiproject.fragment;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.awesome.byunghwa.android.didiproject.R;
import com.awesome.byunghwa.android.didiproject.adapter.ViewPagerAdapter;
import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {

    public static int tabSelection;

    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);

        tabSelection = 0;

        // set app bar
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.htab_toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);

        // find the backdrop imageview
        final ImageView backdrop = (ImageView) view.findViewById(R.id.htab_header);
        backdrop.setImageResource(R.drawable.national); // initialize it to the first category

        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(
                R.id.htab_collapse_toolbar);
        collapsingToolbar.setTitleEnabled(false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.htab_tabs);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabSelection = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        Glide
                                .with(getActivity())
                                .load(R.drawable.national)
                                .centerCrop()
                                .override(500, 500)
                                .crossFade()
                                .into(backdrop);
                        break;
                    case 1:
                        Glide
                                .with(getActivity())
                                .load(R.drawable.international)
                                .centerCrop()
                                .override(500, 500)
                                .crossFade()
                                .into(backdrop);
                        break;
                    case 2:
                        Glide
                                .with(getActivity())
                                .load(R.drawable.military)
                                .centerCrop()
                                .override(500, 500)
                                .crossFade()
                                .into(backdrop);
                        break;
                    case 3:
                        Glide
                                .with(getActivity())
                                .load(R.drawable.finance)
                                .centerCrop()
                                .override(500, 500)
                                .crossFade()
                                .into(backdrop);
                        break;
                    case 4:
                        Glide
                                .with(getActivity())
                                .load(R.drawable.internet)
                                .centerCrop()
                                .override(500, 500)
                                .crossFade()
                                .into(backdrop);
                        break;
                    case 5:
                        Glide
                                .with(getActivity())
                                .load(R.drawable.real_estate)
                                .centerCrop()
                                .override(500, 500)
                                .crossFade()
                                .into(backdrop);
                        break;
                    case 6:
                        Glide
                                .with(getActivity())
                                .load(R.drawable.car)
                                .centerCrop()
                                .override(500, 500)
                                .crossFade()
                                .into(backdrop);
                        break;
                    case 7:
                        Glide
                                .with(getActivity())
                                .load(R.drawable.sports)
                                .centerCrop()
                                .override(500, 500)
                                .crossFade()
                                .into(backdrop);
                        break;
                    case 8:
                        Glide
                                .with(getActivity())
                                .load(R.drawable.entertainment)
                                .centerCrop()
                                .override(500, 500)
                                .crossFade()
                                .into(backdrop);
                        break;
                    case 9:
                        Glide
                                .with(getActivity())
                                .load(R.drawable.games)
                                .centerCrop()
                                .override(500, 500)
                                .crossFade()
                                .into(backdrop);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new DummyFragment(), "国内焦点");
        adapter.addFrag(new DummyFragment(), "国际焦点");
        adapter.addFrag(new DummyFragment(), "军事焦点");
        adapter.addFrag(new DummyFragment(), "财经焦点");
        adapter.addFrag(new DummyFragment(), "互联网焦点");
        adapter.addFrag(new DummyFragment(), "房产焦点");
        adapter.addFrag(new DummyFragment(), "汽车焦点");
        adapter.addFrag(new DummyFragment(), "体育焦点");
        adapter.addFrag(new DummyFragment(), "娱乐焦点");
        adapter.addFrag(new DummyFragment(), "游戏焦点");
        viewPager.setAdapter(adapter);
    }

}
