package edu.marta.dida.cartelera;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


   
        
        
public class FormularioInicialController implements Initializable {

    @FXML
    TextField titulo;
    @FXML 
    CheckBox mayorCheck;
    @FXML 
    CheckBox menorCheck;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarChecks();
    } 
    
    
     private void configurarChecks() {
        mayorCheck.selectedProperty().addListener((obs, oldV, newV) -> {
            if(newV) {
               menorCheck.setSelected(false);
            }
        });
        menorCheck.selectedProperty().addListener((obs, oldV, newV) -> {
            if(newV) {
               mayorCheck.setSelected(false);
            }
        });
    }
    
     public void add(){
       
    }
    
    public void edit() {
        
    }
    
    public void delete(){
       
    }
    
}
