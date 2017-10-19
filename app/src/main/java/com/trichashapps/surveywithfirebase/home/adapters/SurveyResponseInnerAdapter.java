package com.trichashapps.surveywithfirebase.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trichashapps.surveywithfirebase.R;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ashish on 19/10/17.
 */

class SurveyResponseInnerAdapter extends RecyclerView.Adapter<SurveyResponseInnerAdapter.ViewHolder> {
    List<Question> questions;

    public SurveyResponseInnerAdapter() {
        this.questions = new ArrayList<>();
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_survey_responses, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.init(position);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_question)
        TextView tvQuesiton;

        @BindView(R.id.tv_answer)
        TextView tvAnswer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(int position) {
            Question question = questions.get(position);
            tvQuesiton.setText(question.getQuestionData().getTitle());
            tvAnswer.setText(question.getUserSelectedResponse());
        }
    }
}
