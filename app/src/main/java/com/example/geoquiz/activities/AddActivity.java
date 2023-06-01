package com.example.geoquiz.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.geoquiz.R;
import com.example.geoquiz.daos.QuestionDao;
import com.example.geoquiz.models.Question;


public class AddActivity extends AppCompatActivity {
    EditText addText;
    RadioButton trueChoice;
    RadioButton falseChoice;
    Button addButton;
    QuestionDao questionDao;

    static final String ADD_QUESTION = "com.example.geoquiz.add-question";
    static final String ADD_ANS = "com.example.geoquiz.add-answer";

    public static Intent newIntent(AppCompatActivity activity) {
        return new Intent(activity, AddActivity.class);
    }

    public static String getQuestion(Intent result) {
        return result.getStringExtra(ADD_QUESTION);
    }

    public static boolean getAnswer(Intent result) {
        return result.getBooleanExtra(ADD_ANS, true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        addText = findViewById(R.id.add_text);
        trueChoice = findViewById(R.id.true_choice);
        falseChoice = findViewById(R.id.false_choice);
        addButton = findViewById(R.id.add_button);

        questionDao = StartActivity.database.questionDao();

        addButton.setOnClickListener(view -> {
            String questionText = addText.getText().toString();
            Question question = new Question(questionText, trueChoice.isChecked(), false, false);
            questionDao.insertOne(question);
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();

            addText.setText("");
        });

        trueChoice.setOnClickListener(this::onRadioButtonClicked);
        falseChoice.setOnClickListener(this::onRadioButtonClicked);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View view) {
        if (view.getId() == R.id.true_choice) {
            falseChoice.setChecked(false);
        } else if (view.getId() == R.id.false_choice) {
            trueChoice.setChecked(false);
        }
    }
}
