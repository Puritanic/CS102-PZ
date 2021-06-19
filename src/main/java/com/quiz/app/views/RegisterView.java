package com.quiz.app.views;

import com.quiz.app.controllers.PlayerController;
import com.quiz.app.controllers.ScreenController;
import com.quiz.interfaces.BaseView;
import com.quiz.util.ViewUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Klasa zadužena za prikazivanje UI sa formom za registraciju novog korisnika.
 */
public class RegisterView extends BorderPane implements BaseView {
    /**
     * Podrazumevani konstruktor
     */
    public RegisterView() {
        System.out.println("RegisterView loaded.");
        getStyleClass().add("register");
        setPadding(new Insets(30, 30, 30, 30));

        HBox top = new HBox();
        top.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Go Back");
        top.getChildren().add(backButton);
        setTop(top);

        backButton.setOnAction(e -> {
            ScreenController sc = ScreenController.getScreenControllerInstance();
            sc.goBack();
        });
        render();
    }

    /**
     * Overriden metoda, zadužena za renderovanje GUI AdminView-a
     */
    @Override
    public void render() {
        GridPane center = ViewUtils.createFormPane();
        addUIControls(center);
        setCenter(center);
    }

    /**
     * Metoda zadužena za kreiranje i postavljanje kontrolnih elemenata na UI
     *
     * @param gridPane okno rasporeda
     */
    private void addUIControls(GridPane gridPane) {
        Label headerLabel = new Label("Registration");
        headerLabel.getStyleClass().add("header");
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        Label nameLabel = new Label("Username: ");
        gridPane.add(nameLabel, 0, 1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1, 1);

        Label emailLabel = new Label("Email: ");
        gridPane.add(emailLabel, 0, 2);

        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 2);

        Label passwordLabel = new Label("Password: ");
        gridPane.add(passwordLabel, 0, 3);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        Label setAdminLabel = new Label("Set Admin role: ");
        gridPane.add(setAdminLabel, 0, 4);
        CheckBox isAdmin = new CheckBox();
        gridPane.add(isAdmin, 1, 4);

        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        submitButton.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Form Error!", "Please enter your name");
                return;
            }
            if (emailField.getText().isEmpty()) {
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Form Error!", "Please enter your email");
                return;
            }
            if (passwordField.getText().isEmpty()) {
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Form Error!", "Please enter a password");
                return;
            }

            ViewUtils.showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(),
                    "Registration Successful!", "Welcome " + nameField.getText());

            PlayerController pc = new PlayerController();

            try {
                pc.registerPlayer(emailField.getText(), nameField.getText(), passwordField.getText(), isAdmin.isSelected());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
