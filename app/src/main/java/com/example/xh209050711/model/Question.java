package com.example.xh209050711.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Question implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String textQuestion;
    public boolean answerTrue;
    public boolean finish;
    public boolean cheat;

    public Question(String textQuestion, boolean answerTrue, boolean finish, boolean cheat) {
        this.id = null;
        this.textQuestion = textQuestion;
        this.answerTrue = answerTrue;
        this.finish = finish;
        this.cheat = cheat;
    }
}
