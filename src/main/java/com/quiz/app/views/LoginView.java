package com.quiz.app.views;

import com.quiz.app.controllers.ScreenController;
import com.quiz.interfaces.BaseView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class LoginView extends BorderPane implements BaseView {
    public LoginView(){
        System.out.println("LoginView loaded");
        getStyleClass().add("login");
        setPadding(new Insets(30, 30, 30, 30));

        HBox top = new HBox();
        top.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Go Back");
        top.getChildren().add(backButton);

        backButton.setOnAction(e -> {
            ScreenController sc = ScreenController.getScreenControllerInstance();
            sc.goBack();
        });

        setTop(top);
    }
}
