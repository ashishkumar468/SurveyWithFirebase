package com.trichashapps.surveywithfirebase.home.utils;

import android.support.v4.app.Fragment;

/**
 * Created by Ashish on 09/10/17.
 */

public class HomeDataView {
    private String title;
    private Fragment fragment;

    public HomeDataView(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
