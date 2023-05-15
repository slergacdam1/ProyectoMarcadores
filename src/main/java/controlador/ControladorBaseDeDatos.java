package controlador;

import java.sql.*;

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

            // Creamos una sentencia SQL preparada para realizar inserciones
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO noticia (id,titulo, texto) VALUES (?, ?, ?)");
            idNoticia++;
                // Configuramos los valores a insertar en la sentencia preparada
                sentencia.setInt(1, idNoticia); // id
                sentencia.setString(2, "titulo: " + idNoticia); // titulo
                sentencia.setString(3, "OSASUNA GANA 3-1"); // edad

                // Ejecutamos la sentencia SQL preparada
                sentencia.executeUpdate();


            // Cerramos la conexión y la sentencia
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
      insertarNoticia();
      cerrarConexion();


    }

}

