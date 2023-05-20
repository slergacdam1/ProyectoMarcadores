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
import modelo.Partido;
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
import java.util.List;

public class ControladorMarcadores {
    private ControladorBaseDeDatos baseDeDatos;


    @FXML
    private Label labelEquipoLocal1;

    @FXML
    private Label labelEquipoLocal10;

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
    private Label labelEquipoVisitante10;

    @FXML
    private Label labelEquipoVisitante2;

    @FXML
    private Label labelEquipoVisitante3;

    @FXML
    private Label labelEquipoVisitante4;

    @FXML
    private Label labelEquipoVisitante5;

    @FXML
    private Label labelEquipoVisitante51;

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
    private Label labelResultado10;

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
    private Label fecha;

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
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) labelEquipoLocal2.getScene().getWindow();
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
        Stage stage = (Stage) labelEquipoLocal2.getScene().getWindow();
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
    void actualizarPartido() {

        try {
            List<Partido> partidos = ControladorBaseDeDatos.consultarPartido();

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
                        case 2:
                        labelEquipoLocal3.setText(partido.getEquipoLocal());
                        labelEquipoVisitante3.setText(partido.getEquipoVisitante());
                        labelResultado3.setText(partido.getResultado());
                        case 3:
                        labelEquipoLocal4.setText(partido.getEquipoLocal());
                        labelEquipoVisitante4.setText(partido.getEquipoVisitante());
                        labelResultado4.setText(partido.getResultado());
                        case 4:
                        labelEquipoLocal5.setText(partido.getEquipoLocal());
                        labelEquipoVisitante5.setText(partido.getEquipoVisitante());
                        labelResultado5.setText(partido.getResultado());
                        case 5:
                        labelEquipoLocal6.setText(partido.getEquipoLocal());
                        labelEquipoVisitante6.setText(partido.getEquipoVisitante());
                        labelResultado6.setText(partido.getResultado());
                        case 6:
                        labelEquipoLocal7.setText(partido.getEquipoLocal());
                        labelEquipoVisitante7.setText(partido.getEquipoVisitante());
                        labelResultado7.setText(partido.getResultado());

                        case 7:
                        labelEquipoLocal8.setText(partido.getEquipoLocal());
                        labelEquipoVisitante8.setText(partido.getEquipoVisitante());
                        labelResultado8.setText(partido.getResultado());

                        case 8:
                        labelEquipoLocal9.setText(partido.getEquipoLocal());
                        labelEquipoVisitante9.setText(partido.getEquipoVisitante());
                        labelResultado9.setText(partido.getResultado());


                    // Resto de los casos para los demás labels
                }
            }

        } finally {

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
                    String jornada = "Jornada: " + numeroJornada; // Agregamos el número de la jornada
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
