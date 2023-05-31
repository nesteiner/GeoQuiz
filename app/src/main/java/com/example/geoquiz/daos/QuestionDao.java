package com.example.geoquiz.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.geoquiz.models.Question;

import java.io.Serializable;
import java.util.List;

@Dao
public interface QuestionDao extends Serializable {
    @Query("select * from Question")
    List<Question> findAll();

    @Query("select * from QUestion where id = :id")
    Question findOne(long id);

    @Insert
    void insertAll(List<Question> questions);

    @Insert
    void insertOne(Question question);
    @Delete
    void deleteOne(Question question);
}
