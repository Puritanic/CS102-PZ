package com.quiz.app.views;

import com.quiz.app.controllers.PlayerController;
import com.quiz.app.controllers.ScreenController;
import com.quiz.enums.Views;
import com.quiz.exceptions.AuthException;
import com.quiz.interfaces.BaseView;
import com.quiz.util.ViewUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Klasa zadužena za prikazivanje UI sa formom za login postojećeg korisnika.
 */
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
        render();
    }

    @Override
    public void render() {
        GridPane center = ViewUtils.createFormPane();
        addUIControls(center);

        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER_RIGHT);
        Button registerButton = new Button("Register");
        bottom.getChildren().add(registerButton);

        registerButton.setOnAction(e -> {
            ScreenController sc = ScreenController.getScreenControllerInstance();
            sc.show(sc.getScreen(Views.REGISTER.name()));
        });

        setCenter(center);
        setBottom(bottom);
    }

    private void addUIControls(GridPane gridPane) {
        Label headerLabel = new Label("Login");
        headerLabel.getStyleClass().add("header");
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        Label emailLabel = new Label("Email: ");
        gridPane.add(emailLabel, 0, 1);

        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 1);

        Label passwordLabel = new Label("Password: ");
        gridPane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 2);

        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 3, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(event -> {
            if(emailField.getText().isEmpty()) {
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Form Error!", "Please enter your email id");
                return;
            }
            if(passwordField.getText().isEmpty()) {
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Form Error!", "Please enter a password");
                return;
            }

            PlayerController pc = new PlayerController();
            try {
                pc.loginPlayer(emailField.getText(), passwordField.getText());
            } catch (AuthException ex){
                ex.printStackTrace();
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Login Error!", "Bad credentials. Please check your email and password");
            } catch (Exception ex){
                ex.printStackTrace();
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Login Error!", "Something went wrong. Please try again.");
            }
        });
    }
}
