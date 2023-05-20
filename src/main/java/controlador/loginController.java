package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    @FXML
    private TextField contraseña;

    @FXML
    private TextField usuario;
    @FXML
    private Label credenciales;

    @FXML
    void onClick(ActionEvent event) {
        if (usuario.getText().equals("") || contraseña.getText().equals("")){
            credenciales.setOpacity(1.0);
        }
        else {
            System.out.println(usuario.getText() + contraseña.getText() + "\n" + "Has iniciado sesion");
            Stage stage = (Stage) usuario.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/noticias.fxml"));
            // Crear una instancia de la nueva ventana
            try {
                Scene scene = new Scene(loader.load(), 600, 600);
                stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
                stage.setScene(scene);
                stage.show();


                controladorNoticias controlador = loader.getController();
                controlador.actualizarNoticia();
                controlador.asignarId();



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
