package com.example.xh209050711.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import com.example.xh209050711.R;
import com.example.xh209050711.databases.AppDatabase;
import com.example.xh209050711.viewmodel.QuizViewModel;

import java.util.Locale;

public class StartActivity extends AppCompatActivity {
    TextView startTitle;
    Button startOpen;
    Button startAdd;
    Button startAll;
    Button startClear;
    QuizViewModel quizViewModel;

    static final String QUESTION_LENGTH = "com.example.geoquiz.start.question.length";
    static final int REQUSET_ACTIVITY_ADD = 0;
    public static AppDatabase database = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startTitle = findViewById(R.id.start_title);
        startOpen = findViewById(R.id.start_open);
        startAdd = findViewById(R.id.start_add);
        startAll = findViewById(R.id.start_all);
        startClear = findViewById(R.id.start_clear);

        if (database == null) {
            database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "data.db").allowMainThreadQueries().build();
        }

        ViewModelProvider provider = new ViewModelProvider(this);
        quizViewModel = provider.get(QuizViewModel.class);
        quizViewModel.loadQuestions(getApplicationContext(), database.questionDao());

        startTitle.setText(String.format(Locale.CHINA, "题库中共有%d道题", quizViewModel.getLength()));
        startOpen.setOnClickListener(view -> {
            if (quizViewModel.getLength() == 0) {
                Toast.makeText(this, "目前还没有题目，请添加题目", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = MainActivity.newIntent(this);
                startActivity(intent);
            }
        });

        startAdd.setOnClickListener(view -> {
            Intent intent = AddActivity.newIntent(this);
            startActivityForResult(intent, REQUSET_ACTIVITY_ADD);
        });

        startAll.setOnClickListener(view -> {
            Intent intent = AllActivity.newIntent(this);
            startActivity(intent);
        });

        startClear.setOnClickListener(view -> {
            database.questionDao().deleteAll();
            Toast.makeText(this, "已清除所有题目", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUSET_ACTIVITY_ADD && data != null) {
            if (resultCode == RESULT_OK) {
                String question = AddActivity.getQuestion(data);
                boolean answer = AddActivity.getAnswer(data);
                quizViewModel.addQuestion(question, answer);
                startTitle.setText(String.format(Locale.CHINA, "题库中共有%d题", quizViewModel.getLength()));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        int length = quizViewModel.getLength();
        outState.putInt(QUESTION_LENGTH, length);;
        for (int i = 7; i < length; i += 1) {
            outState.putSerializable(String.format(Locale.CHINA, "question%d",i), quizViewModel.getQuestion(i));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        quizViewModel.loadQuestions(getApplicationContext(), database.questionDao());
        startTitle.setText(String.format(Locale.CHINA, "题库中共有%d道题", quizViewModel.getLength()));
    }
}
