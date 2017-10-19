package com.trichashapps.surveywithfirebase.home.model;

import com.trichashapps.surveywithfirebase.home.model.domain.Question;
import com.trichashapps.surveywithfirebase.home.model.domain.QuestionData;

import java.util.List;

/**
 * Created by Ashish on 19/10/17.
 */

public class SurveyResponse {
    private String surveyTimestamp;
    private List<Question> questions;

    public void setSurveyTimestamp(String surveyTimestamp) {
        this.surveyTimestamp = surveyTimestamp;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getSurveyTimestamp() {
        return surveyTimestamp;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

