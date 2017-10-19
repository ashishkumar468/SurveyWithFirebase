package com.trichashapps.surveywithfirebase.home.utils;

import com.trichashapps.surveywithfirebase.app.Constant;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;
import com.trichashapps.surveywithfirebase.home.model.domain.QuestionData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 19/10/17.
 */

public class MockUtils {
    public static List<Question> createQuestions() {
        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setId(1);
        question1.setType(Constant.QUESTION_TYPES.TEXT_INPUT);
        QuestionData questionData1 = new QuestionData();
        questionData1.setTitle("What is your name");
        question1.setQuestionData(questionData1);


        Question question2 = new Question();
        question2.setId(2);
        question2.setType(Constant.QUESTION_TYPES.SINGLE_CHOICE);
        QuestionData questionData2 = new QuestionData();
        questionData1.setTitle("What is your age group");
        List<String> options2 = new ArrayList<>();
        options2.add("0-18");
        options2.add("18-25");
        options2.add("25-35");
        options2.add(">35");
        questionData2.setOptions(options2);
        question2.setQuestionData(questionData2);

        Question question3 = new Question();
        question3.setId(3);
        question3.setType(Constant.QUESTION_TYPES.MULTIPLE_CHOICE);
        QuestionData questionData3 = new QuestionData();
        questionData3.setTitle("Will all do you use daily");
        List<String> options3 = new ArrayList<>();
        options3.add("Soap");
        options3.add("Oil");
        options3.add("Perfume");
        options3.add("None");
        questionData3.setOptions(options3);
        question3.setQuestionData(questionData3);

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        return questions;
    }
}
