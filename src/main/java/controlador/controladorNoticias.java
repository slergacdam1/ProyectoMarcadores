package controlador;

import com.example.pantallalog.ControladorMarcadores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;

public class controladorNoticias {

    @FXML
    private Button botonMarcadores;

    @FXML
    private Button botonNoticias;

    @FXML
    private Label noticia1;

    @FXML
    private Label noticia2;

    @FXML
    private Label noticia3;

    @FXML
    private Label noticia4;

    @FXML
    private Label noticia5;

    @FXML
    private Label noticia6;
    @FXML
    private ImageView imagen1;

    @FXML
    private ImageView imagen2;

    @FXML
    private ImageView imagen3;

    @FXML
    private ImageView imagen4;

    @FXML
    private ImageView imagen5;

    @FXML
    private ImageView imagen6;

  /*  @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) botonMarcadores.getScene().getWindow();
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/


    @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) botonMarcadores.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/marcadores.fxml"));

        try {
            Scene scene = new Scene(loader.load(), 600, 400);
            stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
            stage.setScene(scene);
            stage.show();

            ControladorMarcadores controller = loader.getController();
            controller.initialize();

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
            Scene scene = new Scene(loader.load(), 800, 500);
            stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void onLeerNoticia(MouseEvent event) {

        Stage stage = (Stage) botonNoticias.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/articuloNoticia.fxml"));
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
    void actualizarNoticia(MouseEvent event) {
        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").timeout(6000).get();
            Elements contenedor = documento.select(".ue-c-cover-content__main");
            Elements noticias = contenedor.select(".ue-c-cover-content__headline");
            int contador = 0;

            for (int i = 0; i < noticias.size(); i++) {
                if (contador <= 6) {
                    Element noticia = noticias.get(i);
                    String titulo = noticia.text();

                    // Obtener la imagen de la noticia actual
                    Element imagenElement = noticia.selectFirst("img");
                    String urlImagen = "";
                    if (imagenElement != null) {
                        urlImagen = imagenElement.attr("abs:src");
                    }

                    // Asignar la noticia y la imagen al label correspondiente
                    switch (contador) {
                        case 1:
                            noticia1.setText(titulo);
                            // Asumiendo que tienes un ImageView llamado "imagen1" asociado a noticia1
                            imagen1.setImage(new Image(urlImagen));
                            break;
                        case 2:
                            noticia2.setText(titulo);
                            // Asumiendo que tienes un ImageView llamado "imagen2" asociado a noticia2
                            imagen2.setImage(new Image(urlImagen));
                            break;
                        case 3:
                            noticia3.setText(titulo);
                            // Asumiendo que tienes un ImageView llamado "imagen3" asociado a noticia3
                            imagen3.setImage(new Image(urlImagen));
                            break;
                        case 4:
                            noticia4.setText(titulo);
                            // Asumiendo que tienes un ImageView llamado "imagen4" asociado a noticia4
                            imagen4.setImage(new Image(urlImagen));
                            break;
                        case 5:
                            noticia5.setText(titulo);
                            // Asumiendo que tienes un ImageView llamado "imagen5" asociado a noticia5
                            imagen5.setImage(new Image(urlImagen));
                            break;
                        case 6:
                            noticia6.setText(titulo);
                            // Asumiendo que tienes un ImageView llamado "imagen6" asociado a noticia6
                            imagen6.setImage(new Image(urlImagen));
                            break;
                    }

                    contador++;
                } else {
                    break; // Salir del bucle si ya se han asignado las 6 noticias
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void actualizarNoticia1(MouseEvent event) {
        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").timeout(6000).get();
            Elements contenedor = documento.select(".ue-c-cover-content__main");
            Elements titulos = contenedor.select("h2");
            Elements imagenes = contenedor.select(".ue-c-cover-content__media");
            int contador = 0;
            for (Element titulo : titulos) {
                if (contador <= 6) {
                    String noticia = titulo.text();

                    // Asignar la noticia al label correspondiente
                    switch (contador) {
                        case 1:
                            noticia1.setText(noticia);
                            break;
                        case 2:
                            noticia2.setText(noticia);
                            break;
                        case 3:
                            noticia3.setText(noticia);
                            break;
                        case 4:
                            noticia4.setText(noticia);
                            break;
                        case 5:
                            noticia5.setText(noticia);
                            break;
                        case 6:
                            noticia6.setText(noticia);
                            break;
                    }

                    contador++;
                } else {
                    break; // Salir del bucle si ya se han asignado las 6 noticias
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

}