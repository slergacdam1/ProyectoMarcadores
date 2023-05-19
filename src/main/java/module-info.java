module com.example.pantallalog {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires java.sql;


    opens com.example.pantallalog to javafx.fxml;
    exports com.example.pantallalog;

    opens controlador to javafx.fxml;
    exports controlador;
}