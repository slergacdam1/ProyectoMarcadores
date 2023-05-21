package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Partido;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class controladorMarcadores {

    @FXML
    private Label labelEquipoLocal1;


    @FXML
    private Label labelEquipoLocal2;

    @FXML
    private Label labelEquipoLocal3;

    @FXML
    private Label labelEquipoLocal4;

    @FXML
    private Label labelEquipoLocal5;

    @FXML
    private Label labelEquipoLocal6;

    @FXML
    private Label labelEquipoLocal7;

    @FXML
    private Label labelEquipoLocal8;

    @FXML
    private Label labelEquipoLocal9;

    @FXML
    private Label labelEquipoVisitante1;


    @FXML
    private Label labelEquipoVisitante2;

    @FXML
    private Label labelEquipoVisitante3;

    @FXML
    private Label labelEquipoVisitante4;

    @FXML
    private Label labelEquipoVisitante5;


    @FXML
    private Label labelEquipoVisitante6;

    @FXML
    private Label labelEquipoVisitante7;

    @FXML
    private Label labelEquipoVisitante8;

    @FXML
    private Label labelEquipoVisitante9;

    @FXML
    private Label labelResultado1;


    @FXML
    private Label labelResultado2;

    @FXML
    private Label labelResultado3;

    @FXML
    private Label labelResultado4;

    @FXML
    private Label labelResultado5;

    @FXML
    private Label labelResultado6;

    @FXML
    private Label labelResultado7;

    @FXML
    private Label labelResultado8;

    @FXML
    private Label labelResultado9;

    @FXML
    private Label jornada;

    @FXML
    private ScrollBar scroll;
    @FXML
    private Button botonMarcadors;

    @FXML
    private Button botonNoticias;


    public void initialize() {
        List<Partido> partidos = controladorBaseDeDatos.consultarPartido();
        partidos.get(0).getJornada();
        jornada.setText("Jornada " + (partidos.get(0).getJornada()));
    }

    @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) labelEquipoLocal2.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/marcadores.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 600);
            stage.setTitle("Marcadores");
            stage.setScene(scene);
            stage.show();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void scenaNoticias(ActionEvent event) {
        Stage stage = (Stage) labelEquipoLocal2.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/noticias.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 600);
            stage.setTitle("Noticias");
            stage.setScene(scene);
            stage.show();
            controladorNoticias controlador = loader.getController();
            controlador.actualizarNoticia();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void actualizarPartido() {
            List<Partido> partidos = controladorBaseDeDatos.consultarPartido();

            for (int i = 0; i < partidos.size(); i++) {
                Partido partido = partidos.get(i);

                switch (i) {
                    case 0:
                        labelEquipoLocal1.setText(partido.getEquipoLocal());
                        labelEquipoVisitante1.setText(partido.getEquipoVisitante());
                        labelResultado1.setText(partido.getResultado());
                        break;
                    case 1:
                        labelEquipoLocal2.setText(partido.getEquipoLocal());
                        labelEquipoVisitante2.setText(partido.getEquipoVisitante());
                        labelResultado2.setText(partido.getResultado());
                        break;
                    case 2:
                        labelEquipoLocal3.setText(partido.getEquipoLocal());
                        labelEquipoVisitante3.setText(partido.getEquipoVisitante());
                        labelResultado3.setText(partido.getResultado());
                        break;
                    case 3:
                        labelEquipoLocal4.setText(partido.getEquipoLocal());
                        labelEquipoVisitante4.setText(partido.getEquipoVisitante());
                        labelResultado4.setText(partido.getResultado());
                        break;
                    case 4:
                        labelEquipoLocal5.setText(partido.getEquipoLocal());
                        labelEquipoVisitante5.setText(partido.getEquipoVisitante());
                        labelResultado5.setText(partido.getResultado());
                        break;
                    case 5:
                        labelEquipoLocal6.setText(partido.getEquipoLocal());
                        labelEquipoVisitante6.setText(partido.getEquipoVisitante());
                        labelResultado6.setText(partido.getResultado());
                        break;
                    case 6:
                        labelEquipoLocal7.setText(partido.getEquipoLocal());
                        labelEquipoVisitante7.setText(partido.getEquipoVisitante());
                        labelResultado7.setText(partido.getResultado());
                        break;
                    case 7:
                        labelEquipoLocal8.setText(partido.getEquipoLocal());
                        labelEquipoVisitante8.setText(partido.getEquipoVisitante());
                        labelResultado8.setText(partido.getResultado());
                        break;
                    case 8:
                        labelEquipoLocal9.setText(partido.getEquipoLocal());
                        labelEquipoVisitante9.setText(partido.getEquipoVisitante());
                        labelResultado9.setText(partido.getResultado());
                        break;
                }
            }

        }
       }


