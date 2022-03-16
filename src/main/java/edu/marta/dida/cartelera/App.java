package edu.marta.dida.cartelera;

import edu.marta.dida.carteleraDAO.DirectorDAO;
import edu.marta.dida.carteleraDAO.PeliculaDAO;
import edu.marta.dida.carteleraDAO.TablaIntermediaDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene escena;
    private static Stage stage;


    public static Stage getStage() {
        return stage;
    }
    
    

    @Override
    public void start(Stage primaryStage) throws IOException {

        
     this.stage = primaryStage;
     escena = new Scene(loadFXML("PantallaInicial"));  
     
     primaryStage.setScene(escena);
     primaryStage.show();
   
    }
    
    static void setRoot(String fxml) throws IOException {
        
        escena.setRoot(loadFXML(fxml));
        App.getStage().sizeToScene();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        double x = (primScreenBounds.getWidth() - App.getStage().getWidth()) / 2;
        double y = (primScreenBounds.getHeight() - App.getStage().getHeight()) / 2;
        App.getStage().setX(x);
        App.getStage().setY(y);
        
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load(); 
    }

    public static void main(String[] args) {
        launch();
    }

}