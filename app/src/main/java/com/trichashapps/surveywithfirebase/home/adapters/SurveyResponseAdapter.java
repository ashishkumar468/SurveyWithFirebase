package com.trichashapps.surveywithfirebase.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trichashapps.surveywithfirebase.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ashish on 19/10/17.
 */

public class SurveyResponseAdapter extends RecyclerView.Adapter<SurveyResponseAdapter.ViewHolder> {
    private Context context;
    private List<String> surveyResponses;

    public SurveyResponseAdapter() {
        this.surveyResponses = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        this.context = parent.getContext();
        View view = layoutInflater.inflate(R.layout.row_item_survey_response, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.init(position);
    }

    @Override
    public int getItemCount() {
        return surveyResponses.size();
    }

    public void setSurveyResponses(List<String> surveyResponses) {
        this.surveyResponses = surveyResponses;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_survey_response)
        TextView tvSurveyResponse;

        @BindView(R.id.view_survey_seperator)
        View viewSurveySeperator;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(int position) {
            if (surveyResponses.get(position).equals("SURVEY_ENDS")) {
                viewSurveySeperator.setVisibility(View.VISIBLE);
            } else {
                viewSurveySeperator.setVisibility(View.GONE);
                tvSurveyResponse.setText(surveyResponses.get(position));
            }
        }
    }
}
