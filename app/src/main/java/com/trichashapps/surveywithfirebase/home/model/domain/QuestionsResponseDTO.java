package com.trichashapps.surveywithfirebase.home.model.domain;

import java.util.List;

/**
 * Created by Ashish on 09/10/17.
 */

public class QuestionsResponseDTO {
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
