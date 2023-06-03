package com.example.geoquiz.dao;

import androidx.room.*;
import com.example.geoquiz.model.Question;

import java.io.Serializable;
import java.util.List;

@Dao
public interface QuestionDao extends Serializable {
    @Query("select * from Question")
    List<Question> findAll();

    @Query("select * from QUestion where id = :id")
    Question findOne(long id);

    @Insert
    long insertOne(Question question);
    @Delete
    void deleteOne(Question question);

    @Update
    void updateOne(Question question);

    @Query("delete from Question")
    void deleteAll();
}
