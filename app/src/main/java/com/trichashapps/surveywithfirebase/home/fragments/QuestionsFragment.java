package com.trichashapps.surveywithfirebase.home.fragments;

import android.app.ProgressDialog;
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
import com.trichashapps.surveywithfirebase.home.activities.HomeActivity;
import com.trichashapps.surveywithfirebase.home.adapters.QuestionsAdapter;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;
import com.trichashapps.surveywithfirebase.home.model.domain.QuestionsResponseDTO;
import com.trichashapps.surveywithfirebase.home.utils.FirebaseHelper;
import com.trichashapps.surveywithfirebase.home.utils.MiscUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ashish on 09/10/17.
 */

public class QuestionsFragment extends Fragment {

    @BindView(R.id.rv_questions)
    RecyclerView rvQuestions;

    QuestionsAdapter adapter;

    List<Question> questions;

    private static QuestionsFragment instance;

    List<Question> userSelectedResponses;

    private DatabaseReference firebaseResponsesReference;

    private ProgressDialog progressDialog;

    public static QuestionsFragment getInstance() {
        instance = new QuestionsFragment();
        return instance;
    }

    public QuestionsFragment() {
        this.userSelectedResponses = new ArrayList<>();
    }

    private void clearData() {
        userSelectedResponses.clear();
        questions.clear();
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
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        initRecyclerView();
        initData();
        initFirebaseResponsesReference();
    }

    private void showProgress() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void hideProgress() {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    private void initFirebaseResponsesReference() {
        firebaseResponsesReference = FirebaseHelper.getInstance().getFirebaseResponsesReference();
    }

    private void initData() {
        if (MiscUtils.isConnectedToInternet(getContext())) {
            showProgress();
            FirebaseHelper firebaseHelper = FirebaseHelper.getInstance();
            FirebaseDatabase firebaseDatabaseInstance = firebaseHelper.getFirebaseDatabaseInstance();

            DatabaseReference reference = firebaseDatabaseInstance.getReference(1 + "");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    QuestionsResponseDTO value = dataSnapshot.getValue(QuestionsResponseDTO.class);
                    questions = value.getQuestions();
                    adapter.setQuestionList(value.getQuestions());
                    hideProgress();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    showMessage(getString(R.string.something_went_wrong));
                }
            });
        } else {
            showMessage(getString(R.string.no_internet_connection));
        }
    }

    private void showMessage(String message) {
        ((HomeActivity) getContext()).showMessage(message);
    }


    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvQuestions.setLayoutManager(layoutManager);
        adapter = new QuestionsAdapter();
        adapter.setCallback(new QuestionsAdapter.Callback() {
            @Override
            public void onOptionsSelected(Question question, int position) {
                if (userSelectedResponses.contains(question)) {
                    userSelectedResponses.remove(question);
                } else {
                    userSelectedResponses.add(question);
                }
                if (position < questions.size() - 1) {
                    rvQuestions.scrollToPosition(position + 1);
                }
            }

            @Override
            public void onSubmit() {

                if (MiscUtils.isConnectedToInternet(getContext())) {
                    if (areResponsesValid()) {
                        showProgress();
                        firebaseResponsesReference.child(System.currentTimeMillis() + "").setValue(userSelectedResponses, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                userSelectedResponses.clear();
                                userSelectedResponses = new ArrayList<>();
                                adapter.setQuestionList(new ArrayList<Question>());
                                adapter.setQuestionList(questions);
                                rvQuestions.scrollToPosition(0);
                                hideProgress();
                                showErrorMessage(getString(R.string.response_saved_succesfully));
                            }
                        });
                    } else {
                        showMessage(getString(R.string.please_make_sure_you_answer_all_the_questions));
                        rvQuestions.scrollToPosition(getInvalidResponsePosition());
                    }
                } else {
                    showMessage(getString(R.string.no_internet_connection));
                }
            }

            @Override
            public void showErrorMessage(String message) {
                showMessage(message);
            }
        });
        rvQuestions.setAdapter(adapter);
    }

    private int getInvalidResponsePosition() {
        int i = 0;
        for (Question question : questions) {
            boolean flag = false;
            for (Question userSelectedRespons : userSelectedResponses) {
                if (question.getId() == userSelectedRespons.getId()) {
                    flag = true;
                }
            }
            if (!flag)
                return i;
            i++;
        }
        return 0;//This case would never happen
    }

    private boolean areResponsesValid() {
        if (userSelectedResponses.size() == questions.size())
            return true;
        return false;
    }
}
