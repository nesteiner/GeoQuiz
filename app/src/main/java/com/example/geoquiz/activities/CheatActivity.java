package com.example.geoquiz.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.geoquiz.R;

public class CheatActivity extends AppCompatActivity {
    TextView cheatText;
    Button cheatButton;
    boolean answerIsTrue;
    boolean isCheat;

    static final String EXTRA_ANSWER = "com.example.geoquiz.answer";
    static final String EXTRA_CHEAT = "com.example.geoquiz.cheat";

    public static Intent newIntent(AppCompatActivity activity, boolean answer) {
        Intent intent = new Intent(activity, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER, answer);
        return intent;
    }

    public static boolean wasCheat(Intent result) {
        return result.getBooleanExtra(EXTRA_CHEAT, false);
    }

    void setIsCheat(boolean isCheat) {
        Intent data = new Intent();
        data.putExtra(EXTRA_CHEAT, isCheat);
        setResult(Activity.RESULT_OK, data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        isCheat = false;

        cheatButton = findViewById(R.id.cheat_button);
        cheatText = findViewById(R.id.cheat_text);

        if (savedInstanceState != null) {
            isCheat = savedInstanceState.getBoolean("isCheat");
        }

        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER, false);

        cheatButton.setOnClickListener(view -> {
            if (answerIsTrue) {
                cheatText.setText(R.string.correct);
            } else {
                cheatText.setText(R.string.wrong);
            }

            setIsCheat(true);
            isCheat = true;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isCheat", isCheat);
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
