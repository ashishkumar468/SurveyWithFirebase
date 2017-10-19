package com.trichashapps.surveywithfirebase.home.utils;

import com.trichashapps.surveywithfirebase.home.model.SurveyResponse;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 19/10/17.
 */

public class MiscUtils {
    public static List<String> getSurveyResponseStringList(List<SurveyResponse> surveyResponses) {
        List<String> surveyResponseStringList = new ArrayList<>();
        for (SurveyResponse surveyResponse : surveyResponses) {
            surveyResponseStringList.add(surveyResponse.getSurveyTimestamp());
            for (Question question : surveyResponse.getQuestions()) {
                surveyResponseStringList.add(question.getQuestionData().getTitle());
                surveyResponseStringList.add(question.getType());
                if (question.getSelectedOptions() != null) {
                    String selectedOptions = "";
                    for (String selectedOption : question.getSelectedOptions()) {
                        selectedOptions = selectedOption + " ";
                    }
                    surveyResponseStringList.add(selectedOptions.trim());
                } else if (question.getSelectedAnswer() != null) {
                    surveyResponseStringList.add(question.getSelectedAnswer());
                }
            }
            surveyResponseStringList.add("SURVEY_ENDS");
        }
        return surveyResponseStringList;
    }
}
