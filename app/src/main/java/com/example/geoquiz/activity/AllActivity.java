package com.example.geoquiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.geoquiz.R;
import com.example.geoquiz.adapter.AllAdapter;
import com.example.geoquiz.dao.QuestionDao;
import com.example.geoquiz.databases.AppDatabase;
import com.example.geoquiz.model.Question;

import java.util.List;

public class AllActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    public static Intent newIntent(AppCompatActivity activity) {
        return new Intent(activity, AllActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerview);
        AppDatabase database = StartActivity.database;
        QuestionDao questionDao = database.questionDao();
        List<Question> questions = questionDao.findAll();

        AllAdapter adapter = new AllAdapter(questions);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
