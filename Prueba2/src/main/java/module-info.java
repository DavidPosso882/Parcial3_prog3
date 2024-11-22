module com.example.prueba2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prueba2 to javafx.fxml;
    exports com.example.prueba2;
}