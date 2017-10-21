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
import com.trichashapps.surveywithfirebase.home.adapters.SurveyResponseAdapter;
import com.trichashapps.surveywithfirebase.home.model.SurveyResponse;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;
import com.trichashapps.surveywithfirebase.home.model.domain.QuestionData;
import com.trichashapps.surveywithfirebase.home.utils.FirebaseHelper;
import com.trichashapps.surveywithfirebase.home.utils.MiscUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ashish on 09/10/17.
 */

public class SurveyResponseFragment extends Fragment {
    @BindView(R.id.rv_survey_responses)
    RecyclerView rvSurveyResponses;

    List<SurveyResponse> surveyResponses;

    private SurveyResponseAdapter adapter;

    public static SurveyResponseFragment getInstance() {
        return new SurveyResponseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_survey_response, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        surveyResponses = new ArrayList<>();
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new SurveyResponseAdapter();
        rvSurveyResponses.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSurveyResponses.setAdapter(adapter);

        FirebaseDatabase firebaseDatabaseInstance = FirebaseHelper.getInstance().getFirebaseDatabaseInstance();
        DatabaseReference reference = firebaseDatabaseInstance.getReference(1 + "").child("userResponses");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                surveyResponses = new ArrayList<>();
                Map<String, Object> responseMap = (Map<String, Object>) dataSnapshot.getValue();
                if (responseMap != null && responseMap.size() > 0) {
                    for (String key : responseMap.keySet()) {
                        SurveyResponse surveyResponse = new SurveyResponse();
                        surveyResponse.setSurveyTimestamp(key);
                        List<Map<String, Object>> responseMapList = (List<Map<String, Object>>) responseMap.get(key);
                        List<Question> surveyQuestions = new ArrayList<>();
                        for (Map<String, Object> stringObjectMap : responseMapList) {
                            Question surveyQuesionWithResponse = new Question();
                            surveyQuesionWithResponse.setType((String) stringObjectMap.get("type"));
                            if (stringObjectMap.containsKey("selectedAnswer")) {
                                surveyQuesionWithResponse.setSelectedAnswer((String) stringObjectMap.get("selectedAnswer"));
                            }
                            if (stringObjectMap.containsKey("selectedOptions")) {
                                surveyQuesionWithResponse.setSelectedOptions((List<String>) stringObjectMap.get("selectedOptions"));
                            }
                            surveyQuesionWithResponse.setId(Integer.parseInt(String.valueOf(stringObjectMap.get("id"))));
                            QuestionData questionData = new QuestionData();
                            HashMap<String, Object> questionDataMap = (HashMap<String, Object>) stringObjectMap.get("questionData");
                            questionData.setTitle((String) questionDataMap.get("title"));
                            if (questionDataMap.containsKey("options")) {
                                List<String> options = (List<String>) questionDataMap.get("options");
                                questionData.setOptions(options);
                            }
                            surveyQuesionWithResponse.setQuestionData(questionData);
                            surveyQuestions.add(surveyQuesionWithResponse);
                        }
                        surveyResponse.setQuestions(surveyQuestions);
                        surveyResponses.add(surveyResponse);
                    }
                }
                List<String> surveyResponseStringList = MiscUtils.getSurveyResponseStringList(surveyResponses);
                adapter.setSurveyResponses(surveyResponseStringList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
