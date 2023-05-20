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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        if (equipo3.getText().equals("SEVILLA")) {
            Image imagen6 = new Image(getClass().getResourceAsStream("/escudos/OSASUNA.JPG"));
            imagen3.setImage(imagen6);
        }
    }

    @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) equipo3.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/marcadores.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 600);
            stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
            stage.setScene(scene);
            stage.show();
            stage.setOnShown(windowEvent -> {
                Label fechaLabel = (Label) scene.lookup("#fecha");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaActual = LocalDate.now();
                String fechaFormateada = fechaActual.format(formatter);
                fechaLabel.setText(fechaFormateada);
            });


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void scenaNoticias(ActionEvent event) {
        Stage stage = (Stage) equipo1.getScene().getWindow();
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

    @FXML
    void actualizarPartido(MouseEvent event) {
        try {


            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division/calendario.html").get();

            Elements contenedor = documento.select(".jor agendas");
            Elements locales = documento.select("local");
            Elements equipoL = locales.select("span");/*
            Elements table = contenedor.select("table");
            Elements local = table.select("td").*/


            // Creamos una sentencia SQL preparada para realizar inserciones

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division/calendario.html").get();

            Elements contenedor = documento.select("div.jornada.calendarioInternacional");

            int numeroJornada = 1; // Inicializamos el número de la jornada

            for (Element jornadaDiv : contenedor) {
                Element jornadaElement = jornadaDiv.selectFirst("div.cal-agendas.calendario");
                if (jornadaElement != null) {
                    String jornada = "Jornada " + numeroJornada; // Agregamos el número de la jornada
                    System.out.println(jornada);

                    Elements filas = jornadaElement.select("tr");

                    for (Element fila : filas) {
                        Element local = fila.selectFirst("td.local span");
                        Element visitante = fila.selectFirst("td.visitante span");
                        Element resultado = fila.selectFirst("td.resultado span");

                        if (local != null && visitante != null && resultado != null) {
                            String nombreLocal = local.text();
                            String nombreVisitante = visitante.text();
                            String resultadoPartido = resultado.text();

                            System.out.println("Local: " + nombreLocal);
                            System.out.println("Visitante: " + nombreVisitante);
                            System.out.println("Resultado: " + resultadoPartido);
                            System.out.println("-----------------------");
                        }
                    }

                    numeroJornada++; // Incrementamos el número de la jornada
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
