package edu.marta.dida.cartelera;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FormularioDirectoresController implements Initializable {

   @FXML
    Button volver;
    
    @FXML
    Button cargarPelis;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     @FXML
    private void volver() throws IOException{
         App.setRoot("PantallaInicial");
    }    
    
    
    
    @FXML
    private void cargarPelis() throws IOException{
         App.setRoot("FormularioInicial");
    }  
    
}
