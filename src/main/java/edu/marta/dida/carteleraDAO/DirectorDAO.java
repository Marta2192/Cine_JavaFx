/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.carteleraDAO;

import edu.marta.dida.cartelera.Director;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class DirectorDAO {
    
    public static final String URL_CONEXION = "jdbc:h2:./carteleraBBDD";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public void crearTablaSiNoExiste() {
        System.err.println("Tabla director creada");
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "CREATE TABLE IF NOT EXISTS director" + 
                                 "(iddir INTEGER auto_increment, " +
                                 "nombre VARCHAR(255), " +
                                 "edad INTEGER(3), " +
                                 "nivel VARCHAR(45));";
                    statement.executeUpdate(sql);
       } catch (Exception e){
           e.printStackTrace();
       }
    }

    
    public void guardarOActualizar(Director director){
        if(director.getIdDir()== 0){
            guardar(director);
        } else {
            actualizar(director);
        }
    }

    public void guardar(Director director) {
        System.err.println("DENTRO DE GUARDAR DIRECTOR");
       try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "INSERT INTO director(nombre, edad, nivel) "  
                                 + "VALUES ('" + director.getNombre()+ "', '" + director.getEdad() + "', '" + director.getNivel() + "')";        
                    statement.executeUpdate(sql);
            System.err.println("sql");
       } catch (Exception e){
           throw new RuntimeException("Ocurrió un error al guardar los directores: " + e.getMessage());
       }
    }

    public void actualizar(Director director) {
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "UPDATE director set nombre='" + director.getNombre()+
                            "', edad='" + director.getEdad() + "', nivel='" + director.getNivel()
                            + "' WHERE iddir=" + director.getIdDir();       
                    statement.executeUpdate(sql);
       } catch (Exception e){
           throw new RuntimeException("Ocurrió un error al actualizar los directores: " + e.getMessage());
       }
    }
    
    public List<Director> buscarDirectores(){
        List<Director> directores = new ArrayList<>();
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "SELECT * FROM director ORDER BY iddir";
                    ResultSet resultSet = statement.executeQuery(sql);
                    while(resultSet.next()){
                        Director director = new Director();
                        director.setIdDir(resultSet.getInt("iddir"));
                        director.setNombre(resultSet.getString("nombre"));
                        director.setEdad(resultSet.getInt("edad"));
                        director.setNivel(resultSet.getString("nivel"));
                              
                        directores.add(director);
                    }                
       } catch (Exception e){
           throw new RuntimeException("Ocurrió un error al consultar la información: " + e.getMessage());
       }
        
        directores.forEach(d -> System.out.println("dire = " + d.getNombre()));
        return directores;
    }
     
     public void eliminar(Director director){
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "DELETE FROM director WHERE iddir=" + director.getIdDir();
                    statement.executeUpdate(sql);
       } catch (Exception e){
           throw new RuntimeException("Ocurrió un error al eliminar el director seleccionado: " + e.getMessage());
       }
    }
     
     public Director encontrarDirectorPorId(int id) throws SQLException {
         
         Director director = new Director();
         
         
         List<Director> directores = new ArrayList<>();
         try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "SELECT * FROM director WHERE idDir =" + id;
                    ResultSet resultSet = statement.executeQuery(sql);
                    while(resultSet.next()){
                        
                        director.setIdDir(resultSet.getInt("iddir"));
                        director.setNombre(resultSet.getString("nombre"));
                        director.setEdad(resultSet.getInt("edad"));
                        director.setNivel(resultSet.getString("nivel"));
                              
                        directores.add(director);
                    }                
         
         return director;
     } 
    }
}