package com.example.pruebaspring;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main extends Application {

    public static void main(String[] args) {
        System.out.println("Hello World");
        //SpringApplication.run(PruebaSpringApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }
}
