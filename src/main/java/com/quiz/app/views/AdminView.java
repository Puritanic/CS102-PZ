package com.quiz.app.views;

import com.quiz.app.controllers.QuestionController;
import com.quiz.app.controllers.ScreenController;
import com.quiz.interfaces.BaseView;
import com.quiz.models.NumericInput;
import com.quiz.util.ViewUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Klasa zadužena za prikaz Admin UI gde admin korisnici mogu da unose nova pitanja u bazu podataka.
 */
public class AdminView extends BorderPane implements BaseView {

    public AdminView() {
        System.out.println("AddQuestion loaded.");
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

    @Override
    public void render() {
        GridPane center = ViewUtils.createFormPane();
        addUIControls(center);
        setCenter(center);
    }

    private void addUIControls(GridPane gridPane) {
        Label headerLabel = new Label("Add new question");
        headerLabel.getStyleClass().add("header");
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        TextArea question = new TextArea();
        question.setPromptText("Enter your question here");
        gridPane.add(question, 0, 1, 2, 1);

        TextArea a1 = new TextArea();
        a1.setPromptText("Enter the first answer here");
        gridPane.add(a1, 0, 2, 2, 1);

        TextArea a2 = new TextArea();
        a2.setPromptText("Enter the second answer here");
        gridPane.add(a2, 0, 3, 2, 1);

        TextArea a3 = new TextArea();
        a3.setPromptText("Enter the third answer here");
        gridPane.add(a3, 0, 4, 2, 1);

        Label idxLabel = new Label("Correct answer idx: ");
        idxLabel.setMinWidth(165);
        gridPane.add(idxLabel, 0, 5);
        NumericInput nInput = new NumericInput("", 0, 2);
        nInput.setPromptText("In range of 0 to 2");
        nInput.setMaxWidth(165);
        gridPane.add(nInput, 1, 5);

        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 6, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        submitButton.setOnAction(event -> {
            if (question.getText().isEmpty()) {
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Form Error!", "Please enter the question");
                return;
            }
            if (a1.getText().isEmpty() || a2.getText().isEmpty() || a3.getText().isEmpty()) {
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Form Error!", "Question must have 3 answers");
                return;
            }
            if (nInput.getText().isEmpty()) {
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Form Error!", "Please enter a correct answer index number");
                return;
            }

            QuestionController qc = new QuestionController();
            try {
                qc.saveQuestion(question.getText(), a1.getText(), a2.getText(),
                        a3.getText(), Integer.parseInt(nInput.getText()));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ViewUtils.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Login Error!", "Something went wrong. Please try again.");
            } finally {
                // Nakon što je pitanje poslato na skladištenje u bazu podataka, resetujemo formu
                question.clear();
                a1.clear();
                a2.clear();
                a3.clear();
                nInput.clear();
            }
        });
    }
}
