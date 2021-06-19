package com.quiz.app.controllers;

import com.quiz.app.views.*;
import com.quiz.enums.Views;
import com.quiz.exceptions.AlreadyInitializedException;
import com.quiz.interfaces.BaseView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Klasa odgovorna za navigaciju kroz aplikaciju. Omogućava povratak na prethodne stranice (ukoliko korisnik ima tu opciju)
 * tako što se sve posećene stranice čuvaju u Stack strukturi u polju history.
 * Ova klasa funkcioniše kao jednostavna singleton klasa, kreira se jednom u toku rada aplikacije i onda se ta
 * instanca (preko polja screenControllerInstance) upotrebljava tamo gde je potrebna.
 */
public class ScreenController {
    /**
     * Instanca ScreenController singleton klase
     */
    private static ScreenController screenControllerInstance = null;
    /**
     * Glavna scena aplikacije
     */
    private final Scene main;
    /**
     * Mapa dostupnih ekrana u aplikaciji
     */
    private Map<String, Pane> screenMap = new HashMap<>();
    /**
     * Istorija ekrana kroz koji je korisnik prošao
     */
    private Stack<Pane> history = new Stack<>();

    /**
     * Podrazumevani konstruktor za ScreenController klasu.
     *
     * @param main - primarna scena aplikacije
     * @throws AlreadyInitializedException - Greška koja se izbacuje ukoliko je ScreenController već inicijalizovan.
     */
    public ScreenController(Scene main) throws AlreadyInitializedException {
        if (screenControllerInstance != null) {
            throw new AlreadyInitializedException();
        }
        this.main = main;
        screenMap.put(Views.HOME.name(), new HomeView());
        screenMap.put(Views.GAME.name(), new GameView());
        screenMap.put(Views.LOGIN.name(), new LoginView());
        screenMap.put(Views.REGISTER.name(), new RegisterView());
        screenMap.put(Views.RESULTS.name(), new ResultsView());
        screenMap.put(Views.ADMIN.name(), new AdminView());
        screenMap.put(Views.RELAX.name(), new RelaxView());
        screenControllerInstance = this;
    }

    /**
     * @param main      - primarna scena aplikacije
     * @param screenMap - Map-a sa predefinisanim ekranima
     * @throws AlreadyInitializedException - Greška koja se izbacuje ukoliko je ScreenController već inicijalizovan.
     */
    public ScreenController(Scene main, Map<String, Pane> screenMap) throws AlreadyInitializedException {
        if (screenControllerInstance != null) {
            throw new AlreadyInitializedException();
        }
        this.screenMap = screenMap;
        this.main = main;
        screenControllerInstance = this;
    }

    /**
     * Statička metoda zadužena za prosledjivanje instance ScreenController klase
     *
     * @return ScreenController singleton instanca ScreenController-a
     */
    public static ScreenController getScreenControllerInstance() {
        return screenControllerInstance;
    }

    /**
     * Metoda za setovanje instance ScreenController klase
     *
     * @param screenControllerInstance - instanca ScreenController klase
     */
    public static void setScreenControllerInstance(ScreenController screenControllerInstance) {
        ScreenController.screenControllerInstance = screenControllerInstance;
    }

    /**
     * @param screenName - ime ekrana koji želimo da nadjemo, koriste se enums.Views.
     * @param <T>        - generički tip klase, koja nasledjuje BorderPane i implementira BaseView,
     *                   ovo su u suštini *View klase iz views/ paketa.
     * @return BorderPane
     */
    public <T extends BorderPane & BaseView> T getScreen(String screenName) {
        return (T) screenMap.get(screenName);
    }

    /**
     * Metoda koja omogućava povratak na prethodni ekran
     */
    public void goBack() {
        history.pop();
        Pane currentScreen = history.peek();
        main.setRoot(currentScreen);
        main.getRoot().getStylesheets().add("styles.css");
    }

    /**
     * Koristi se za prikazivanje ekrana za koje nam nije bitno da li krećemo od početnog stanja ili ne.
     *
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
     *
     * @param view - Jedna od *View klasa iz views/ paketa
     * @param <T>  - Jedna od *View klasa iz views/ paketa
     */
    public <T extends BorderPane & BaseView> void show(T view) {
        view.render();
        history.push(view);
        main.setRoot(view);
        main.getRoot().getStylesheets().add("styles.css");
    }

    /**
     * Getter metoda za čitanje mape ekrana
     *
     * @return screenMap - mapa ekrana
     */
    public Map<String, Pane> getScreenMap() {
        return screenMap;
    }

    /**
     * Setter metoda za setovanje mape ekrana
     *
     * @param screenMap - mapa ekrana
     */
    public void setScreenMap(Map<String, Pane> screenMap) {
        this.screenMap = screenMap;
    }

    /**
     * Getter metoda za čitanje istorije ekrana
     *
     * @return history - trenutni history Stack ekrana
     */
    public Stack<Pane> getHistory() {
        return history;
    }

    /**
     * Setter metoda za setovanje istorije ekrana
     *
     * @param history - history Stack ekrana
     */
    public void setHistory(Stack<Pane> history) {
        this.history = history;
    }
}
