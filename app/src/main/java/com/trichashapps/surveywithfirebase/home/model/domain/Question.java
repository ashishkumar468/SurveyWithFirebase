package com.trichashapps.surveywithfirebase.home.model.domain;

import android.support.annotation.StringDef;

import com.trichashapps.surveywithfirebase.app.Constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Ashish on 09/10/17.
 */

public class Question {

    private int id;
    private QUESTION_TYPE type;
    private QuestionData questionData;

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
}
