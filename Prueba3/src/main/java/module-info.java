module com.example.prueba3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prueba3 to javafx.fxml;
    exports com.example.prueba3;
}