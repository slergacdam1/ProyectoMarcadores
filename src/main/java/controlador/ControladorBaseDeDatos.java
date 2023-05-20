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
    }public static void crearBaseDeDatos() {
        try {
            // Cargamos el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            String sql = "CREATE DATABASE marcadores";
            Statement statement = conexion.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Base de datos creada correctamente.");
            statement.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearTablaNoticia() {
        Statement statement = null;
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con la base de datos
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Crear una sentencia SQL para crear la tabla
            statement = conexion.createStatement();
            String sql = "CREATE TABLE noticia (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "titulo TEXT," +
                    "contenido TEXT," +
                    "imagen TEXT NOT NULL" +
                    ")";

            // Ejecutar la sentencia SQL para crear la tabla
            statement.executeUpdate(sql);

            System.out.println("Tabla creada correctamente.");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conexion.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void crearTablaPartido(){
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Crear una sentencia SQL para crear la tabla
            Statement statement = conexion.createStatement();
            String sql = "CREATE TABLE partido (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "fecha DATE," +
                    "hora TIME," +
                    "equipo_local TEXT NOT NULL," +
                    "equipo_visitante TEXT NOT NULL, "+
                    "resultado TEXT NOT NULL "+
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
    public static void eliminarTablaNoticia(){
        try {
            // Cargamos el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecemos la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Borramos la tabla "personas"
            String sql = "DROP TABLE noticia";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);
            System.out.println("Tabla eliminada correctamente");

            // Cerramos la conexión y la sentencia
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void eliminarTablaPartido(){
        try {
            // Cargamos el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecemos la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Borramos la tabla "personas"
            String sql = "DROP TABLE partido";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);
            System.out.println("Tabla eliminada correctamente");

            // Cerramos la conexión y la sentencia
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

            Elements contenedor = documento.select(".ue-c-cover-content__body");
            Elements titulos = contenedor.select("h2");
            Elements enlaces = contenedor.select("a");
            Elements figureImagenes = contenedor.select(".ue-c-cover-content__image");

            ArrayList<String> titulosEnlaces = new ArrayList<>();
            ArrayList<String> textoEnlace = new ArrayList<>();
            ArrayList<String> linksImagenes = new ArrayList<>();

            for (Element cont : contenedor) {
                String noticia = cont.select("h2").text();
                String linkImagen = cont.select(".ue-c-cover-content__image").select("img").attr("src");
                System.out.println(linkImagen);
                String enlaceNoticia = cont.select("a").attr("href");
                Document textoNoticia = Jsoup.connect(enlaceNoticia).get();
                String contenidoNoticia = textoNoticia.select("p").text();
                System.out.println(contenidoNoticia);

                titulosEnlaces.add(noticia);
                textoEnlace.add(contenidoNoticia);
                linksImagenes.add(linkImagen);
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

            System.out.println("Inserción correcta");

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








    public static void main(String[] args) {
        crearConexion();
        eliminarTablaNoticia();
        eliminarTablaPartido();
        crearTablaPartido();
        crearTablaNoticia();
        insertarNoticia();
        cerrarConexion();



    }}

