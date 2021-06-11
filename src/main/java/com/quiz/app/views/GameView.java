package com.quiz.app.views;

import com.quiz.app.controllers.QuestionController;
import com.quiz.entities.Question;
import com.quiz.interfaces.BaseView;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class GameView extends BorderPane implements BaseView {
    public GameView(){
        System.out.println("GameView loaded");
        QuestionController controller = new QuestionController();
        List<Question> questions = controller.getQuestions();

    }
}
