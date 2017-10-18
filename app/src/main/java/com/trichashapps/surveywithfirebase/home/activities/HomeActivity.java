package com.trichashapps.surveywithfirebase.home.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trichashapps.surveywithfirebase.R;
import com.trichashapps.surveywithfirebase.home.adapters.HomeViewPagerAdapter;
import com.trichashapps.surveywithfirebase.home.fragments.QuestionsFragment;
import com.trichashapps.surveywithfirebase.home.fragments.SurveyResponseFragment;
import com.trichashapps.surveywithfirebase.home.presenters.HomePresenter;
import com.trichashapps.surveywithfirebase.home.utils.HomeDataView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Ashish on 09/10/17.
 */

public class HomeActivity extends BaseActivity {

    @InjectView(R.id.tl_home)
    TabLayout tlHome;

    @InjectView(R.id.vp_home)
    ViewPager vpHome;

    private FirebaseDatabase database;

    private DatabaseReference questionsReference;

    private HomeViewPagerAdapter adapter;

    private HomePresenter homePresenter;

    private List<HomeDataView> homeViewFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        homeViewFragments = new ArrayList<>();
        initFirebaseReference();
        initTabLayout();
        initViewPager();
    }

    private void initViewPager() {
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        HomeDataView homeQuestionsFragmentView = new HomeDataView("Questions", QuestionsFragment.getInstance());
        HomeDataView homeResponsesFragmentView = new HomeDataView("Responses", SurveyResponseFragment.getInstance());
        homeViewFragments.add(homeQuestionsFragmentView);
        homeViewFragments.add(homeResponsesFragmentView);
        adapter.setHomeViewData(homeViewFragments);
        vpHome.setAdapter(adapter);
    }

    private void initTabLayout() {
        tlHome.setupWithViewPager(vpHome);
    }

    private void initFirebaseReference() {
        database = FirebaseDatabase.getInstance();
    }


}
