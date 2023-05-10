module com.example.pantallalog {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens com.example.pantallalog to javafx.fxml;
    exports com.example.pantallalog;
}