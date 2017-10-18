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
import com.trichashapps.surveywithfirebase.home.model.domain.Question;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;

/**
 * Created by Ashish on 09/10/17.
 */

public class QuestionsFragment extends Fragment {

    @BindView(R.id.rv_questions)
    RecyclerView rvQuestions;

    QuestionsAdapter adapter;

    List<Question> questions;

    private static QuestionsFragment instance;

    private Callback callback;

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

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        rvQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvQuestions.setAdapter(adapter);
        adapter.setQuestionList(questions);
    }

    public interface Callback {
        void onOptionsSelected(Question question);
    }
}
