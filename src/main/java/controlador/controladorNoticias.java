package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Noticia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;

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
    private ArrayList<Noticia> noticias;




    @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) botonMarcadores.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/marcadores.fxml"));


        try {
            Scene scene = new Scene(loader.load(), 600, 600);
            stage.setTitle("Marcadores");
            stage.setScene(scene);
            stage.show();

            controladorMarcadores controller = loader.getController();
            controller.initialize();
            controller.actualizarPartido();

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
    void onLeerNoticia(MouseEvent event) {

        Stage stage = (Stage) botonNoticias.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/articuloNoticia.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 600);
            stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
            controladorArticuloNoticias controladorArticuloNoticias = loader.getController();
            controladorArticuloNoticias.mostrarContenido();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void actualizarNoticia() {

        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").get();
            Elements contenedor = documento.select(".ue-c-cover-content__main");
            Elements titulos = contenedor.select("h2");
            Elements figureImagenes = documento.select(".ue-c-cover-content__image");

            int contador = 0;
            for (Element titulo : titulos) {
                if (contador < 6) {
                    String noticia = titulo.text();
                    String link = figureImagenes.get(contador).select("img").attr("src");
                    ImageView imageView = new ImageView(link);
                    switch (contador) {
                        case 0:
                            noticia1.setText(noticia);
                            imagen1.setImage(imageView.getImage());
                            break;
                        case 1:
                            noticia2.setText(noticia);
                            imagen2.setImage(imageView.getImage());
                            break;
                        case 2:
                            noticia3.setText(noticia);
                            imagen3.setImage(imageView.getImage());
                            break;
                        case 3:
                            noticia4.setText(noticia);
                            imagen4.setImage(imageView.getImage());
                            break;
                        case 4:
                            noticia5.setText(noticia);
                            imagen5.setImage(imageView.getImage());
                            break;
                        case 5:
                            noticia6.setText(noticia);
                            imagen6.setImage(imageView.getImage());
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





}