package com.quiz.app.components;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String message){
        Stage window = new Stage();
        // Block user's interaction with other windows until this modal is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(250);

        Label label = new Label(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(25);
        layout.getChildren().addAll(label, closeButton);

        Scene scene = new Scene(layout, 250, 250);
        window.setScene(scene);
        window.showAndWait();
    }
}
