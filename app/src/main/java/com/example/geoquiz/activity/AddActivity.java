package com.example.geoquiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.geoquiz.R;



public class AddActivity extends AppCompatActivity {
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
