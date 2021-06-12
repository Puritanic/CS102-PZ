package com.quiz.app.controllers;

import com.quiz.app.views.*;
import com.quiz.enums.Views;
import com.quiz.interfaces.BaseView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Stack;

/**
 * Klasa odgovorna za navigaciju kroz aplikaciju. Omogućava povratak na prethodne stranice (ukoliko korisnik ima tu opciju)
 * tako što se sve posećene stranice čuvaju u Stack strukturi u polju history.
 * Ova klasa funkcioniše kao jednostavna singleton klasa, kreira se jednom u toku rada aplikacije i onda se ta
 * instanca (preko polja screenControllerInstance) upotrebljava tamo gde je potrebna.
 */
public class ScreenController {
    private static ScreenController screenControllerInstance = null;
    private final HashMap<String, Pane> screenMap = new HashMap<>();
    private final Scene main;
    private final Stack<Pane> history = new Stack<>();

    public ScreenController(Scene main) {
        this.main = main;
        screenMap.put(Views.HOME.name(), new HomeView());
        screenMap.put(Views.GAME.name(), new GameView());
        screenMap.put(Views.LOGIN.name(), new LoginView());
        screenMap.put(Views.REGISTER.name(), new RegisterView());
        screenMap.put(Views.RESULTS.name(), new ResultsView());
        screenControllerInstance = this;
    }

    /**
     * Statička metoda zadužena za prosledjivanje instance ScreenController klase
     * @return ScreenController singleton instanca ScreenController-a
     */
    public static ScreenController getScreenControllerInstance() {
        return screenControllerInstance;
    }

    public static void setScreenControllerInstance(ScreenController screenControllerInstance) {
        ScreenController.screenControllerInstance = screenControllerInstance;
    }

    /**
     * @param screenName - ime ekrana koji želimo da nadjemo, koriste se enums.Views.
     * @param <T> - generički tip klase, koja nasledjuje BorderPane i implementira BaseView,
     *           ovo su u suštini *View klase iz views/ paketa.
     * @return
     */
    public <T extends BorderPane & BaseView> T getScreen(String screenName){
        return (T) screenMap.get(screenName);
    }

    /**
     * Metoda koja omogućava povratak na prethodni ekran
     */
    public void goBack(){
        history.pop();
        Pane currentScreen = history.peek();
        main.setRoot(currentScreen);
        main.getRoot().getStylesheets().add("styles.css");
    }

    /**
     * Koristi se za prikazivanje ekrana za koje nam nije bitno da li krećemo od početnog stanja ili ne.
     * @param name - ime ekrana koji želimo da prikažemo
     */
    public void show(String name) {
        Pane currentScreen = screenMap.get(name);
        history.push(currentScreen);
        main.setRoot(currentScreen);
        main.getRoot().getStylesheets().add("styles.css");
    }

    /**
     * Koristi se za prikazivanje ekrana za koje nam jebitno da krećemo sa svežim UI, što se postiže zvanjem
     * render() metode koja kreira UI ekrana sa novim podacima i novim state-om
     * @param view - Jedna od *View klasa iz views/ paketa
     * @param <T> - Jedna od *View klasa iz views/ paketa
     */
    public <T extends BorderPane & BaseView> void show(T view) {
        view.render();
        history.push(view);
        main.setRoot(view);
        main.getRoot().getStylesheets().add("styles.css");
    }
}
