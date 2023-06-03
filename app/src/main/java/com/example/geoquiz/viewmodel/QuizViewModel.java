package com.example.geoquiz.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.example.geoquiz.dao.QuestionDao;
import com.example.geoquiz.model.Question;

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
        Question question = questions.get(index);
        question.finish = true;
        questionDao.updateOne(question);
    }

    public boolean isCheat() {
        return questions.get(index).cheat;
    }

    public void setCheat() {
        Question question = questions.get(index);
        question.cheat = true;
        questionDao.updateOne(question);
    }

    public void addQuestion(String question, boolean answer) {
        Question question0 = new Question(question, answer, true, false);
        question0.id = questionDao.insertOne(question0);
        questions.add(question0);
    }

    public void addQuestion(Question question) {
        question.id = questionDao.insertOne(question);
        questions.add(question);
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }
}
