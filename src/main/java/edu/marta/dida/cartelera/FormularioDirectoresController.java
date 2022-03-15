package edu.marta.dida.cartelera;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import edu.marta.dida.carteleraDAO.DirectorDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FormularioDirectoresController implements Initializable {

    @FXML
    TextField nombre;
    @FXML
    Slider edad;
    @FXML
    TextField edadField;
    @FXML
    TextField nivel;
    
    @FXML
    Button volver;
    
    @FXML
    Button cargarPelis;
    
     
    @FXML 
    TableView<Director> tablaDirectores;
    
    DirectorDAO directorDAO;
    
    int iddir = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      directorDAO = new DirectorDAO();
        cargarDirectores();
        edad.valueProperty().addListener((observable, oldValue, newValue) -> {
        edadField.setText(Double.toString(newValue.intValue()));
        });
    }    
    
    @FXML
    public void addDir(){
        System.err.println("Dentro de ADDIR");
            Director director = new Director();
            director.setIdDir(iddir);
            director.setNombre(nombre.getText());
            director.setEdad((int)edad.getValue());
            director.setNivel(nivel.getText());
   
            directorDAO.guardarOActualizar(director);
            
            iddir = 0;
            
            cargarDirectores();
            
            clean();
            
        }
    
    public void clean(){
            nombre.clear();
            edad.setValue(0);
            nivel.clear();
           
    } 
    
    private void cargarDirectores() {
       ObservableList<Director> directores = FXCollections.observableArrayList();
       List<Director> directoresCartelera = directorDAO.buscarDirectores();
       directores.addAll (directoresCartelera);
       tablaDirectores.setItems(directores);
    }
    
    @FXML
    public void editDir() {
        Director director = tablaDirectores.getSelectionModel().getSelectedItem();
        nombre.setText(director.getNombre());
        
        System.err.println(director.getEdad());
        
        edad.setValue(director.getEdad());
        nivel.setText(director.getNivel());
       
        iddir = director.getIdDir();
    }
    
    
    
    
    @FXML
    public void deleteDir(){
       Director director = tablaDirectores.getSelectionModel().getSelectedItem();
        directorDAO.eliminar(director);
        cargarDirectores();
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
