package edu.marta.dida.cartelera;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
     Parent contenedor = new FXMLLoader().load(getClass().getResource("FormularioInicial.fxml"));
     
     Scene escena = new Scene(contenedor, 820,500);
     
     primaryStage.setScene(escena);
     primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}