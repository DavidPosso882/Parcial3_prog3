module com.example.lab2prueba {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.lab2prueba to javafx.fxml;
    exports com.example.lab2prueba;
}