package com.example.pantallalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField contraseña;

    @FXML
    private TextField usuario;

    @FXML
    void onClick(ActionEvent event) {
        System.out.println(usuario.getText() + contraseña.getText()+ "\n" + "Has iniciado sesion");
    }

}
