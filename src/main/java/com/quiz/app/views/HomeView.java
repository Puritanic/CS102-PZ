package com.quiz.app.views;

import com.quiz.app.controllers.AuthController;
import com.quiz.app.controllers.ScreenController;
import com.quiz.entities.Player;
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

/**
 * Klasa zadužena za prikazivanje početnog ekrana aplikacije.
 */
public class HomeView extends BorderPane implements BaseView {
    /**
     * Podrazumevani konstruktor
     */
    public HomeView() {
        super();
        getStyleClass().add("home");
        setPadding(new Insets(100, 100, 100, 100));

        Label welcomeMsg = new Label("Welcome to Java Quiz");
        welcomeMsg.getStyleClass().add("title");

        AuthController ac = AuthController.getAuthControllerInstance();
        setButtons(ac.getPlayer());

        // Listener potreban za osmatranje promena u auth state-u AuthControllera,
        // da bi početna strana prikazala odgovarajući UI u zavisnosti da li je korisnik ulogvan ili ne
        AuthController.getAuthControllerInstance().playerProperty().addListener((obs, oldPlayer, newPlayer) ->
                setButtons(newPlayer)
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
    public void render() {
    }

    /**
     * Metoda odgovorna za prikazivanje odgovarajućeg seta buttons-a.
     *
     * @param player Player, igrač
     */
    private void setButtons(Player player) {
        boolean isAuthenticated = (player != null);
        if (isAuthenticated) {
            boolean isAdmin = player.isAdmin();

            if (isAdmin) {
                setBottom(withAuthButton(
                        withAuthButton(loadMainButtons(true), "Admin")
                        , "Sign out"));
            } else {
                setBottom(withAuthButton(loadMainButtons(true), "Sign out"));
            }
        } else {
            setBottom(withAuthButton(loadMainButtons(false), "Sign in"));
        }
    }

    /**
     * "Dekorator" metoda koja dodaje dugme sa btnTxt tekstom u prosledjeni node (pane)
     *
     * @param pane   - node u koji treba da se ubaci dugme
     * @param btnTxt - tekst koji će biti prikazan na dugmetu
     * @return HBox
     */
    private HBox withAuthButton(HBox pane, String btnTxt) {
        ButtonHandler btnHandler = new ButtonHandler();
        Button authBtn = new Button(btnTxt);
        authBtn.setOnAction(btnHandler);
        pane.getChildren().add(authBtn);
        return pane;
    }

    /**
     * Metoda odgovorna za prikazivanje glavnog seta buttons-a na početnoj strani
     *
     * @param isAuthenticated - auth state igrača
     * @return HBox
     */
    private HBox loadMainButtons(boolean isAuthenticated) {
        HBox bottom = new HBox();

        ButtonHandler btnHandler = new ButtonHandler();
        Button resultsButton = new Button("See Results");
        resultsButton.setOnAction(btnHandler);

        Button playButton = new Button(isAuthenticated ? "Play" : "Quick Play");
        playButton.setOnAction(btnHandler);

        Button relaxButton = new Button("Relax");
        relaxButton.setOnAction(btnHandler);

        bottom.getChildren().addAll(resultsButton, playButton, relaxButton);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(15);

        return bottom;
    }

    /**
     * Unutrašnja klasa koja je odgovorna za obradu "klik" dogadjaja dugmadi sa početne strane
     */
    private static class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String selectedAction = ((Button) event.getSource()).getText();
            ScreenController sc = ScreenController.getScreenControllerInstance();
            AuthController ac = AuthController.getAuthControllerInstance();

            switch (selectedAction) {
                case "Sign in":
                    sc.show(sc.getScreen(Views.LOGIN.name()));
                    break;
                case "Sign out":
                    ac.setPlayer(null);
                    break;
                case "See Results":
                    sc.show(sc.getScreen(Views.RESULTS.name()));
                    break;
                case "Admin":
                    sc.show(sc.getScreen(Views.ADMIN.name()));
                    break;
                case "Relax":
                    sc.show(sc.getScreen(Views.RELAX.name()));
                    break;
                case "Quick Play":
                case "Play":
                    sc.show(sc.getScreen(Views.GAME.name()));
                    break;
            }
        }
    }
}
