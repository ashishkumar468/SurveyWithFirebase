package com.trichashapps.surveywithfirebase.home.model.domain;

import android.support.annotation.StringDef;

import com.trichashapps.surveywithfirebase.app.Constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by Ashish on 09/10/17.
 */

public class Question {

    private int id;
    private QUESTION_TYPE type;
    private QuestionData questionData;

    private List<String> selectedOptions;

    //This can be from single choice or text type
    private String selectedAnswer;

    @StringDef({Constant.QUESTION_TYPES.SINGLE_CHOICE, Constant.QUESTION_TYPES.MULTIPLE_CHOICE, Constant.QUESTION_TYPES.TEXT_INPUT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface QUESTION_TYPE {
    }


    public int getId() {
        return id;
    }

    public QUESTION_TYPE getType() {
        return type;
    }

    public QuestionData getQuestionData() {
        return questionData;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestionData(QuestionData questionData) {
        this.questionData = questionData;
    }

    public void setType(QUESTION_TYPE type) {
        this.type = type;
    }

    public List<String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
