module com.example.singleton {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;

    opens com.example.singleton to javafx.fxml;
    exports com.example.singleton;
}