package com.trichashapps.surveywithfirebase.home.model;

import com.google.gson.annotations.SerializedName;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by Ashish on 19/10/17.
 */

public class SurveyResponseDTO {
    @SerializedName("userResponses")
    private Map<String, List<Question>> userResponses;

    public Map<String, List<Question>> getSurveyResponseList() {
        return userResponses;
    }
}
