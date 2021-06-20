package com.quiz.app.views;

import com.quiz.app.controllers.ScreenController;
import com.quiz.interfaces.BaseView;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Random;

/**
 * Klasa zadužena za preuzimanje slika sa Getty images sajta, i njihovo prikazivanje na UI
 */
public class RelaxView extends BorderPane implements BaseView {
    /**
     * Maksimalni broj različitih slika koje možemo prikazati na stranici
     */
    private final int maxImgNum = 20;

    /**
     * Podrazumevani konstruktor
     */
    public RelaxView() {
        System.out.println("RelaxView loaded.");
        getStyleClass().add("relax");
        setPadding(new Insets(30, 30, 30, 30));

        HBox top = new HBox();
        top.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Go Back");
        top.getChildren().add(backButton);
        setTop(top);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMinSize(100, 100);
        progressIndicator.setStyle("-fx-progress-color: #161616;");
        HBox center = new HBox(progressIndicator);
        center.setAlignment(Pos.CENTER);
        setCenter(center);

        backButton.setOnAction(e -> {
            ScreenController sc = ScreenController.getScreenControllerInstance();
            setCenter(center);
            sc.goBack();
        });
    }

    /**
     * Overriden metoda, zadužena za renderovanje GUI AdminView-a
     */
    @Override
    public void render() {
        Task<String[]> task = getImagesTask();

        task.setOnSucceeded(t -> {
            String[] imageUrls = task.getValue();
            Random rand = new Random();

            String imageUrl = imageUrls[rand.nextInt(maxImgNum)];

            VBox center = new VBox();
            Image img = new Image(imageUrl);
            ImageView imgView = new ImageView(img);
            imgView.setFitHeight(400);

            Button newImgButton = new Button("Change Image");
            newImgButton.setOnAction(e -> {
                String newImgUrl = imageUrls[rand.nextInt(maxImgNum)];
                Image newImg = new Image(newImgUrl);
                imgView.setImage(newImg);
            });

            center.setAlignment(Pos.TOP_CENTER);
            center.setPadding(new Insets(30, 0, 0, 0));
            center.setSpacing(30);
            center.getChildren().addAll(imgView, newImgButton);
            setCenter(center);
        });

        new Thread(task).start();
    }

    /**
     * Task metoda zadužena za pokretanje Jsoup page crawlera i izvlačenja slika iz img elemenata na stranici
     *
     * @return niz sa img URL
     */
    private Task<String[]> getImagesTask() {
        return new Task<String[]>() {
            @Override
            protected String[] call() {
                int MAX = 100;
                final String dogsImagesURL = "https://www.gettyimages.com/photos/dog-and-puppies";
                String[] imageUrls = new String[maxImgNum];

                try {
                    updateProgress(0, MAX);
                    Thread.sleep(100);
                    Document doc = Jsoup.connect(dogsImagesURL).get();
                    updateProgress(25, MAX);
                    Thread.sleep(100);

                    Elements images = doc.select("img.gallery-mosaic-asset__thumb");

                    for (int i = 0; i < maxImgNum; i++) {
                        Element img = images.get(i);
                        String imgSrc = img.attr("src");

                        if (imgSrc.length() > 0) {
                            imageUrls[i] = imgSrc;
                        }
                    }

                    updateProgress(50, MAX);
                    Thread.sleep(100);

                    updateProgress(75, MAX);
                    Thread.sleep(100);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                updateProgress(MAX, MAX);

                return imageUrls;
            }
        };
    }
}
