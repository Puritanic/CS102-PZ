package com.quiz.util;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;

/**
 * Util klasa sa funkcijama koje se koriste u BaseView klasama. Moguć je samo statički pristup.
 */
public final class ViewUtils {
    private ViewUtils(){}

    /**
     * Kreira GridPane za prikaz forme na LoginView i RegisterView stranama.
     * @return GridPane
     */
    public static GridPane createFormPane() {
        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(50, 100, 50, 100));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // konfiguracija za prvu kolonu GridPane-a
        ColumnConstraints columnOneConstraints = new ColumnConstraints(130, 130, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // konfiguracija za drugu kolonu GridPane-a
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    /**
     * @param alertType Vrsta Alert dijaloga
     * @param owner - Glavni stage gde treba da bude prikazan
     * @param title - Naslov Aler Box-a
     * @param message - Poruka Alert Box-a
     */
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
