package controlador;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ControladorBaseDeDatos {
    public static final String url = "jdbc:mysql://localhost:3306/marcadores?useSSL=false";
    public static final String usuario = "root";
    public static final String contraseña = "root";

    public static Connection conexion;
    public static int idNoticia = 1;

    public static void crearConexion() {
        // Configuramos la conexión con la base de datos
        try {
            // Cargamos el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecemos la conexión con la base de datos
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexion abierta con exito");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void cerrarConexion(){
        try {
            conexion.close();
            System.out.println("Se ha cerrado la conexion");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarNoticia() {

        try {
            // Cargamos el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecemos la conexión con la base de datos
            conexion = DriverManager.getConnection(url, usuario, contraseña);


            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").get();
            Elements contenedor = documento.select(".ue-c-cover-content__main");
            Elements titulos = contenedor.select("h2");
            Elements enlaces = contenedor.select("a");
            Elements figureImagenes = documento.select(".ue-c-cover-content__image");



            int contador = 0;
            ArrayList<String> titulosEnlaces = new ArrayList<>();
            ArrayList<String> textoEnlace = new ArrayList<>();
            ArrayList<String> linksImagenes = new ArrayList<>();

            for (int i = 0; i < titulos.size(); i++) {
                String link = figureImagenes.get(i).select("img").attr("src");
                Element titulo = titulos.get(i);
                Element enlace = enlaces.get(i);
                String noticia = titulo.text();
                String enlaceNoticia = enlace.attr("href");
                Document textoNoticia = Jsoup.connect(enlaceNoticia).get();
                Elements contenido = textoNoticia.select("p");
                System.out.println(contenido.text());
                textoEnlace.add(contenido.text());
                titulosEnlaces.add(noticia);
                linksImagenes.add(link);
                contador++;
            }



            // Creamos una sentencia SQL preparada para realizar inserciones
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO noticia (titulo, contenido, imagen) VALUES (?, ?, ?)");

            for (int i = 0; i < titulosEnlaces.size(); i++) {

                String titulo = titulosEnlaces.get(i);
                String contenido = textoEnlace.get(i);
                String imagen = linksImagenes.get(i);
                sentencia.setString(1, titulo);
                sentencia.setString(2, contenido);
                sentencia.setString(3, imagen);

                sentencia.executeUpdate();
            }
            System.out.println("Insercion correcta");


            // Cerramos la conexión y la sentencia
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearTabla(){
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Crear una sentencia SQL para crear la tabla
            Statement statement = conexion.createStatement();
            String sql = "CREATE TABLE noticia (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "titulo TEXT," +
                    "contenido TEXT," +
                    "imagen TEXT NOT NULL" +
                    ")";

            // Ejecutar la sentencia SQL para crear la tabla
            statement.executeUpdate(sql);

            System.out.println("Tabla creada correctamente.");

            // Cerrar la conexión
            statement.close();
            conexion.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public static void main(String[] args) {
      crearConexion();
      crearTabla();
      insertarNoticia();
      cerrarConexion();


    }

}

