package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private Button botonMarcadors;

    @FXML
    private Button botonNoticias;


    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaActual = LocalDate.now();
        String fechaFormateada = fechaActual.format(formatter);
        fecha.setText(fechaFormateada);
    }

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
        }
    }

    @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) equipo3.getScene().getWindow();
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
            /* stage.setOnShown(event -> {
            Label fechaLabel = (Label) scene.lookup("#fecha");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaActual = LocalDate.now();
            String fechaFormateada = fechaActual.format(formatter);
            fechaLabel.setText(fechaFormateada);
        });*/

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void scenaNoticias(ActionEvent event) {
        Stage stage = (Stage) equipo1.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/vista/noticias.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 400);
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
