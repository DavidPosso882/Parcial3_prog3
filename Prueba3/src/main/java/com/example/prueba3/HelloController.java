package com.example.prueba3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Button btnSumar;

    @FXML
    private Button btnRestar;

    @FXML
    private Button btnMultiplicar;

    @FXML
    private TextField txtNumero1;

    @FXML
    private TextField txtNumero2;

    @FXML
    private TextField txtResultado;

    Operacion operacion = new Operacion();

   /* @FXML

   // metodo para ingresar dos numeros y sumarlos,
    void onSumarNumeros(ActionEvent event) {
        try {
            int num1 = Integer.parseInt(txtNumero1.getText());
            int num2 = Integer.parseInt(txtNumero2.getText());
            int resutado = operacion.sumar(num1, num2);

            txtResultado.setText(String.valueOf(resutado));

        }catch (NumberFormatException ignored) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }

    }

    @FXML
    void onMutiplicarNumeros(ActionEvent event) {
        try {
            int num1 = Integer.parseInt(txtNumero1.getText());
            int num2 = Integer.parseInt(txtNumero2.getText());
            int resutado = operacion.multiplicar(num1, num2);

            txtResultado.setText(String.valueOf(resutado));

        }catch (NumberFormatException ignored) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }

    }

    @FXML
    void onRestarNumeros(ActionEvent event) {
        try {
            int num1 = Integer.parseInt(txtNumero1.getText());
            int num2 = Integer.parseInt(txtNumero2.getText());
            int resutado = operacion.restar(num1, num2);

            txtResultado.setText(String.valueOf(resutado));

        }catch (NumberFormatException ignored) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }

    }*/
    @FXML
    void onResolveOperation(ActionEvent event) {
        int resultado;
        try{
            int num1 = Integer.parseInt(txtNumero1.getText());
            int num2 = Integer.parseInt(txtNumero2.getText());
            if(event.getSource() == btnSumar){
               resultado = operacion.sumar(num1, num2);
               txtResultado.setText(String.valueOf(resultado));
            } else if (event.getSource() == btnRestar) {
                resultado = operacion.restar(num1, num2);
                txtResultado.setText(String.valueOf(resultado));
            }
            else{
                resultado = operacion.multiplicar(num1, num2);
                txtResultado.setText(String.valueOf(resultado));
            }
        }catch (Exception ignored) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }

}
