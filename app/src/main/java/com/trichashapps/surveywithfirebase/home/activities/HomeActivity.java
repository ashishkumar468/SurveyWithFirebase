package com.trichashapps.surveywithfirebase.home.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.trichashapps.surveywithfirebase.R;
import com.trichashapps.surveywithfirebase.home.adapters.HomeViewPagerAdapter;
import com.trichashapps.surveywithfirebase.home.fragments.QuestionsFragment;
import com.trichashapps.surveywithfirebase.home.fragments.SurveyResponseFragment;
import com.trichashapps.surveywithfirebase.home.utils.FirebaseHelper;
import com.trichashapps.surveywithfirebase.home.utils.HomeDataView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ashish on 09/10/17.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tl_home)
    TabLayout tlHome;

    @BindView(R.id.vp_home)
    ViewPager vpHome;

    private HomeViewPagerAdapter adapter;

    private List<HomeDataView> homeViewFragments;

    private FirebaseHelper firebaseHelper;

    private QuestionsFragment questionsFragment;

    private SurveyResponseFragment surveyResponseFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        initFirebaseHelper();
        homeViewFragments = new ArrayList<>();
        initViewPager();
        initTabLayout();
    }

    private void initFirebaseHelper() {
        firebaseHelper = FirebaseHelper.getInstance();
    }

    private void initViewPager() {
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        questionsFragment = QuestionsFragment.getInstance();
        surveyResponseFragment = SurveyResponseFragment.getInstance();
        HomeDataView homeQuestionsFragmentView = new HomeDataView("Questions", questionsFragment);
        HomeDataView homeResponsesFragmentView = new HomeDataView("Responses", surveyResponseFragment);
        homeViewFragments.add(homeQuestionsFragmentView);
        homeViewFragments.add(homeResponsesFragmentView);
        adapter.setHomeViewData(homeViewFragments);
        vpHome.setAdapter(adapter);
    }

    private void initTabLayout() {
        tlHome.setupWithViewPager(vpHome);
    }

    public void showMessage(String mesage) {
        Snackbar.make(toolbar, mesage, Snackbar.LENGTH_SHORT).show();
    }

}
