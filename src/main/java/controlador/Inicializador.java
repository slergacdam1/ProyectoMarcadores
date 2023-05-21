package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Inicializador extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inicializador.class.getResource("/vista/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setResizable(false);
        stage.setTitle("Inicio de sesion");
        stage.setScene(scene);
        stage.show();
        controladorBaseDeDatos.generarBaseDeDatosCompleta();
    }

    public static void main(String[] args) {
        launch();
    }
}