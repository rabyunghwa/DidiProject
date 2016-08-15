package com.awesome.byunghwa.android.didiproject;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {


    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);

        // set app bar
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.htab_toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);

        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(
                R.id.htab_collapse_toolbar);
        collapsingToolbar.setTitleEnabled(false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.htab_tabs);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
//                    case 0:
//                        showToast("One");
//                        break;
//                    case 1:
//                        showToast("Two");
//                        ...
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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
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
