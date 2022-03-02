/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.cartelera;

import edu.marta.dida.carteleraDAO.AdministradorDAO;
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
import javafx.scene.layout.BorderPane;


 
public class ControladorPantallaInicial implements Initializable{
    
    @FXML
    TextField tfAdministrador;
    @FXML
    PasswordField pfContrasenha;
    @FXML
    Button entrada;
    Administrador administrador;
    AdministradorDAO administradorDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        administradorDAO = new AdministradorDAO();
        administradorDAO.guardarUser(administrador);
        //NO FUNCIONA
//        BorderPane root = new BorderPane();
//        root.setStyle("-fx-background-image:@../../../../imagenInicial/portada.jpg");
    } 
    
    @FXML
    public void irSegundaPantalla() throws IOException{
        
        if(comprobarAdm()==true){
            App.setRoot("FormularioInicial");
        }
       
               
    }

    private boolean comprobarAdm() {
        
        administradorDAO = new AdministradorDAO();
        Administrador objectoAdministrador = new Administrador(tfAdministrador.getText(), pfContrasenha.getText());
        boolean loginOk = administradorDAO.loguearse(objectoAdministrador);
        return loginOk;
    }
      
   
    
}
