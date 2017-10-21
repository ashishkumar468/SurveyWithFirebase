package com.trichashapps.surveywithfirebase.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.trichashapps.surveywithfirebase.R;
import com.trichashapps.surveywithfirebase.app.Constant;
import com.trichashapps.surveywithfirebase.home.model.domain.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType;
        Question question = questionList.get(position);
        String questionType = question.getType();

        switch (questionType) {
            case Constant.QUESTION_TYPES.SINGLE_CHOICE:
                itemViewType = SINGLE_CHOICE;
                break;
            case Constant.QUESTION_TYPES.MULTIPLE_CHOICE:
                itemViewType = MULTIPLE_CHOICE;
                break;
            case Constant.QUESTION_TYPES.TEXT_INPUT:
            default:
                itemViewType = TEXT_TYPE;
                break;
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
                viewHolder = new TextTypeViewHolder(view);
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

        public void onNextButtonClicked(Question question, int position) {
            callback.onOptionsSelected(question, position);

            if (position == questionList.size()) {
                callback.onSubmit();
            }
        }
    }

    public class SingleChoiceViewHolder extends ViewHolder {
        @BindView(R.id.tv_question)
        TextView tvQuestion;

        @BindView(R.id.lv_options)
        ListView lvOptions;

        @BindView(R.id.btn_next)
        Button btnNext;

        private Question question;


        public SingleChoiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void init(int position) {
            question = questionList.get(position);
            tvQuestion.setText(question.getQuestionData().getTitle());

            if (position == questionList.size() - 1) {
                btnNext.setText(context.getString(R.string.submit));
            }

            lvOptions.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, question.getQuestionData().getOptions()));
            lvOptions.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

            lvOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedOption = question.getQuestionData().getOptions().get(i);
                    question.setSelectedAnswer(selectedOption);
                }
            });
        }

        @OnClick(R.id.btn_next)
        public void onNextButtomClicked() {
            super.onNextButtonClicked(question, getAdapterPosition());
        }
    }

    public class MultipleChoiceViewHolder extends ViewHolder {
        @BindView(R.id.tv_question)
        TextView tvQuestion;

        @BindView(R.id.lv_options)
        ListView lvOptions;

        @BindView(R.id.btn_next)
        Button btnNext;

        List<String> selectedOptions;
        private Question question;

        public MultipleChoiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void init(int position) {
            selectedOptions = new ArrayList<>();
            question = questionList.get(position);
            tvQuestion.setText(question.getQuestionData().getTitle());

            if (position == questionList.size() - 1) {
                btnNext.setText(context.getString(R.string.submit));
            }


            lvOptions.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_multiple_choice, question.getQuestionData().getOptions()));
            lvOptions.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);


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
        }

        @OnClick(R.id.btn_next)
        public void onNextButtomClicked() {
            super.onNextButtonClicked(question, getAdapterPosition());
        }
    }

    public class TextTypeViewHolder extends ViewHolder {
        @BindView(R.id.tv_question)
        TextView tvQuestion;

        @BindView(R.id.et_answer)
        EditText etAnswer;

        @BindView(R.id.btn_next)
        Button btnNext;

        private Question question;

        public TextTypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void init(int position) {
            etAnswer.setText("");
            question = questionList.get(position);
            tvQuestion.setText(question.getQuestionData().getTitle());

            //The last next button actually submits the response to firebase
            if (position == questionList.size() - 1) {
                btnNext.setText(context.getString(R.string.submit));
            }
        }

        @OnClick(R.id.btn_next)
        public void onNextButtonClicked() {
            question.setSelectedAnswer(etAnswer.getText().toString());
            super.onNextButtonClicked(question, getAdapterPosition());
        }
    }

    public interface Callback {
        void onOptionsSelected(Question question, int position);

        void onSubmit();
    }

}
