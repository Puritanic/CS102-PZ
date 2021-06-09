package com.quiz.app.views;

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

        String imgURI = "images/quizlet.png";
        Image image3 = new Image(imgURI);
        System.out.println(image3);

        ImageView imgView = new ImageView(image3);
        imgView.setFitHeight(150);
        imgView.setFitWidth(150);
        HBox center = new HBox();
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(50, 0, 0, 0));
        center.getChildren().add(imgView);

        Button loginButton = new Button("Sign in");
        Button resultsButton = new Button("See Results");
        Button quickPlayButton = new Button("Quick Play");

        HBox bottom = new HBox(loginButton, resultsButton, quickPlayButton);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(15);

        setTop(top);
        setCenter(center);
        setBottom(bottom);
    }
}
