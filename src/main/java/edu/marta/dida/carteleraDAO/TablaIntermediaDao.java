/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.carteleraDAO;

import static edu.marta.dida.carteleraDAO.DirectorDAO.PASSWORD_BDD;
import static edu.marta.dida.carteleraDAO.DirectorDAO.URL_CONEXION;
import static edu.marta.dida.carteleraDAO.DirectorDAO.USUARIO_BDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class TablaIntermediaDao {
    
    public static final String URL_CONEXION = "jdbc:h2:./carteleraBBDD";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    
    public void crearTablaSiNoExiste() {
        System.err.println("Tabla intermedia creada");
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "CREATE TABLE IF NOT EXISTS pelicula_director " + 
                                 "(idpeldir INTEGER, " +
                                 "id INTEGER, " +
                                 "iddir INTEGER, " +
                                 "FOREIGN KEY (iddir) REFERENCES director (iddir), " +
                                 "FOREIGN KEY (id) REFERENCES pelicula (id));";
                                 

                    statement.executeUpdate(sql);
       } catch (Exception e){
           e.printStackTrace();
       }
    }
    
}
