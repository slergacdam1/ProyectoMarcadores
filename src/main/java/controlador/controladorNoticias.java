package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    void onLeerNoticia(MouseEvent event) {


        Stage stage = (Stage) botonNoticias.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/articuloNoticia.fxml"));
        // Crear una instancia de la nueva ventana
        try {
            Scene scene = new Scene(loader.load(), 600, 400);
            stage.setTitle("- Ejemplo sencillo de aplicación JavaFX -");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
            System.out.println(event.getSceneX());
            System.out.println(event.getSceneY());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void actualizarNoticia() {
        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").timeout(1000).get();
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
                            //ImageView imageView0 = new ImageView(link);
                            imagen1.setImage(imageView.getImage());
                            break;
                        case 1:
                            noticia2.setText(noticia);
                            //ImageView imageView1 = new ImageView(link);
                            imagen2.setImage(imageView.getImage());
                            break;
                        case 2:
                            noticia3.setText(noticia);
                            //ImageView imageView2 = new ImageView(link);
                            imagen3.setImage(imageView.getImage());
                            break;
                        case 3:
                            noticia4.setText(noticia);
                            //ImageView imageView3 = new ImageView(link);
                            imagen4.setImage(imageView.getImage());
                            break;
                        case 4:
                            noticia5.setText(noticia);
                            //ImageView imageView4 = new ImageView(link);
                            imagen5.setImage(imageView.getImage());
                            break;
                        case 5:
                            noticia6.setText(noticia);
                            ImageView imageView6 = new ImageView(link);
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
    public void asignarId(){
        noticia1.setId("label1");
        noticia2.setId("label2");
        noticia3.setId("label3");
        noticia4.setId("label4");
        noticia5.setId("label5");
        noticia6.setId("label6");
    }

    public static void main(String[] args) {
        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").timeout(1000).get();
            Elements contenedor = documento.select(".ue-c-cover-content__main");
            Elements titulos = contenedor.select("h2");
            Elements figureImagenes = documento.select(".ue-c-cover-content__image");
            for (Element figureImagene : figureImagenes) {
                String link = figureImagene.attr("src");
                System.out.println(link.toString());
            }
/*
            String imagen = figureImagenes.attr("src");
            System.out.println(imagen);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}