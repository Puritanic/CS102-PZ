package com.quiz.app.views;

import com.quiz.app.controllers.*;
import com.quiz.entities.Answer;
import com.quiz.entities.Player;
import com.quiz.entities.Question;
import com.quiz.enums.Views;
import com.quiz.interfaces.BaseView;
import com.quiz.models.AnswerButton;
import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

import java.util.List;

/**
 * Klasa zadužena za prikazivanje kviz ekrana aplikacije.
 */
public class GameView extends BorderPane implements BaseView {
    private final AnswerButton[] answerButtons = new AnswerButton[3];
    private Label questionLabel;
    private Label feedbackLabel;
    private GameController gameController;
    private final FadeTransition fadeInOut = new FadeTransition(
            Duration.millis(1200)
    );

    public GameView() {
        System.out.println("GameView loaded");
        getStyleClass().add("game");
        setPadding(new Insets(100, 100, 50, 100));
    }

    @Override
    public void render() {
        setTop(buildQuestionLabel());

        setCenter(buildAnswerButtons());

        setBottom(buildFeedbackLabel());

        QuestionController controller = new QuestionController();
        List<Question> questions = controller.getQuestions();
        AuthController ac = AuthController.getAuthControllerInstance();

        if (ac.getPlayer() != null) {
            gameController = new GameController(questions, ac.getPlayer());
        } else {
            gameController = new GameController(questions);
        }

        Question question = gameController.getNextQuestion();
        displayQuestion(question);
    }

    /**
     * Metoda koja je zadužena za kreiranje Label node-a koji će služiti za prikaz teksta pitanja
     * @return HBox sa Label node-om
     */
    private HBox buildQuestionLabel() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        questionLabel = new Label();
        questionLabel.getStyleClass().add("question");
        questionLabel.setWrapText(true);
        hBox.getChildren().add(questionLabel);

        return hBox;
    }

    /**
     * Metoda koja je zadužena za kreiranje Label node-a koji će služiti za feedback
     * u zavisnosti da li je odgovor na dato pitanje bio tačan ili ne. Takodje konfiguriše jednostavnu
     * FadeTransition animaciju za Label node.
     * @return HBox sa Label node-om
     */
    private HBox buildFeedbackLabel() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        feedbackLabel = new Label();
        feedbackLabel.getStyleClass().add("feedback");
        hBox.getChildren().add(feedbackLabel);
        fadeInOut.setNode(feedbackLabel);

        fadeInOut.setFromValue(0.0);
        fadeInOut.setToValue(1.0);
        fadeInOut.setCycleCount(2);
        fadeInOut.setAutoReverse(true);

        return hBox;
    }

    /**
     * Metoda zadužena za kreiranje vertikalne liste odgovora (AnswerButton nodes)
     * @return VBox - sa odgovorima
     */
    private VBox buildAnswerButtons() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
        vBox.setPadding(new Insets(30, 0, 0, 0));

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

    /**
     * Metoda zadužena za prikaz pitanja sa propratnim odgovorima.
     * @param question instanca Question klase
     */
    private void displayQuestion(Question question) {
        questionLabel.setText(question.getQuestion());
        List<Answer> answers = question.getAnswers();

        try {
            for (int i = 0; i < answers.size(); i++) {
                Answer _answer = answers.get(i);
                answerButtons[i].setText(_answer.getAnswer());
                answerButtons[i].setAnswerId(_answer.getId());
            }
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metoda zadužena za prikaz Game Over interfejsa, nakon završetka igre.
     * Takodje, u slučaju da je korisnik ulogovan, pokreće komunikaciju sa bazom podataka
     * gde se vrši update korisničkih podataka, sa novim brojem poena i brojem završenih partija.
     * @return BorderPane
     */
    private BorderPane displayGameOver() {
        questionLabel.setText("");
        feedbackLabel.setText("");

        System.out.println("Game Over");
        BorderPane bp = new BorderPane();

        HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        Label title = new Label("Game Over");
        title.getStyleClass().add("title");
        top.getChildren().add(title);
        bp.setTop(top);

        VBox gameInfo = new VBox();
        gameInfo.setSpacing(40);
        gameInfo.setAlignment(Pos.CENTER);

        AuthController ac = AuthController.getAuthControllerInstance();

        Label playerGreet = new Label();
        playerGreet.getStyleClass().add("infoLabel");

        if (ac.getPlayer() != null) {
            Player player = ac.getPlayer();
            playerGreet.setText("Congratulations, " + player.getUsername() + ".");
            player.setTotalPoints(player.getTotalPoints() + gameController.getGame().getPoints());
            player.setFinishedGames(player.getFinishedGames() + 1);
            PlayerController pc = new PlayerController();
            pc.updatePlayer(player);
        } else {
            playerGreet.setText("Register or login to save your points.");
        }

        Label pointsInfo = new Label("You've finished the game with " + gameController.getGame().getPoints() + " points.");
        pointsInfo.getStyleClass().add("infoLabel");
        gameInfo.getChildren().addAll(playerGreet, pointsInfo);

        bp.setCenter(gameInfo);

        HBox buttons = new HBox();
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        Button playAgainButton = new Button("Play Again");
        playAgainButton.getStyleClass().add("secondaryButton");

        playAgainButton.setOnAction(e -> {
            ScreenController sc = ScreenController.getScreenControllerInstance();
            sc.show(sc.getScreen(Views.GAME.name()));
        });

        Button goHomeButton = new Button("Home");
        goHomeButton.getStyleClass().add("secondaryButton");

        goHomeButton.setOnAction(e -> {
            ScreenController sc = ScreenController.getScreenControllerInstance();
            sc.show(Views.HOME.name());
        });

        buttons.getChildren().addAll(playAgainButton, goHomeButton);

        bp.setBottom(buttons);

        return bp;
    }

    /**
     * Unutrašnja klasa koja je odgovorna za obradu "klik" dogadjaja AnswerButton-a
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            AnswerButton selectedBtn = (AnswerButton) event.getSource();
            int selectedAnswerId = selectedBtn.getAnswerId();
            Question currentQuestion = gameController.getCurrentQuestion();

            feedbackLabel.setStyle(null);
            if (currentQuestion.getCorrectAnswerId() == selectedAnswerId) {
                feedbackLabel.setText("Correct Answer!");
                feedbackLabel.setStyle("-fx-text-fill: #a6b401");
                fadeInOut.playFromStart();
                gameController.getGame().setPoints(gameController.getGame().getPoints() + 5);
            } else {
                feedbackLabel.setText("Incorrect Answer!");
                feedbackLabel.setStyle("-fx-text-fill: #d50102");
                fadeInOut.playFromStart();
            }

            Question question = gameController.getNextQuestion();
            if (question != null) {
                displayQuestion(question);
            } else {
                setCenter(displayGameOver());
            }
        }
    }
}
