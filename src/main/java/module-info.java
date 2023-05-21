module com.example.pantallalog {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires java.sql;


    opens controlador to javafx.fxml;
    exports controlador;


}