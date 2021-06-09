package com.quiz.app.views;

import com.quiz.interfaces.BaseView;
import javafx.scene.layout.BorderPane;

public class GameView extends BorderPane implements BaseView {
    public GameView(){
        System.out.println("GameView loaded");
    }
}
