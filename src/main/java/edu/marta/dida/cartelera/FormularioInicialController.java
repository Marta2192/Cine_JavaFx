package edu.marta.dida.cartelera;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import edu.marta.dida.carteleraDAO.PeliculaDAO;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
        
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
    DatePicker horario;
    @FXML
    TextField sala;
    
    
    @FXML 
    TableView<Pelicula> tablaPeliculas;
    
    PeliculaDAO peliculaDAO;
    
    int id = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarChecks();
        peliculaDAO = new PeliculaDAO();
        cargarPeliculasCartelera();
        //configurarTamanhoColumnas();
        configurarFecha();
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
            pelicula.setHorario(horario.getValue().toString());
            pelicula.setSala(Integer.parseInt(sala.getText()));
            
            peliculaDAO.guardarOActualizar(pelicula);
            
            id = 0;
            
            cargarPeliculasCartelera();
            
            titulo.clear();
            director.clear();
            sinopsis.clear();
            siVoCheck.setSelected(true);
           // noVoCheck.setSelected(false);
            configurarFecha();
            sala.clear();
            
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
        String[] fecha = pelicula.getHorario().split("-");
        List<Integer> datosFecha = Arrays.asList(fecha).stream().map(dato -> Integer.parseInt(dato)).collect(Collectors.toList());
       
        horario.setValue( LocalDate.of(datosFecha.get(0), datosFecha.get(1), datosFecha.get(2)));
        sala.setText(String.valueOf(pelicula.getSala()));
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
    
//    private void configurarTamanhoColumnas() {
//        tablaPeliculas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        ObservableList<TableColumn<Pelicula, ?>> columnas = tablaPeliculas.getColumns();
//        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 15);
//        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 25);
//        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 60);
//    }

    private void configurarFecha() {
      horario.setValue(LocalDate.now());

    }
    
}
