package com.example.pantallalog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class ControladorMarcadores {


    @FXML
    private Label equipo1;

    @FXML
    private Label equipo2;

    @FXML
    private Label equipo3;

    @FXML
    private Label equipo4;
 @FXML
    private Label equipo5;

    @FXML
    private Label fecha;

    @FXML
    private ImageView imagen;

    @FXML
    private ImageView imagen3;

    @FXML
    private Label noticia;

    @FXML
    private Label resultado1;

    @FXML
    private Label resultado2;

    @FXML
    private ScrollBar scroll;

    @FXML
    void onSetFecha(MouseEvent event) {
        LocalDate fechaActual = LocalDate.now();
        int valorScrollBar = (int) scroll.getValue();

        if (valorScrollBar < 50) {
            // Retroceder días
            int diasARetroceder = 50 - valorScrollBar;
            LocalDate fechaScrollBar = fechaActual.minusDays(diasARetroceder);
            fecha.setText(fechaScrollBar.toString());
        } else {
            // Avanzar días
            int diasAAvanzar = valorScrollBar - 50;
            LocalDate fechaScrollBar = fechaActual.plusDays(diasAAvanzar);
            fecha.setText(fechaScrollBar.toString());
        }
    }
    @FXML
    void onSetImagen(MouseEvent event) {
        if (equipo3.getText().equals("SEVILLA")){
            Image imagen6 = new Image(getClass().getResourceAsStream("/com/example/pantallalog/OSASUNA.JPG"));
            imagen3.setImage(imagen6);
            System.out.println(imagen6.toString());
        }
    }


}
