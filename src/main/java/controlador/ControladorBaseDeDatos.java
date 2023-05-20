package controlador;

import modelo.Partido;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorBaseDeDatos {
    public static final String url = "jdbc:mysql://localhost:3306/marcadores?useSSL=false";
    public static final String usuario = "root";
    public static final String contraseña = "root";

    private static List<Partido> partidos;



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
                    "jornada INT NOT NULL,"+
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
                String enlaceNoticia = cont.select("a").attr("href");
                Document textoNoticia = Jsoup.connect(enlaceNoticia).get();
                String contenidoNoticia = textoNoticia.select("p").text();
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
    public static void insertarPartido(){
        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division/calendario.html").get();

            Elements contenedor = documento.select("div.jornada.calendarioInternacional");

            int numeroJornada = 1; // Inicializamos el número de la jornada

            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            for (Element jornadaDiv : contenedor) {
                Element jornadaElement = jornadaDiv.selectFirst("div.cal-agendas.calendario");
                if (jornadaElement != null) {
                    // Agregamos el número de la jornada a la tabla
                    String jornada = "Jornada: " + numeroJornada;

                    Elements filas = jornadaElement.select("tr");

                    for (Element fila : filas) {
                        Element local = fila.selectFirst("td.local span");
                        Element visitante = fila.selectFirst("td.visitante span");
                        Element resultado = fila.selectFirst("td.resultado span");

                        if (local != null && visitante != null && resultado != null) {
                            String nombreLocal = local.text();
                            String nombreVisitante = visitante.text();
                            String resultadoPartido = resultado.text();
                            Partido partido = new Partido();
                            partido.setEquipoLocal(nombreLocal);
                            partido.setEquipoVisitante(nombreVisitante);
                            partido.setResultado(resultadoPartido);


                            // Realizar la inserción en la tabla partido
                            String sql = "INSERT INTO partido (jornada, equipo_local, equipo_visitante, resultado) VALUES (?, ?, ?, ?)";
                            PreparedStatement statement = conexion.prepareStatement(sql);
                            statement.setInt(1, numeroJornada);
                            statement.setString(2, nombreLocal);
                            statement.setString(3, nombreVisitante);
                            statement.setString(4, resultadoPartido);
                            statement.executeUpdate();
                        }
                    }

                    numeroJornada++; // Incrementamos el número de la jornada
                }
            }
            System.out.println("Partidos añadidos correctamente");
            // Cerrar la conexión con la base de datos
            conexion.close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Partido> consultarPartido(){
        partidos = new ArrayList<>();
        try {

            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Realizar la consulta a la tabla partido
            String sql = "SELECT * FROM partido";
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();

            // Recorrer el resultado de la consulta y mostrar los valores
            while (resultado.next()) {
                int id = resultado.getInt("id");
                int jornada = resultado.getInt("jornada");
                String equipoLocal = resultado.getString("equipo_local");
                String equipoVisitante = resultado.getString("equipo_visitante");
                String resultadoPartido = resultado.getString("resultado");

                // Crear un objeto Partido y añadirlo a la lista
                Partido partido = new Partido(jornada, equipoLocal, equipoVisitante, resultadoPartido);
                partidos.add(partido);

                System.out.println("ID: " + id);
                System.out.println("Jornada: " + jornada);
                System.out.println("Equipo Local: " + equipoLocal);
                System.out.println("Equipo Visitante: " + equipoVisitante);
                System.out.println("Resultado: " + resultadoPartido);
                System.out.println("-----------------------");
            }

            // Cerrar la conexión con la base de datos
            resultado.close();
            statement.close();
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return partidos;
    }
    public static void consultarNoticias() {
        try {
            // Cargamos el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecemos la conexión con la base de datos
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Creamos la consulta SQL para obtener los datos de la tabla
            String consulta = "SELECT titulo, contenido, imagen FROM noticia";

            // Creamos una sentencia SQL preparada para la consulta
            PreparedStatement sentencia = conexion.prepareStatement(consulta);

            // Ejecutamos la consulta y obtenemos el resultado
            ResultSet resultado = sentencia.executeQuery();

            // Recorremos el resultado y mostramos los datos
            while (resultado.next()) {
                String titulo = resultado.getString("titulo");
                String contenido = resultado.getString("contenido");
                String imagen = resultado.getString("imagen");

                System.out.println("Título: " + titulo);
                System.out.println("Contenido: " + contenido);
                System.out.println("Imagen: " + imagen);
                System.out.println("-----------------------");
            }

            // Cerramos la conexión, la sentencia y el resultado
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void consultarTitulosImagenes() {
        ArrayList<String> Titulos = new ArrayList<>();
        ArrayList<String> Imagenes = new ArrayList<>();
        try {
            // Cargamos el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecemos la conexión con la base de datos
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Creamos la consulta SQL para obtener los datos de la tabla
            String consulta = "SELECT titulo, imagen FROM noticia";

            // Creamos una sentencia SQL preparada para la consulta
            PreparedStatement sentencia = conexion.prepareStatement(consulta);

            // Ejecutamos la consulta y obtenemos el resultado
            ResultSet resultado = sentencia.executeQuery();

            // Recorremos el resultado y mostramos los datos
            while (resultado.next()) {
                String titulo = resultado.getString("titulo");
                String imagen = resultado.getString("imagen");

                System.out.println("Título: " + titulo);
                System.out.println("Imagen: " + imagen);
                System.out.println("-----------------------");
                Titulos.add(titulo);
                Imagenes.add(imagen);
            }

            // Cerramos la conexión, la sentencia y el resultado
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








    public static void main(String[] args) {
        crearConexion();
        eliminarTablaNoticia();
        eliminarTablaPartido();
        crearTablaPartido();
        crearTablaNoticia();
        insertarNoticia();
        insertarPartido();
     consultarPartido();
        cerrarConexion();



    }}

