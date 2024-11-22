package com.example.laboratoriouno;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import metodos.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeporteController {
    @FXML
    private Button btnEliminarDeporte;

    @FXML
    private Button btnGuardarDeporte;

    @FXML
    private Button btnAgregarEntrenador;
    @FXML
    private TableView<Deporte> tableDeporte;

    @FXML
    private TableColumn<Deporte, String> tcDescripcion;

    @FXML
    private TableColumn<Deporte, Enum> tcDificultad;

    @FXML
    private TableColumn<Deporte, ArrayList> tcEntrenadores;

    @FXML
    private TableColumn<Deporte, String> tcNombreDeporte;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombreDeporte;
    @FXML
    private TextField txtDescriptionDeporte;
    @FXML
    private TextField txtDificultad;
    @FXML
    private TextField txtIdEntrenador;

    @FXML
    private Label lbNivel;

    @FXML
    private TableView<Modelo> tableMiembros;

    @FXML
    private TableColumn<Modelo, String> tcEspecialidad;

    @FXML
    private TableColumn<Modelo, String> tcNombre;
    String ruta = "deportes.json";
    @FXML
    Type listType = new TypeToken<ArrayList<Deporte>>(){}.getType();
    List<Modelo> m = MetodosCrud.readFromFile(ruta, listType);
    Deporte deporte = new Deporte();

    private ObservableList<Deporte> deporteList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        tcNombreDeporte.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcDificultad.setCellValueFactory(new PropertyValueFactory<>("dificultad"));
        tcEntrenadores.setCellValueFactory(new PropertyValueFactory<>("entrenadores"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tableDeporte.setItems(deporteList);
    }

    @FXML
    private void handleEliminar() {
        Deporte selectedDeporte = tableDeporte.getSelectionModel().getSelectedItem();
        if (selectedDeporte != null) {
            deporteList.remove(selectedDeporte);
        }
    }


    @FXML
    void handleModificar(ActionEvent event) {
        Deporte selectedDeporte = tableDeporte.getSelectionModel().getSelectedItem();
        if (selectedDeporte != null) {
            int id = Integer.parseInt(txtId.getText());
            selectedDeporte.setId(id);
            selectedDeporte.setNombre(txtNombreDeporte.getText());
            if (txtDificultad.getText() != null && !txtDificultad.getText().trim().isEmpty()) {
                int op = Integer.parseInt(txtDificultad.getText());
                selectedDeporte.setDificultad(MetodosCrud.dificultadC(op));
            }
            selectedDeporte.setDescripcion(txtDescriptionDeporte.getText());

            // Convertir el String de IDs de entrenadores a una lista de objetos Modelo
            String entrenadoresText = txtIdEntrenador.getText();
            ArrayList<Modelo> entrenadoresList = new ArrayList<>();
            for (String idStr : entrenadoresText.split(",")) {
                int entrenadorId = Integer.parseInt(idStr.trim());
                Modelo entrenador = MetodosCrud.buscarUsuario(entrenadorId, "entrenadores.json", new TypeToken<Modelo>(){}.getType());
                if (entrenador != null) {
                    entrenadoresList.add(entrenador);
                }
            }
            selectedDeporte.setEntrenadores(entrenadoresList);

            MetodosCrud.editarUsuario(selectedDeporte, ruta, m);
            deporteList.set(deporteList.indexOf(selectedDeporte), selectedDeporte); // Actualizar el deporte en la lista observable
            tableDeporte.refresh(); // Refrescar la tabla
            limpiarCampos();
            mostrarAlerta("Se modificó exitosamente");
        } else {
            mostrarAlerta("No se ha seleccionado ningún deporte para modificar.");
        }
    }



    @FXML
    void agregarEntrenador(ActionEvent event) {
        int id = Integer.parseInt(txtIdEntrenador.getText());
        Type listType = new TypeToken<ArrayList<Modelo>>(){}.getType();
        Modelo mo = MetodosCrud.buscarUsuario(id, "entrenadores.json", listType);
        deporte.entrenadores.add(mo);
    }

    @FXML
    void crearDeporte(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        int op = Integer.parseInt(txtDificultad.getText());
        Deporte nuevoDeporte = new Deporte();
        nuevoDeporte.setId(id);
        nuevoDeporte.setNombre(txtNombreDeporte.getText());
        nuevoDeporte.setDificultad(MetodosCrud.dificultadC(op));
        nuevoDeporte.setDescripcion(txtDescriptionDeporte.getText());
        MetodosCrud.crearUsuario(nuevoDeporte, ruta, m);
        deporteList.add(nuevoDeporte); // Agregar el nuevo deporte a la lista observable
        tableDeporte.refresh(); // Refrescar la tabla
        limpiarCampos();
        mostrarAlerta("Se creó un nuevo deporte exitosamente");
    }

    @FXML
    void editarDeporte(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        deporte.setId(id);
        deporte.setNombre(txtNombreDeporte.getText());
        if (txtDificultad.getText() != null && !txtDificultad.getText().trim().isEmpty()) {
            int op = Integer.parseInt(txtDificultad.getText());
            deporte.setDificultad(MetodosCrud.dificultadC(op));
        }
        deporte.setDescripcion(txtDescriptionDeporte.getText());
        MetodosCrud.editarUsuario(deporte, ruta, m);
        deporteList.set(deporteList.indexOf(deporte), deporte); // Actualizar el deporte en la lista observable
        tableDeporte.refresh(); // Refrescar la tabla
        limpiarCampos();
        mostrarAlerta("Se modificó exitosamente");
    }

    @FXML
    void eliminarDeporte(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        MetodosCrud.eliminarUsuario(id, ruta, m);
        deporteList.removeIf(d -> d.getId() == id); // Eliminar el deporte de la lista observable
        tableDeporte.refresh(); // Refrescar la tabla
    }

    @FXML
    void buscarDeporte(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        Type listType = new TypeToken<ArrayList<Deporte>>(){}.getType();
        deporte = MetodosCrud.buscarUsuario(id, ruta, listType);
        if (deporte == null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "No se encontraron resultados", ButtonType.OK);
            alerta.setTitle("Confirmación");
            alerta.showAndWait();
        } else {
            txtNombreDeporte.setText(deporte.getNombre());
            txtDescriptionDeporte.setText(deporte.getDescripcion());
            lbNivel.setText(String.valueOf(deporte.getDificultad()));
        }
    }

    private void limpiarCampos() {
        txtDificultad.setText("");
        txtDescriptionDeporte.setText("");
        txtId.setText("");
        txtNombreDeporte.setText("");
        txtIdEntrenador.setText("");
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.OK);
        alerta.showAndWait();
    }
}
