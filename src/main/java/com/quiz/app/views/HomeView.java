package com.quiz.app.views;

import com.quiz.app.controllers.AuthController;
import com.quiz.app.controllers.ScreenController;
import com.quiz.enums.Views;
import com.quiz.interfaces.BaseView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class HomeView extends BorderPane implements BaseView {
    public HomeView() {
        super();
        getStyleClass().add("home");
        setPadding(new Insets(100, 100, 100, 100));

        Label welcomeMsg = new Label("Welcome to Java Quiz");
        welcomeMsg.getStyleClass().add("title");

        AuthController ac = AuthController.getAuthControllerInstance();
        boolean isAuthenticated = (ac.getPlayer() != null);

        setButtons(isAuthenticated);

        AuthController.getAuthControllerInstance().playerProperty().addListener((obs, oldPlayer, newPlayer) ->
                setButtons((newPlayer != null))
        );

        HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        top.getChildren().add(welcomeMsg);

        HBox center = new HBox();
        Image quizLogo = new Image("images/quizlet.png");
        ImageView imgView = new ImageView(quizLogo);
        imgView.setFitHeight(150);
        imgView.setFitWidth(150);
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(50, 0, 0, 0));
        center.getChildren().add(imgView);

        setTop(top);
        setCenter(center);
    }

    @Override
    public void resetView() {

    }

    private void setButtons(boolean isAuthenticated) {
        if (isAuthenticated) {
            setBottom(withAuthButton(loadMainButtons(true), "Sign out"));
        } else {
            setBottom(withAuthButton(loadMainButtons(false), "Sign in"));
        }
    }

    private HBox withAuthButton(HBox pane, String btnTxt) {
        ButtonHandler btnHandler = new ButtonHandler();
        Button authBtn = new Button(btnTxt);
        authBtn.setOnAction(btnHandler);
        pane.getChildren().add(authBtn);
        return pane;
    }

    private HBox loadMainButtons(boolean isAuthenticated) {
        HBox bottom = new HBox();

        ButtonHandler btnHandler = new ButtonHandler();
        Button resultsButton = new Button("See Results");
        resultsButton.setOnAction(btnHandler);

        Button playButton = new Button(isAuthenticated ? "Play" : "Quick Play");
        playButton.setOnAction(btnHandler);

        bottom.getChildren().addAll(resultsButton, playButton);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(15);

        return bottom;
    }

    private static class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String selectedAction = ((Button) event.getSource()).getText();
            ScreenController sc = ScreenController.getScreenControllerInstance();
            AuthController ac = AuthController.getAuthControllerInstance();

            switch (selectedAction) {
                case "Sign in":
                    sc.activate(Views.LOGIN.name());
                    break;
                case "Sign out":
                    ac.setPlayer(null);
                    break;
                case "See Results":
                    sc.activate(Views.RESULTS.name());
                    break;
                case "Quick Play":
                case "Play":
                    sc.activate(Views.GAME.name());
                    break;
            }
        }
    }
}
