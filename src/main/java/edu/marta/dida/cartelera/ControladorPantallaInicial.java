/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.cartelera;

import edu.marta.dida.carteleraDAO.PeliculaDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


 
public class ControladorPantallaInicial implements Initializable{
    
    @FXML
    TextField administrador;
    @FXML
    PasswordField contrasenha;
    @FXML
    Button entrada;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    @FXML
    public void irSegundaPantalla() throws IOException{
       App.setRoot("FormularioInicial");
               
    }

    private void entrar() throws IOException {
        //Si llamo a este método desde el #onAction del botón revienta la aplicación
        if(administrador.getText().equals("marta")&& contrasenha.getText().equals("1234")){
            System.out.println("Datos correctos");
            
         irSegundaPantalla();
        }else{
            System.out.println("Datos erróneos");
        }
    }
    
}
