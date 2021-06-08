package quiz.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quiz.app.views.HomeView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Java kviz");

        primaryStage.setScene(new Scene(new HomeView(), 800, 650));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
