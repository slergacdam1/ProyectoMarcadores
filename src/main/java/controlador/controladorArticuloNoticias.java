package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class controladorArticuloNoticias {

    @FXML
    private Button botonMarcadores;

    @FXML
    private Button botonNoticias;

    @FXML
    private Label texto;

    @FXML
    private Label titulo;

    public static void main(String[] args) {
        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").get();
            Elements contenedor = documento.select(".ue-c-cover-content__main");
            Elements titulos = contenedor.select("h2");
            Elements enlaces = contenedor.select("a");


            int contador = 0;
            ArrayList<String> titulosEnlaces = new ArrayList<>();
            ArrayList<String> textoEnlace = new ArrayList<>();

            for (int i = 0; i < titulos.size(); i++) {
                if (contador < 1) {
                    Element titulo = (Element) titulos.get(i);
                    Element enlace = enlaces.get(i);
                    String noticia =  titulo.text();
                    String enlaceNoticia =  enlace.attr("href");
                    Document textoNoticia = Jsoup.connect(enlaceNoticia).get();
                    Elements contenido = textoNoticia.select("p");
                    textoEnlace.add(contenido.text());

                    titulosEnlaces.add(noticia);


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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void scenaMarcadores(ActionEvent event) {
        Stage stage = (Stage) titulo.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/marcadores.fxml"));
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
    void scenaNoticias(ActionEvent event) {
        Stage stage = (Stage) titulo.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/vista/noticias.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 400);
            stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
            stage.setScene(scene);
            stage.show();
            controladorNoticias controladorNoticias = loader.getController();
            controladorNoticias.actualizarNoticia();
            controladorNoticias.asignarId();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*@FXML
    void mostrarTexto(MouseEvent  event) {
        obtenerTitulos();

    }*/

    @FXML
    void mostrarTitulo(MouseEvent event) {

    }

    private void obtenerTitulos(){
        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").get();
            Elements contenedor = documento.select(".ue-c-cover-content__main");
            Elements titulos = contenedor.select("h2");
            Elements enlaces = contenedor.select("a");


            int contador = 0;
            ArrayList<String> titulosEnlaces = new ArrayList<>();
            ArrayList<String> textoEnlace = new ArrayList<>();

            for (int i = 0; i < titulos.size(); i++) {
                if (contador < 1) {
                    Element titulo =  titulos.get(i);
                    Element enlace = enlaces.get(i);
                    String noticia =  titulo.text();
                    String enlaceNoticia =  enlace.attr("href");
                    Document textoNoticia = Jsoup.connect(enlaceNoticia).get();
                    Elements contenido = textoNoticia.select("p");
                    textoEnlace.add(contenido.text());


                    titulosEnlaces.add(noticia);

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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void mostrarContenido(javafx.scene.input.MouseEvent mouseEvent) {
        obtenerTitulos();
    }
}
