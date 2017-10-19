package com.trichashapps.surveywithfirebase.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trichashapps.surveywithfirebase.R;
import com.trichashapps.surveywithfirebase.home.adapters.QuestionsAdapter;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;
import com.trichashapps.surveywithfirebase.home.model.domain.QuestionsResponseDTO;
import com.trichashapps.surveywithfirebase.home.utils.FirebaseHelper;
import com.trichashapps.surveywithfirebase.home.utils.MockUtils;

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
        initData();
    }

    private void initData() {
        FirebaseHelper firebaseHelper = FirebaseHelper.getInstance();
        FirebaseDatabase firebaseDatabaseInstance = firebaseHelper.getFirebaseDatabaseInstance();

        DatabaseReference reference = firebaseDatabaseInstance.getReference(1 + "");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuestionsResponseDTO value = dataSnapshot.getValue(QuestionsResponseDTO.class);
                adapter.setQuestionList(value.getQuestions());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvQuestions.setLayoutManager(layoutManager);
        adapter = new QuestionsAdapter();
        adapter.setCallback(new QuestionsAdapter.Callback() {
            @Override
            public void onOptionsSelected(Question question) {
                callback.onOptionsSelected(question);
            }
        });
        rvQuestions.setAdapter(adapter);
    }

    public interface Callback {
        void onOptionsSelected(Question question);
    }
}
