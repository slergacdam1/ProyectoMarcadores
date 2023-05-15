package controlador;

import com.example.pantallalog.ControladorMarcadores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Elements;



import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class controladorNoticias {

    @FXML
    private Button botonMarcadores;

    @FXML
    private Button botonNoticias;

  /*  @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) botonMarcadores.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/marcadores.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 400);
            stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
            stage.setScene(scene);
            stage.show();
            stage.setOnShown(windowEvent ->{
                Label fechaLabel = (Label) scene.lookup("#fecha");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaActual = LocalDate.now();
                String fechaFormateada = fechaActual.format(formatter);
                fechaLabel.setText(fechaFormateada);
            } );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/


    @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) botonMarcadores.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/marcadores.fxml"));

        try {
            Scene scene = new Scene(loader.load(), 600, 400);
            stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
            stage.setScene(scene);
            stage.show();

            ControladorMarcadores controller = loader.getController();
            controller.initialize();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    void scenaNoticias(ActionEvent event) {
        Stage stage = (Stage) botonNoticias.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/noticias.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 400);
            stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void onLeerNoticia(MouseEvent event) {

        Stage stage = (Stage) botonNoticias.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/articuloNoticia.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 400);
            stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            // Obtener el Documento HTML de la página web
            Document document = (Document) Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").get();

            // Buscar el div principal de la noticia
            Element divNoticia = document.getElementById("div.ue-c-cover-content__main");

            // Obtener el título de la noticia (contenido del h2)
            Element h2Titulo = divNoticia.sele("h2");
            String tituloNoticia = h2Titulo.text();
            System.out.println("Título de la noticia: " + tituloNoticia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}