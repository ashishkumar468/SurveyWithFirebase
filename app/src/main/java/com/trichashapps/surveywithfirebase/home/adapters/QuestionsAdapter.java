package com.trichashapps.surveywithfirebase.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.trichashapps.surveywithfirebase.R;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;`

/**
 * Created by Ashish on 18/10/17.
 */

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    private static final int SINGLE_CHOICE = 1;
    private static final int MULTIPLE_CHOICE = 2;
    private static final int TEXT_TYPE = 3;
    List<Question> questionList;
    private Context context;

    private Callback callback;

    public QuestionsAdapter() {
        this.questionList = new ArrayList<>();
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType;
        Question question = questionList.get(position);
        Question.QUESTION_TYPE questionType = question.getType();
        if (questionType.equals(SINGLE_CHOICE)) {
            itemViewType = SINGLE_CHOICE;
        } else if (questionType.equals(MULTIPLE_CHOICE)) {
            itemViewType = MULTIPLE_CHOICE;
        } else {
            itemViewType = TEXT_TYPE;
        }
        return itemViewType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        context = parent.getContext();
        View view;
        switch (viewType) {
            case SINGLE_CHOICE:
                view = LayoutInflater.from(context).inflate(R.layout.row_item_single_choice, parent, false);
                viewHolder = new SingleChoiceViewHolder(view);
                break;
            case MULTIPLE_CHOICE:
                view = LayoutInflater.from(context).inflate(R.layout.row_item_multiple_choice, parent, false);

                viewHolder = new MultipleChoiceViewHolder(view);
                break;
            case TEXT_TYPE:
            default:
                view = LayoutInflater.from(context).inflate(R.layout.row_item_text_type, parent, false);
                viewHolder = new TextTypeViewHoder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.init(position);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void init(int position);

        public void onSubmitButtonClicked(Question question) {
            callback.onOptionsSelected(question);
        }
    }

    private class SingleChoiceViewHolder extends ViewHolder {
        @InjectView(R.id.tv_question)
        TextView tvQuestion;

        @InjectView(R.id.lv_options)
        ListView lvOptions;

        @InjectView(R.id.btn_submit)
        Button btnSubmit;
        private Question question;


        public SingleChoiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void init(int position) {
            question = questionList.get(position);
            tvQuestion.setText(question.getQuestionData().getTitle());

            lvOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedOption = question.getQuestionData().getOptions().get(i);
                    question.setSelectedAnswer(selectedOption);
                }
            });
        }

        @OnClick(R.id.btn_submit)
        public void onSubmitButtomClicked() {
            super.onSubmitButtonClicked(question);
        }
    }

    private class MultipleChoiceViewHolder extends ViewHolder {
        @InjectView(R.id.tv_question)
        TextView tvQuestion;

        @InjectView(R.id.lv_options)
        ListView lvOptions;

        @InjectView(R.id.btn_submit)
        Button btnSubmit;

        List<String> selectedOptions;
        private Question question;

        public MultipleChoiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void init(int position) {
            selectedOptions = new ArrayList<>();
            question = questionList.get(position);
            tvQuestion.setText(question.getQuestionData().getTitle());
            lvOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedOption = question.getQuestionData().getOptions().get(i);
                    if (!selectedOptions.contains(selectedOption)) {
                        selectedOptions.add(selectedOption);
                    } else {
                        selectedOptions.remove(selectedOption);
                    }
                    question.setSelectedOptions(selectedOptions);
                }
            });
            lvOptions.setAdapter(new SimpleAdapter(context, ));
        }

        @OnClick(R.id.btn_submit)
        public void onSubmitButtomClicked() {
            super.onSubmitButtonClicked(question);
        }
    }

    private class TextTypeViewHoder extends ViewHolder {
        @InjectView(R.id.tv_question)
        TextView tvQuestion;

        @InjectView(R.id.et_answer)
        EditText etAnswer;

        @InjectView(R.id.btn_submit)
        Button btnSubmit;

        private Question question;

        public TextTypeViewHoder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void init(int position) {
            question = questionList.get(position);
            tvQuestion.setText(question.getQuestionData().getTitle());
        }

        @OnClick(R.id.btn_submit)
        public void onSubmitButtomClicked() {
            super.onSubmitButtonClicked(question);
        }
    }

    public interface Callback {
        void onOptionsSelected(Question question);
    }

}
