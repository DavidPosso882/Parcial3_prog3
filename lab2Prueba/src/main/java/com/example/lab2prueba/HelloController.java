package com.example.lab2prueba;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Button espaniolbtn;

    @FXML
    private Button inglesbtn;

    @FXML
    private Button rusobtn;

    @FXML
    private Label saludoLbl;

    @FXML
    private ChoiceBox<String> idiomaChoice;




    @FXML
    public void initialize() {
        // Inicializar el ChoiceBox con los nombres de los idiomas
        idiomaChoice.getItems().addAll("Español", "Inglés", "Alemán");

        // Establecer un valor por defecto
        idiomaChoice.setValue("Español");

        // Establecer un listener para cambiar el idioma cuando se seleccione uno del ChoiceBox
        idiomaChoice.setOnAction(this::desplegarIdioma);
    }

    @FXML
    void desplegarIdioma(ActionEvent event) {
        String selectedLanguage = idiomaChoice.getValue();
        ResourceBundle bundle;

        switch (selectedLanguage) {
            case "Español":
                bundle = ResourceBundle.getBundle("message", new Locale("en", "ES"));
                saludoLbl.setText(bundle.getString("saludo"));
                break;
            case "Inglés":
                bundle = ResourceBundle.getBundle("message", new Locale("en", "US"));
                saludoLbl.setText(bundle.getString("greet"));
                break;
            case "Alemán":
                bundle = ResourceBundle.getBundle("message", new Locale("en", "RU"));
                saludoLbl.setText(bundle.getString("priv"));
                break;
        }
    }

    // Los otros métodos permanecen sin cambios
    @FXML
    void mostrarEspanolAction(ActionEvent event) {
        ResourceBundle bundle = ResourceBundle.getBundle("message", new Locale("en", "ES"));
        saludoLbl.setText(bundle.getString("saludo"));
    }

    @FXML
    void mostrarInglesAction(ActionEvent event) {
        ResourceBundle bundle = ResourceBundle.getBundle("message", new Locale("en", "US"));
        saludoLbl.setText(bundle.getString("greet"));
    }

    @FXML
    void mostrarRusoAction(ActionEvent event) {
        ResourceBundle bundle = ResourceBundle.getBundle("message", new Locale("en", "RU"));
        saludoLbl.setText(bundle.getString("priv"));
    }
}




