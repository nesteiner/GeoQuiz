package com.example.geoquiz.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.geoquiz.daos.QuestionDao;
import com.example.geoquiz.models.Question;

@Database(entities = {Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
}
