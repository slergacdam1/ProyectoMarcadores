package controlador;

import modelo.Noticia;
import modelo.Partido;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class controladorBaseDeDatos {
    public static final String url1 = "jdbc:mysql://localhost:3306/LaLigaNews?useSSL=false";
    public static final String url = "jdbc:mysql://localhost:3306/";
    public static final String usuario = "root";
    public static final String contraseña = "root";

    static ArrayList<Partido> partidos;
    static ArrayList<Noticia> noticias;


    public static Connection conexion;

    public static void generarBaseDeDatosCompleta() {
        crearConexion();
        crearBaseDeDatos();
        seleccionarBaseDeDatos();
        crearTablaNoticia();
        crearTablaPartido();
        insertarNoticia();
        insertarPartido();

    }


    public static void crearConexion() {
        // Configuramos la conexión con el servidor de MySQL
        try {
            // Cargamos el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecemos la conexión con el servidor de MySQL sin seleccionar una base de datos
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión abierta con éxito");


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void cerrarConexion() {
        try {
            conexion.close();
            System.out.println("Se ha cerrado la conexion");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearBaseDeDatos() {
        Statement statement = null;
        try {
            String sql = "CREATE DATABASE IF NOT EXISTS LaLigaNews";
            statement = conexion.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("Base de datos creada correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void seleccionarBaseDeDatos() {
        Statement statement = null;
        try {
            String sql = "USE LaLigaNews";
            statement = conexion.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("Base de datos seleccionada correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearTablaNoticia() {
        Statement statement = null;
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con la base de datos
            conexion = DriverManager.getConnection(url1, usuario, contraseña);

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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void crearTablaPartido() {
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(url1, usuario, contraseña);

            statement = conexion.createStatement();
            String sql = "CREATE TABLE partido (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "jornada INT NOT NULL," +
                    "equipo_local TEXT NOT NULL," +
                    "equipo_visitante TEXT NOT NULL, " +
                    "resultado TEXT NOT NULL " +
                    ")";

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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void eliminarTablaNoticia() {
        Statement statement = null;
        try {
            // Cargamos el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(url1, usuario, contraseña);

            String sql = "DROP TABLE noticia";
            statement = conexion.createStatement();
            statement.execute(sql);
            System.out.println("Tabla eliminada correctamente");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void eliminarTablaPartido() {
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(url1, usuario, contraseña);

            String sql = "DROP TABLE partido";
            statement = conexion.createStatement();
            statement.execute(sql);
            System.out.println("Tabla eliminada correctamente");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertarNoticia() {
        PreparedStatement sentencia = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecemos la conexión con la base de datos
            conexion = DriverManager.getConnection(url1, usuario, contraseña);

            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division.html?intcmp=MENUPROD&s_kw=primera-division").get();

            Elements contenedor = documento.select(".ue-c-cover-content__body");

            ArrayList<String> titulosEnlaces = new ArrayList<>();
            ArrayList<String> textoEnlace = new ArrayList<>();
            ArrayList<String> linksImagenes = new ArrayList<>();

            for (Element cont : contenedor) {
                String noticia = cont.select("h2").text();
                String linkImagen = cont.select(".ue-c-cover-content__image").select("img").attr("src");
                String enlaceNoticia = cont.select("a").attr("href");
                Document textoNoticia = Jsoup.connect(enlaceNoticia).get();
                String contenidoNoticia = textoNoticia.select("p").text();

                if (!existeNoticia(noticia)) {
                    titulosEnlaces.add(noticia);
                    textoEnlace.add(contenidoNoticia);
                    linksImagenes.add(linkImagen);
                }
            }

             sentencia = conexion.prepareStatement("INSERT INTO noticia (titulo, contenido, imagen) VALUES (?, ?, ?)");

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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                sentencia.close();
                conexion.close();
            }catch (SQLException e ){
                e.printStackTrace();
            }
        }
    }

    private static boolean existeNoticia(String titulo) {
        PreparedStatement sentencia = null;
        try {
            // Creamos una sentencia SQL preparada para realizar la consulta
             sentencia = conexion.prepareStatement("SELECT COUNT(*) FROM noticia WHERE titulo = ?");
            sentencia.setString(1, titulo);

            // Ejecutamos la consulta y obtenemos el resultado
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();
            int count = resultado.getInt(1);

            resultado.close();

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            try{
                sentencia.close();
            }catch (SQLException e ){
                e.printStackTrace();
            }
        }
    }

    public static void insertarPartido() {
        try {
            Document documento = Jsoup.connect("https://www.marca.com/futbol/primera-division/calendario.html").get();

            Elements contenedor = documento.select("div.jornada.calendarioInternacional");

            int numeroJornada = 1; // Inicializamos el número de la jornada

            Connection conexion = DriverManager.getConnection(url1, usuario, contraseña);

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

                            // Verificar si el partido ya existe en la base de datos
                            String consultaSql = "SELECT * FROM partido WHERE jornada = ? AND equipo_local = ? AND equipo_visitante = ?";
                            PreparedStatement consultaStatement = conexion.prepareStatement(consultaSql);
                            consultaStatement.setInt(1, numeroJornada);
                            consultaStatement.setString(2, nombreLocal);
                            consultaStatement.setString(3, nombreVisitante);
                            ResultSet resultadoConsulta = consultaStatement.executeQuery();

                            // Si el resultado de la consulta tiene alguna fila, el partido ya existe
                            if (resultadoConsulta.next()) {
                                System.out.println("El partido ya existe: " + nombreLocal + " vs " + nombreVisitante);
                                continue; // Pasar al siguiente partido sin realizar la inserción
                            }

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

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexion.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Partido> consultarPartido() {
        partidos = new ArrayList<>();
        PreparedStatement sentencia = null;
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url1, usuario, contraseña);

            // Realizar la consulta a la tabla partido
            String sql = "SELECT * FROM partido";
            sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

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
            }

            // Cerrar la conexión con la base de datos
            resultado.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return partidos;
    }

    public static ArrayList<Noticia> consultarNoticias() {
        noticias = new ArrayList<>();
        Statement sentencia = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url1, usuario, contraseña);

            String consulta = "SELECT titulo, contenido, imagen FROM noticia";

             sentencia = conexion.prepareStatement(consulta);

            ResultSet resultado = sentencia.executeQuery(consulta);

            while (resultado.next()) {
                String titulo = resultado.getString("titulo");
                String texto = resultado.getString("contenido");
                String imagen = resultado.getString("imagen");
                Noticia noticia = new Noticia(titulo, texto, imagen);
                noticias.add(noticia);
            }

            resultado.close();
            sentencia.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {

                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return noticias;
    }

}

