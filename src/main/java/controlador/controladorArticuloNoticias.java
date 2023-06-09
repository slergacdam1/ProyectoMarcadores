package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Noticia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class controladorArticuloNoticias {

    @FXML
    private Button botonMarcadores;

    @FXML
    private Button botonNoticias;

    @FXML
    private Label texto;

    @FXML
    private Label titulo;
    @FXML
    private ImageView imagenNoticia;




    @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) titulo.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/marcadores.fxml"));


        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 600);
            stage.setTitle("Marcadores");
            stage.setScene(scene);
            stage.show();
            controladorMarcadores controladorMarcadores = loader.getController();
            controladorMarcadores.initialize();
            controladorMarcadores.actualizarPartido();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void scenaNoticias(ActionEvent event) {
        Stage stage = (Stage) titulo.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/vista/noticias.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 600);
            stage.setTitle("Noticias");
            stage.setScene(scene);
            stage.show();
            controladorNoticias controladorNoticias = loader.getController();
            controladorNoticias.actualizarNoticia();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void obtenerContenido(){
        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").get();
            Elements contenedor = documento.select(".ue-c-cover-content__main");
            Elements titulos = contenedor.select("h2");
            Elements enlaces = contenedor.select("a");
            Elements figureImagenes = documento.select(".ue-c-cover-content__image");



            int contador = 0;
            ArrayList<String> titulosEnlaces = new ArrayList<>();
            ArrayList<String> textoEnlace = new ArrayList<>();
            ArrayList<ImageView> imagenesNoticias = new ArrayList<>();

            for (int i = 0; i < titulos.size(); i++) {
                if (contador < 1) {
                    String link = figureImagenes.get(i).select("img").attr("src");
                    ImageView imageView = new ImageView(link);
                    Element titulo =  titulos.get(i);
                    Element enlace = enlaces.get(i);
                    String noticia =  titulo.text();
                    String enlaceNoticia =  enlace.attr("href");
                    Document textoNoticia = Jsoup.connect(enlaceNoticia).get();
                    Elements contenido = textoNoticia.select("p");
                    textoEnlace.add(contenido.text());
                    titulosEnlaces.add(noticia);
                    imagenesNoticias.add(imageView);
                    contador++;
                } else {
                    break; // Salir del bucle si ya se han obtenido los 6 enlaces
                }
            }

            for (String s : textoEnlace) {
                System.out.println(s);
            }
            for (String titulosEnlace : titulosEnlaces) {
                System.out.println(titulosEnlace);
            }
            titulo.setText(String.join("\n", titulosEnlaces));
            texto.setText(String.join("\n", textoEnlace));
            imagenNoticia.setImage(imagenesNoticias.get(0).getImage());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void mostrarContenido() {
        obtenerContenido();
    }
}
