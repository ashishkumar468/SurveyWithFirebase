package com.trichashapps.surveywithfirebase.home.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.trichashapps.surveywithfirebase.home.model.SurveyResponse;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Ashish on 19/10/17.
 */

public class MiscUtils {
    public static List<String> getSurveyResponseStringList(List<SurveyResponse> surveyResponses) {
        List<String> surveyResponseStringList = new ArrayList<>();
        for (SurveyResponse surveyResponse : surveyResponses) {
            surveyResponseStringList.add(surveyResponse.getSurveyTimestamp());
            for (Question question : surveyResponse.getQuestions()) {
                surveyResponseStringList.add(question.getQuestionData().getTitle());
                surveyResponseStringList.add(question.getType());
                if (question.getSelectedOptions() != null) {
                    String selectedOptions = "";
                    for (String selectedOption : question.getSelectedOptions()) {
                        selectedOptions = selectedOptions + selectedOption + ", ";
                    }
                    selectedOptions = selectedOptions.substring(0, selectedOptions.length() - 2);
                    surveyResponseStringList.add(selectedOptions.trim());
                } else if (question.getSelectedAnswer() != null) {
                    surveyResponseStringList.add(question.getSelectedAnswer());
                }
            }
            surveyResponseStringList.add("SURVEY_ENDS");
        }
        return surveyResponseStringList;
    }

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static String getHumanReadableDate(String timestamp) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        //Find a better way to do this..
        try {
            calendar.setTimeInMillis(Long.parseLong(timestamp));
        } catch (Exception e) {
            return timestamp;
        }
        TimeZone tz = TimeZone.getDefault();
        sdf.setTimeZone(tz);
        return sdf.format(calendar.getTime());
    }
}
