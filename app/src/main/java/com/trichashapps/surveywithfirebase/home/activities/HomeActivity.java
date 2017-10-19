package com.trichashapps.surveywithfirebase.home.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.trichashapps.surveywithfirebase.R;
import com.trichashapps.surveywithfirebase.home.adapters.HomeViewPagerAdapter;
import com.trichashapps.surveywithfirebase.home.fragments.QuestionsFragment;
import com.trichashapps.surveywithfirebase.home.fragments.SurveyResponseFragment;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;
import com.trichashapps.surveywithfirebase.home.utils.FirebaseHelper;
import com.trichashapps.surveywithfirebase.home.utils.HomeDataView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ashish on 09/10/17.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @BindView(R.id.tl_home)
    TabLayout tlHome;

    @BindView(R.id.vp_home)
    ViewPager vpHome;

    List<Question> userSelectedResponses;

    private HomeViewPagerAdapter adapter;

    private List<HomeDataView> homeViewFragments;

    private FirebaseHelper firebaseHelper;

    private QuestionsFragment questionsFragment;

    private SurveyResponseFragment surveyResponseFragment;

    private DatabaseReference firebaseResponsesReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        userSelectedResponses = new ArrayList<>();
        initFirebaseHelper();
        initFirebaseResponsesReference();
        homeViewFragments = new ArrayList<>();
        initViewPager();
        initTabLayout();
    }

    private void initFirebaseResponsesReference() {
        firebaseResponsesReference = firebaseHelper.getFirebaseResponsesReference();
    }

    private void initFirebaseHelper() {
        firebaseHelper = FirebaseHelper.getInstance();
    }

    private void initViewPager() {
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        questionsFragment = QuestionsFragment.getInstance();
        questionsFragment.setCallback(new QuestionsFragment.Callback() {
            @Override
            public void onOptionsSelected(Question question) {
                userSelectedResponses.add(question);
                if (!userSelectedResponses.isEmpty()) {
                    toggleButtonSubmitState(true);
                }
            }
        });
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

    @OnClick(R.id.btn_submit)
    public void onButtonSubmitClicked() {
        toggleButtonSubmitState(false);
        if (!userSelectedResponses.isEmpty()) {
            firebaseResponsesReference.child(System.currentTimeMillis() + "").setValue(userSelectedResponses);
            userSelectedResponses.clear();
        }
    }

    public void toggleButtonSubmitState(boolean state) {
        btnSubmit.setEnabled(state);
        btnSubmit.setClickable(state);
        btnSubmit.setAlpha(state ? 1.0f : 0.5f);
    }

}
