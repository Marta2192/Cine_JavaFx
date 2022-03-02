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
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
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
    DatePicker fecha;
    @FXML
    ComboBox<Integer> sala;
    @FXML
    Button volver;
    @FXML
    Button cargar;
    @FXML
    RadioButton rb1;
    @FXML
    RadioButton rb2;
    
   
    
    
    @FXML 
    TableView<Pelicula> tablaPeliculas;
    
    PeliculaDAO peliculaDAO;
    
    int id = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        configurarRadio();
        peliculaDAO = new PeliculaDAO();
        cargarPeliculasCartelera();
        configurarFecha();
        configurarComboBox();
        
       
    } 
    
    
    
    @FXML
    public void add(){
       Pelicula pelicula = new Pelicula();
            pelicula.setId(id);
            pelicula.setTitulo(titulo.getText());
            pelicula.setDirector(director.getText());
            pelicula.setSinopsis(sinopsis.getText());
            pelicula.setIdioma(rb1.isSelected()? 0 : 1); 
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
            rb1.setSelected(true);
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
           rb1.setSelected(true);
       }else {
            rb2.setSelected(true); 
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
    
    @FXML
    private void cargar() throws IOException{
         App.setRoot("PelisPorSala");
    }    

    private void configurarRadio() {
      
       rb1.setSelected(true);
        rb1.selectedProperty().addListener((obs, oldV, newV) -> {
            if(newV) {
               rb2.setSelected(!newV);
            }
        });
        rb2.selectedProperty().addListener((obs, oldV, newV) -> {
            if(newV) {
               rb1.setSelected(!newV);
            }
        });
    }
}
