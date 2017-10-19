package com.trichashapps.surveywithfirebase.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trichashapps.surveywithfirebase.R;

/**
 * Created by Ashish on 09/10/17.
 */

public class SurveyResponseFragment extends Fragment {
    public static SurveyResponseFragment getInstance() {
        return new SurveyResponseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater.from(container.getContext()).inflate(R.layout.row_item_survey_response,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
