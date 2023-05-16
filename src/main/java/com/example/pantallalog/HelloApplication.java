package com.example.pantallalog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/vista/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

       /* stage.setOnShown(event -> {
            Label fechaLabel = (Label) scene.lookup("#fecha");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaActual = LocalDate.now();
            String fechaFormateada = fechaActual.format(formatter);
            fechaLabel.setText(fechaFormateada);
        });*/

       /* stage.setOnShown(event -> {
            // Aquí se muestra la noticia en el Label cuando se abre la ventana
            String url = "https://www.marca.com/futbol/liga-francesa/2023/05/08/6458d643268e3edc3f8b45b9.html";
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String headline = doc.selectFirst("h1.headline").text();
            String body = doc.selectFirst("div.article-body").text();

            Label noticiaLabel = new Label(headline + "\n\n" + body);
        });*/


        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}