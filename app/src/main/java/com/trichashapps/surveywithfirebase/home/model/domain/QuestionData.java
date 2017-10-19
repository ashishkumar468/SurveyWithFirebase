package com.trichashapps.surveywithfirebase.home.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ashish on 09/10/17.
 */

public class QuestionData {
    @SerializedName("title")
    private String title;
    @SerializedName("options")
    private List<String> options;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
