package com.trichashapps.surveywithfirebase.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trichashapps.surveywithfirebase.R;
import com.trichashapps.surveywithfirebase.home.adapters.QuestionsAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Ashish on 09/10/17.
 */

public class QuestionsFragment extends Fragment {

    @InjectView(R.id.rv_questions)
    RecyclerView rvQuestions;

    QuestionsAdapter adapter;

    private static QuestionsFragment instance;

    public static QuestionsFragment getInstance() {
        if (instance == null)
            instance = new QuestionsFragment();
        else
            instance.clearData();
        return instance;
    }

    private void clearData() {
        // TODO: 09/10/17
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        ButterKnife.inject(this, view);
        init();
        return view;
    }

    private void init() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        rvQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvQuestions.setAdapter(adapter);
    }
}
