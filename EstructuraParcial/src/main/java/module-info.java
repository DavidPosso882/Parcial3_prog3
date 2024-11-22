module com.example.estructuraparcial {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.estructuraparcial to javafx.fxml;
    exports com.example.estructuraparcial;
}