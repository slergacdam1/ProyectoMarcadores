package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class controladorNoticias {

    @FXML
    private Button botonMarcadores;

    @FXML
    private Button botonNoticias;

    @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) botonMarcadores.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/marcadores.fxml"));
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

}