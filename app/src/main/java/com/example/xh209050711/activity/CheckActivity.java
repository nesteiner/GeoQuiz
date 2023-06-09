package com.example.xh209050711.activity;

import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.xh209050711.R;
import com.example.xh209050711.adapter.CheckAdapter;
import com.example.xh209050711.dao.QuestionDao;
import com.example.xh209050711.databases.AppDatabase;
import com.example.xh209050711.model.Question;

import java.util.List;

public class CheckActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    public static Intent newIntent(AppCompatActivity activity) {
        return new Intent(activity, CheckActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerview);
        AppDatabase database = StartActivity.database;
        QuestionDao questionDao = database.questionDao();
        List<Question> questions = questionDao.findAll();

        CheckAdapter adapter = new CheckAdapter(questions);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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