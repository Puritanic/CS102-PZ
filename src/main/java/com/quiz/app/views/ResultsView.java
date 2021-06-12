package com.quiz.app.views;

import com.quiz.app.controllers.PlayerController;
import com.quiz.app.controllers.ScreenController;
import com.quiz.entities.Player;
import com.quiz.interfaces.BaseView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Klasa zadužena za prikazivanje UI sa tabelom rezultata.
 */
public class ResultsView extends BorderPane implements BaseView {
    private final TableView<Player> table = new TableView<>();

    public ResultsView(){
        System.out.println("ResultsView loaded.");
        getStyleClass().add("results");
        setPadding(new Insets(30, 30, 30, 30));

        HBox top = new HBox();
        top.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Go Back");
        top.getChildren().add(backButton);

        backButton.setOnAction(e -> {
            ScreenController sc = ScreenController.getScreenControllerInstance();
            sc.goBack();
        });

        StackPane center = new StackPane();
        center.setPadding(new Insets(50, 20, 50, 20));
        center.getChildren().add(table);

        setTop(top);
        setCenter(center);
        render();
    }

    @Override
    public void render() {
        populateTable();
    }

    /**
     * Metoda zadužena za popunjavanje tabele korisničkim podacima.
     */
    public void populateTable() {
        PlayerController pc = new PlayerController();
        table.getItems().clear();
        table.setStyle("-fx-font-weight:normal; -fx-color: #f0f0f0; -fx-font-size:11; -fx-font-family: Verdana;");
        table.setItems(pc.getPlayerData());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Player, Integer> playerIdCol = new TableColumn<>("ID");
        playerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Player, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<Player, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Player, Integer> pointsCol = new TableColumn<>("Total Points");
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        TableColumn<Player, Integer> gamesCol = new TableColumn<>("Finished Games");
        gamesCol.setCellValueFactory(new PropertyValueFactory<>("finishedGames"));

        table.getColumns().setAll(playerIdCol, usernameCol, emailCol, pointsCol, gamesCol);
    }
}
