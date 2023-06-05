package com.example.xh209050711.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.xh209050711.dao.QuestionDao;
import com.example.xh209050711.model.Question;

@Database(entities = {Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
}
