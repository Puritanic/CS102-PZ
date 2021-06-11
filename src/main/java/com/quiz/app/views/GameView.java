package com.quiz.app.views;

import com.quiz.DAO.QuestionDAOImpl;
import com.quiz.entities.Question;
import com.quiz.interfaces.BaseView;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class GameView extends BorderPane implements BaseView {
    public GameView(){
        System.out.println("GameView loaded");
        QuestionDAOImpl quest = new QuestionDAOImpl();
        List<Question> questions = quest.getQuestions();

        System.out.println(questions);

        for (Question q:questions) {
            System.out.println();
            System.out.println(q);
            System.out.println();
        }
    }
}
