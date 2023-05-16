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
            List<String> listaEnlaces = new ArrayList<>();
            ArrayList<String> titulosEnlaces = new ArrayList<>();

            for (int i = 0; i < titulos.size(); i++) {
                if (contador <= 6) {
                    Element titulo = (Element) titulos.get(i);
                    Element enlace = (Element) enlaces.get(i);
                    String noticia = ((org.jsoup.nodes.Element) titulo).text();
                    String enlaceNoticia = ((org.jsoup.nodes.Element) enlace).attr("href");

                    // Almacenar el enlace en la lista
                    listaEnlaces.add(enlaceNoticia);
                    titulosEnlaces.add(String.valueOf(titulo));

                    contador++;
                } else {
                    break; // Salir del bucle si ya se han obtenido los 6 enlaces
                }
            }

            // Hacer algo con la lista de enlaces, por ejemplo, procesar cada enlace para obtener más detalles

            for (String enlace : listaEnlaces) {
                // Aquí puedes hacer lo que necesites con cada enlace, como obtener el contenido de la noticia


                // Haz algo con el contenido de la noticia, por ejemplo, mostrarlo en un cuadro de diálogo
                System.out.println(enlace);
            }
            for (String titulosEnlace : titulosEnlaces) {
                System.out.println(titulosEnlace);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    @FXML
//    void mostrarContenido(MouseEvent event) {
//        try {
//            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").timeout(6000).get();
//            Elements contenedor = documento.select(".ue-c-cover-content__main");
//            Elements titulos = contenedor.select("h2");
//            Elements links = contenedor.select("a.ue-c-cover-content__link");
//            String link = links.get(0).attr("href");
//            System.out.println("SUPER LINK "+link);
//            Elements imagenes = contenedor.select(".ue-c-cover-content__media");
//
//            int contador = 0;
//            for (Element titulo : titulos) {
//                if (contador <= 6) {
//                    String noticia = titulo.text();
//
//
//                    // Asignar la noticia al label correspondiente
//                    switch (contador) {
//                        case 1:
//                            System.out.println(noticia);
//                            Elements bloqueEnlace = documento.select(".ue-c-article--first-letter-highlighted");
//                            Elements tipoEnlace = bloqueEnlace.select("a[href]");
//                            for (Element element : tipoEnlace) {
//                                System.out.println(tipoEnlace.attr("href"));
//                            }
//                            System.out.println(tipoEnlace);
//                            *//*Document documentoNoticia = Jsoup.connect(enlaceNoticia).timeout(6000).get();
//                            Element contenido = documentoNoticia.select(".ue-c-article--first-letter-highlighted").first();
//                            String textoContenido = contenido.text();
//                            // Haz algo con el contenido de la noticia, por ejemplo, mostrarlo en un cuadro de diálogo
//                            System.out.println(textoContenido);*//*
//                            break;
//                        case 2:
//                            System.out.println(noticia);
//                            break;
//                        case 3:
//                            System.out.println(noticia);
//                            break;
//                        case 4:
//                            System.out.println(noticia);
//                            break;
//                        case 5:
//                            System.out.println(noticia);
//                            break;
//                        case 6:
//                            System.out.println(noticia);
//                            break;
//                    }
//
//                    contador++;
//                } else {
//                    break; // Salir del bucle si ya se han asignado las 6 noticias
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
