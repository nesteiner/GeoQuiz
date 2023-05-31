package com.example.geoquiz.viewmodels;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.example.geoquiz.daos.QuestionDao;
import com.example.geoquiz.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends ViewModel {
    public Context context;
    public List<Question> questions;
    public QuestionDao questionDao;
    public int index;
    public void loadQuestions(Context context, QuestionDao questionDao) {
        this.context = context;
        this.questionDao = questionDao;
        questions = questionDao.findAll();
        questions.forEach(question -> {
            question.finish = false;
        });

        index = 0;
    }

    public int getLength() {
        return questions.size();
    }

    public String currentQuestionText() {
        return questions.get(index).textQuestion;
    }

    public boolean currentQuestionAnswer() {
        return questions.get(index).answerTrue;
    }

    public boolean currentFinish() {
        return questions.get(index).finish;
    }

    public void setCurrentFinish() {
        questions.get(index).finish = true;
    }

    public boolean isCheat() {
        return questions.get(index).cheat;
    }

    public void setCheat() {
        questions.get(index).cheat = true;
    }

    public void addQuestion(String question, boolean answer) {
        Question question0 = new Question(question, answer, true, false);
        questions.add(question0);
        questionDao.insertOne(question0);
    }

    public void addQuestion(Question question) {
        questions.add(question);
        questionDao.insertOne(question);
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }
}
