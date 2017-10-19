package com.trichashapps.surveywithfirebase.home.utils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;
import com.trichashapps.surveywithfirebase.home.model.domain.QuestionsResponseDTO;

import java.util.List;

/**
 * Created by Ashish on 19/10/17.
 */

public class FirebaseHelper {

    private static FirebaseHelper instance;
    private FirebaseDatabase firebaseDatabaseInstance;
    QuestionsResponseDTO value;


    public FirebaseHelper() {
        this.firebaseDatabaseInstance = FirebaseDatabase.getInstance();
    }

    public static FirebaseHelper getInstance() {
        if (instance == null) {
            instance = new FirebaseHelper();
        }
        return instance;
    }

    public FirebaseDatabase getFirebaseDatabaseInstance() {
        return firebaseDatabaseInstance;
    }


    public void setQuestions(List<Question> questions, int surveyId) {
        DatabaseReference reference = firebaseDatabaseInstance.getReference(surveyId + "");
        DatabaseReference questionsReference = reference.child("questions");
        questionsReference.setValue(questions);
    }
}
