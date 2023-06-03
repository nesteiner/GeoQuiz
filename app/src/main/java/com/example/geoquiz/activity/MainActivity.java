package com.example.geoquiz.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.geoquiz.R;
import com.example.geoquiz.dao.QuestionDao;
import com.example.geoquiz.databases.AppDatabase;
import com.example.geoquiz.viewmodel.QuizViewModel;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button trueButton;
    Button submitButton;
    Button falseButton;
    Button cheatButton;
    Button checkButton;
    ImageButton nextButton;
    ImageButton lastButton;
    QuizViewModel quizViewModel;

    int count = 0;
    int countTrue = 0;
    boolean currentCheat = false;

    static final int REQUEST_ACTIVITY_CHEAT = 0;

    public static Intent newIntent(AppCompatActivity activity) {
        return new Intent(activity, MainActivity.class);
    }

    public void setQuestion() {
        textView.setText(String.format(Locale.CHINA, "%d/%d %s", quizViewModel.index + 1, quizViewModel.getLength(), quizViewModel.currentQuestionText()));
    }

    public void checkCheat() {
        if (currentCheat && quizViewModel.currentFinish()) {
            quizViewModel.setCheat();
            quizViewModel.setCurrentFinish();
            count += 1;
        }

        currentCheat = false;
    }

    public void checkAnswer(boolean userAnswer) {
        if (currentCheat || quizViewModel.isCheat()) {
            Toast.makeText(this, "作弊警告", Toast.LENGTH_SHORT).show();
            checkCheat();
        } else {
            if (!quizViewModel.currentFinish()) {
                if (quizViewModel.currentQuestionAnswer() == userAnswer) {
                    Toast.makeText(this, R.string.show_true, Toast.LENGTH_SHORT).show();
                    countTrue += 1;
                } else {
                    Toast.makeText(this, R.string.show_false, Toast.LENGTH_SHORT).show();
                }

                count += 1;
                quizViewModel.setCurrentFinish();
            } else {
                Toast.makeText(this, "这题已经答过了 请下一题", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        textView = findViewById(R.id.question_text);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_imagebutton);
        lastButton = findViewById(R.id.last_imagebutton);
        submitButton = findViewById(R.id.submit_button);
        cheatButton = findViewById(R.id.cheat_button);
        checkButton = findViewById(R.id.check_button);

        AppDatabase database = StartActivity.database;
        QuestionDao questionDao = database.questionDao();
        ViewModelProvider provider = new ViewModelProvider(this);
        quizViewModel = provider.get(QuizViewModel.class);
        quizViewModel.loadQuestions(getApplicationContext(), questionDao);


        if (savedInstanceState != null) {
            quizViewModel.index = savedInstanceState.getInt("index");
            currentCheat = savedInstanceState.getBoolean("currentCheat");
        }

        setQuestion();

        count = 0;

        trueButton.setOnClickListener(view -> {
            checkAnswer(true);
        });

        falseButton.setOnClickListener(view -> {
            checkAnswer(false);
        });

        nextButton.setOnClickListener(view -> {
            checkCheat();
            quizViewModel.index = (quizViewModel.index + 1) % quizViewModel.getLength();
            setQuestion();
        });

        lastButton.setOnClickListener(view -> {
            checkCheat();
            if (quizViewModel.index != 0) {
                quizViewModel.index -= 1;
            } else {
                quizViewModel.index = quizViewModel.getLength() - 1;
            }

            setQuestion();
        });

        submitButton.setOnClickListener(view -> {
            if (quizViewModel.questions.stream().allMatch(x -> x.finish)) {
                double x = (double) countTrue / (double) count * 100;
                Toast.makeText(this, "正确率为" + x + "%", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "还有问题没有完成", Toast.LENGTH_SHORT).show();
            }
        });

        cheatButton.setOnClickListener(view -> {
            Intent intent = CheatActivity.newIntent(this, quizViewModel.currentQuestionAnswer());
            startActivityForResult(intent, REQUEST_ACTIVITY_CHEAT);
        });

        checkButton.setOnClickListener(view -> {
            Intent intent = CheckActivity.newIntent(this);
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", quizViewModel.index);
        outState.putBoolean("currentCheat", currentCheat);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ACTIVITY_CHEAT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    if (quizViewModel.currentFinish()) {
                        currentCheat = CheatActivity.wasCheat(data);
                    }
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}