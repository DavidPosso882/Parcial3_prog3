package com.example.prueba2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private ChoiceBox<String> dificultadChoiceBox;

    @FXML
    private TextField entrenadorTextField;

    @FXML
    private TextArea descripcionTextArea;

    @FXML
    private Button guardarEntrenadorButton;

    @FXML
    private Button guardarButton;

    @FXML
    private Button eliminarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private TableView<Deporte> deporteTableView;

    @FXML
    private TableColumn<Deporte, String> idColumn;

    @FXML
    private TableColumn<Deporte, String> nombreColumn;

    @FXML
    private TableColumn<Deporte, String> dificultadColumn;

    @FXML
    private TableColumn<Deporte, String> entrenadoresColumn;

    @FXML
    private TableColumn<Deporte, String> descripcionColumn;

    private ObservableList<Deporte> deporteList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        dificultadColumn.setCellValueFactory(new PropertyValueFactory<>("dificultad"));
        entrenadoresColumn.setCellValueFactory(new PropertyValueFactory<>("entrenadores"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        deporteTableView.setItems(deporteList);
    }

    @FXML
    private void handleGuardar() {
        if (validarCampos()) {
            String id = idTextField.getText();
            String nombre = nombreTextField.getText();
            String dificultad = dificultadChoiceBox.getValue();
            String entrenadores = entrenadorTextField.getText();
            String descripcion = descripcionTextArea.getText();

            Deporte deporte = new Deporte(id, nombre, dificultad, entrenadores, descripcion);
            deporteList.add(deporte);

            clearFields();
        } else {
            mostrarAlerta("Error", "Todos los campos deben estar completos.");
        }
    }

    private boolean validarCampos() {
        return !idTextField.getText().isEmpty() &&
                !nombreTextField.getText().isEmpty() &&
                dificultadChoiceBox.getValue() != null &&
                !entrenadorTextField.getText().isEmpty() &&
                !descripcionTextArea.getText().isEmpty();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void clearFields() {
        idTextField.clear();
        nombreTextField.clear();
        dificultadChoiceBox.setValue(null);
        entrenadorTextField.clear();
        descripcionTextArea.clear();
    }

    @FXML
    private void handleEliminar() {
        Deporte selectedDeporte = deporteTableView.getSelectionModel().getSelectedItem();
        if (selectedDeporte != null) {
            deporteList.remove(selectedDeporte);
        }
    }

    @FXML
    private void handleModificar() {
        Deporte selectedDeporte = deporteTableView.getSelectionModel().getSelectedItem();
        if (selectedDeporte != null) {
            selectedDeporte.setId(idTextField.getText());
            selectedDeporte.setNombre(nombreTextField.getText());
            selectedDeporte.setDificultad(dificultadChoiceBox.getValue());
            selectedDeporte.setEntrenadores(entrenadorTextField.getText());
            selectedDeporte.setDescripcion(descripcionTextArea.getText());
            deporteTableView.refresh();
        }
    }
}




