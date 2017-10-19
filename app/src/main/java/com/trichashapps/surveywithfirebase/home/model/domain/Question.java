package com.trichashapps.surveywithfirebase.home.model.domain;

import android.support.annotation.StringDef;

import com.google.gson.annotations.SerializedName;
import com.trichashapps.surveywithfirebase.app.Constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by Ashish on 09/10/17.
 */

public class Question {
    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;
    @SerializedName("questionData")
    private QuestionData questionData;
    @SerializedName("selectedOptions")
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

    public String getType() {
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

    @QUESTION_TYPE
    public void setType(String type) {
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

    public String getUserSelectedResponse() {
        String userResponse = "";
        String questionType = this.getType();

        switch (questionType) {
            case Constant.QUESTION_TYPES.SINGLE_CHOICE:
                userResponse = this.getSelectedAnswer();
                break;
            case Constant.QUESTION_TYPES.MULTIPLE_CHOICE:
                for (String s : this.getSelectedOptions()) {
                    userResponse += s + " ";
                }
                break;
            case Constant.QUESTION_TYPES.TEXT_INPUT:
            default:
                userResponse = this.getSelectedAnswer();
                break;
        }
        return userResponse;
    }
}
