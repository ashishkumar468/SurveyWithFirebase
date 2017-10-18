package com.trichashapps.surveywithfirebase.home.model.domain;

import java.util.List;

/**
 * Created by Ashish on 09/10/17.
 */

public class QuestionData {
    private String title;
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
