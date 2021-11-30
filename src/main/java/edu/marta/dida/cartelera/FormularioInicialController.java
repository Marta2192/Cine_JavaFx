package edu.marta.dida.cartelera;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import edu.marta.dida.carteleraDAO.PeliculaDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
        
public class FormularioInicialController implements Initializable {

    @FXML
    TextField titulo;
    @FXML
    TextField director;
    @FXML
    TextArea sinopsis;
    @FXML 
    CheckBox siVoCheck;
    @FXML 
    CheckBox noVoCheck;
    @FXML
    DatePicker fecha;
    @FXML
    ComboBox<Integer> sala;
    @FXML
    Button volver;
   
    
    
    @FXML 
    TableView<Pelicula> tablaPeliculas;
    
    PeliculaDAO peliculaDAO;
    
    int id = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
       
        configurarChecks();
        peliculaDAO = new PeliculaDAO();
        cargarPeliculasCartelera();
        configurarFecha();
        configurarComboBox();
       
    } 
    
    
     private void configurarChecks() {
         siVoCheck.setSelected(true);
        siVoCheck.selectedProperty().addListener((obs, oldV, newV) -> {
            if(newV) {
               noVoCheck.setSelected(!newV);
            }
        });
        noVoCheck.selectedProperty().addListener((obs, oldV, newV) -> {
            if(newV) {
               siVoCheck.setSelected(!newV);
            }
        });
    }
    
    @FXML
    public void add(){
       Pelicula pelicula = new Pelicula();
            pelicula.setId(id);
            pelicula.setTitulo(titulo.getText());
            pelicula.setDirector(director.getText());
            pelicula.setSinopsis(sinopsis.getText());
            pelicula.setIdioma(siVoCheck.isSelected()? 0 : 1); 
            pelicula.setFecha(fecha.getValue().toString());
            pelicula.setSala(sala.getValue());
            
            peliculaDAO.guardarOActualizar(pelicula);
            
            id = 0;
            
            cargarPeliculasCartelera();
            
            clean();
            
        }
    public void clean(){
            titulo.clear();
            director.clear();
            sinopsis.clear();
            siVoCheck.setSelected(true);
            configurarFecha();
            sala.setValue(-1);
    } 
    
    @FXML
    public void edit() {
        Pelicula pelicula = tablaPeliculas.getSelectionModel().getSelectedItem();
        titulo.setText(pelicula.getTitulo());
        director.setText(pelicula.getDirector());
        sinopsis.setText(pelicula.getSinopsis());
        if(pelicula.getIdioma() == 0){
            siVoCheck.setSelected(true); 
        } else {
            noVoCheck.setSelected(true); 
        }      
        String[] fecha = pelicula.getFecha().split("-");
        List<Integer> datosFecha = Arrays.asList(fecha).stream().map(dato -> Integer.parseInt(dato)).collect(Collectors.toList());
       
        this.fecha.setValue( LocalDate.of(datosFecha.get(0), datosFecha.get(1), datosFecha.get(2)));
        sala.setValue(pelicula.getSala());
        id = pelicula.getId();
    }
    
    @FXML
    public void delete(){
       Pelicula pelicula = tablaPeliculas.getSelectionModel().getSelectedItem();
        peliculaDAO.eliminar(pelicula);
        cargarPeliculasCartelera();
    }

    private void cargarPeliculasCartelera() {
       ObservableList<Pelicula> peliculas = FXCollections.observableArrayList();
       List<Pelicula> peliculasCartelera = peliculaDAO.buscarPelis();
       peliculas.addAll (peliculasCartelera);
       tablaPeliculas.setItems(peliculas);
    }
    

    private void configurarFecha() {
      fecha.setValue(LocalDate.now());

    }

    private void configurarComboBox() {
        sala.getItems().addAll(1, 2, 3, 4 , 5, 6);
    }
    
    @FXML
    private void volver() throws IOException{
         App.setRoot("PantallaInicial");
    }    
}
