package com.example.pantallalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    @FXML
    private TextField contraseña;

    @FXML
    private TextField usuario;

    @FXML
    void onClick(ActionEvent event) {
        System.out.println(usuario.getText() + contraseña.getText()+ "\n" + "Has iniciado sesion");
        Stage stage = (Stage) usuario.getScene().getWindow();
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
