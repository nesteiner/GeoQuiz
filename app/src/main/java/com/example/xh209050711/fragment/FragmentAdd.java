package com.example.xh209050711.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.xh209050711.R;
import com.example.xh209050711.activity.StartActivity;
import com.example.xh209050711.dao.QuestionDao;
import com.example.xh209050711.model.Question;
import org.jetbrains.annotations.NotNull;

public class FragmentAdd extends Fragment {
    EditText addText;
    RadioButton trueChoice;
    RadioButton falseChoice;
    Button addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_add, container, false);
        addText = view.findViewById(R.id.add_text);
        trueChoice = view.findViewById(R.id.true_choice);
        falseChoice = view.findViewById(R.id.false_choice);
        addButton = view.findViewById(R.id.add_button);

        addButton.setOnClickListener(view0 -> {
            QuestionDao questionDao = StartActivity.database.questionDao();
            String questionText = addText.getText().toString();

            if (questionText.length() != 0) {
                Question question = new Question(questionText, trueChoice.isChecked(), false, false);
                questionDao.insertOne(question);
                Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                addText.setText("");
            } else {
                Toast.makeText(getContext(), "文本不能为空", Toast.LENGTH_SHORT).show();
            }
        });
        trueChoice.setOnClickListener(this::onRadioButtonClicked);
        falseChoice.setOnClickListener(this::onRadioButtonClicked);

        return view;
    }

    public void onRadioButtonClicked(View view) {
        if (view.getId() == R.id.true_choice) {
            falseChoice.setChecked(false);
        } else if (view.getId() == R.id.false_choice) {
            trueChoice.setChecked(false);
        }
    }
}
