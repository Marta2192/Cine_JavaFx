/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.cartelera;

import edu.marta.dida.carteleraDAO.PeliculaDAO;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

/**
 *
 * @author user
 */



public class PelisPorSala implements Initializable{
    
    @FXML
    Button volver;
    
    @FXML
    private PieChart chart;
    
    @FXML
    Button cargar;
    
    
    
    private PeliculaDAO peliculaDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        peliculaDAO = new PeliculaDAO();
       
        cargarDatosEnChart();
    }

    @FXML
   public void cargarDatosEnChart(){
        Map<String, Integer> pelisPorSala = peliculaDAO.contarPelisPorSala();
        
        ObservableList<PieChart.Data> datosParaChart = FXCollections.observableArrayList();
        
        pelisPorSala.forEach((nombrePeli, cantidad) -> {
            PieChart.Data data = new PieChart.Data(nombrePeli, cantidad);
            datosParaChart.add(data);
        });
        chart.setData(datosParaChart);
    }
     
   @FXML
    private void volver() throws IOException{
         App.setRoot("FormularioInicial");
    }   
    
    
}
