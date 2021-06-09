package com.quiz.app.views;

import com.quiz.app.controllers.ScreenController;
import com.quiz.enums.Views;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import com.quiz.interfaces.BaseView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class HomeView extends BorderPane implements BaseView {
    public HomeView(){
        super();
        getStyleClass().add("home");
        setPadding(new Insets(100, 100, 100, 100));

        Label welcomeMsg = new Label("Welcome to Java Quiz");
        welcomeMsg.getStyleClass().add("welcomeMsg");
        welcomeMsg.setTextFill(Color.web("#f1f1f1"));

        HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        top.getChildren().add(welcomeMsg);

        Image quizLogo = new Image("images/quizlet.png");
        ImageView imgView = new ImageView(quizLogo);
        imgView.setFitHeight(150);
        imgView.setFitWidth(150);
        HBox center = new HBox();
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(50, 0, 0, 0));
        center.getChildren().add(imgView);

        ButtonHandler btnHandler = new ButtonHandler();
        Button loginButton = new Button("Sign in");
        loginButton.setOnAction(btnHandler);
        Button resultsButton = new Button("See Results");
        resultsButton.setOnAction(btnHandler);
        Button quickPlayButton = new Button("Quick Play");
        quickPlayButton.setOnAction(btnHandler);

        HBox bottom = new HBox(loginButton, resultsButton, quickPlayButton);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(15);

        setTop(top);
        setCenter(center);
        setBottom(bottom);
    }

    private static class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String selectedAction = ((Button) event.getSource()).getText();
            ScreenController sc = ScreenController.getScreenControllerInstance();

            switch (selectedAction) {
                case "Sign in":
                    sc.activate(Views.LOGIN.name());
                    break;
                case "See Results":
                    sc.activate(Views.RESULTS.name());
                    break;
                case "Quick Play":
                    sc.activate(Views.GAME.name());
                    break;
            }
        }
    }
}
