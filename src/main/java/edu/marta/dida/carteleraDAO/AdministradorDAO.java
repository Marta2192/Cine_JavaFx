/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.carteleraDAO;

import edu.marta.dida.cartelera.Administrador;
import edu.marta.dida.cartelera.ControladorPantallaInicial;
import edu.marta.dida.cartelera.Pelicula;
import static edu.marta.dida.carteleraDAO.PeliculaDAO.PASSWORD_BDD;
import static edu.marta.dida.carteleraDAO.PeliculaDAO.URL_CONEXION;
import static edu.marta.dida.carteleraDAO.PeliculaDAO.USUARIO_BDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class AdministradorDAO {
    
    public static final String URL_CONEXION = "jdbc:h2:./carteleraBBDD";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
     public AdministradorDAO(){
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "CREATE TABLE IF NOT EXISTS administrador" + 
                                 "(nombreAdm VARCHAR(255), " +
                                 "contrasenha VARCHAR(255))";
                    statement.executeUpdate(sql);
       } catch (Exception e){
           e.printStackTrace();
       }
    }
    
    public void guardarUser(Administrador administrador) {
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "INSERT INTO administrador(nombreAdm, contrasenha) "  
                                 + "VALUES ('marta','1234')";     
                    statement.executeUpdate(sql);
       } catch (Exception e){
           throw new RuntimeException("Ocurrió un error al guardar al administrador: " + e.getMessage());
       }
    }
    
    public boolean loguearse(Administrador administrador) {
        boolean loginOk = false;
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                   
                    
                    Statement statement = conexionDB.createStatement();
                    String sql = "SELECT contrasenha FROM administrador WHERE nombreAdm LIKE'" + administrador.getNombreAdm() + "'";
                    
                    ResultSet resultados = statement.executeQuery(sql);
                    
                  
                   while(resultados.next()) {
                       
                        if(resultados.getString(1).equals(administrador.getContrasenha())){
                            
                            loginOk = true;
                        }
                    }
                    
                    
                    
       } catch (Exception e){
           throw new RuntimeException("Ocurrió un error al guardar al loguearse: " + e.getMessage());
       }
        return loginOk;
    }
    
    
    
}
