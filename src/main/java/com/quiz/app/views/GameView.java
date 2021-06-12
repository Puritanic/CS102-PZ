package com.quiz.app.views;

import com.quiz.app.AnswerButton;
import com.quiz.app.controllers.AuthController;
import com.quiz.app.controllers.GameController;
import com.quiz.app.controllers.QuestionController;
import com.quiz.entities.Answer;
import com.quiz.entities.Question;
import com.quiz.interfaces.BaseView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class GameView extends BorderPane implements BaseView {
    private Label questionLabel;
    private Label feedbackLabel;
    private AnswerButton[] answerButtons = new AnswerButton[3];
    private GameController gameController;

    public GameView(){
        System.out.println("GameView loaded");
        getStyleClass().add("game");
        setPadding(new Insets(100, 100, 100, 100));

        setTop(buildQuestionLabel());

        setCenter(buildAnswerButtons());

        setBottom(buildFeedbackLabel());

        QuestionController controller = new QuestionController();
        List<Question> questions = controller.getQuestions();
        AuthController ac = AuthController.getAuthControllerInstance();

        if (ac.getPlayer() != null){
            gameController = new GameController(questions, ac.getPlayer());
        } else {
            gameController = new GameController(questions);
        }

        Question question = gameController.getNextQuestion();
        displayQuestion(question);
    }

    private HBox buildQuestionLabel(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        questionLabel = new Label();
        questionLabel.getStyleClass().add("question");
        questionLabel.setWrapText(true);
        hBox.getChildren().add(questionLabel);

        return hBox;
    }

    private HBox buildFeedbackLabel(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        feedbackLabel = new Label();
        feedbackLabel.getStyleClass().add("feedback");
        hBox.getChildren().add(feedbackLabel);

        return hBox;
    }

    private VBox buildAnswerButtons(){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
        vBox.setPadding(new Insets(30, 0,0,0));

        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new AnswerButton();
            answerButtons[i].getStyleClass().add("answerButton");
            answerButtons[i].setPrefSize(Double.MAX_VALUE, 45);
            answerButtons[i].setContentDisplay(ContentDisplay.LEFT);
            answerButtons[i].setOnAction(new ButtonHandler());
            vBox.getChildren().add(answerButtons[i]);
        }

        return vBox;
    }

    private void displayQuestion(Question question){
        questionLabel.setText(question.getQuestion());
        List<Answer> answers = question.getAnswers();

        try {
            for (int i = 0; i < answers.size(); i++) {
                Answer _answer = answers.get(i);
                answerButtons[i].setText(_answer.getAnswer());
                answerButtons[i].setAnswerId(_answer.getId());
            }
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    private void finishGame(){

    }

    private static class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            AnswerButton selectedBtn = (AnswerButton) event.getSource();
            String selectedAnswer = selectedBtn.getText();
            int selectedAnswerId = selectedBtn.getAnswerId();

            System.out.println(selectedAnswer + " " + selectedAnswerId);
        }
    }
}
