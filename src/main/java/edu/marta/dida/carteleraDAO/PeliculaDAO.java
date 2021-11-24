/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.carteleraDAO;

import edu.marta.dida.cartelera.Pelicula;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class PeliculaDAO {
    
    public static final String URL_CONEXION = "jdbc:h2:./carteleraBBDD";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    
    public PeliculaDAO(){
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "CREATE TABLE IF NOT EXISTS pelicula" + 
                                 "(id INTEGER auto_increment, " + 
                                 "titulo VARCHAR(255), " +
                                 "director VARCHAR(255), " +
                                 "sinopsis VARCHAR(255), " +
                                 "sala INT(11), " +
                                 "fecha VARCHAR(255), " +
                                 "idioma INT(11) )";
                    statement.executeUpdate(sql);
       } catch (Exception e){
           e.printStackTrace();
       }
    }
    
    public void guardarOActualizar(Pelicula pelicula){
        if(pelicula.getId() == 0){
            guardar(pelicula);
        } else {
            actualizar(pelicula);
        }
    }
    
     public void guardar(Pelicula pelicula) {
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "INSERT INTO pelicula(titulo, director, sinopsis, idioma, fecha, sala) "  
                                 + "VALUES ('" + pelicula.getTitulo() + "', '" + pelicula.getDirector() + "', '" + pelicula.getSinopsis() 
                            + "', '" + pelicula.getIdioma()+ "', '" + pelicula.getFecha()+ "', '" + pelicula.getSala()+ "')";        
                    statement.executeUpdate(sql);
       } catch (Exception e){
           throw new RuntimeException("Ocurrió un error al guardar las películas: " + e.getMessage());
       }
    }
     
     public void actualizar(Pelicula pelicula) {
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "UPDATE pelicula set titulo='" + pelicula.getTitulo()+
                            "', director='" + pelicula.getDirector() + "', sinopsis='" + pelicula.getSinopsis() 
                            + "', idioma='" + pelicula.getIdioma() + "', fecha='" + pelicula.getFecha() 
                            + "', sala='" + pelicula.getSala() + "' WHERE id=" + pelicula.getId();       
                    statement.executeUpdate(sql);
       } catch (Exception e){
           throw new RuntimeException("Ocurrió un error al actualizar las películas: " + e.getMessage());
       }
    }
     
     public List<Pelicula> buscarPelis(){
        List<Pelicula> peliculas = new ArrayList<>();
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "SELECT * FROM pelicula ORDER BY id";
                    ResultSet resultSet = statement.executeQuery(sql);
                    while(resultSet.next()){
                        Pelicula pelicula = new Pelicula();
                        pelicula.setId(resultSet.getInt("id"));
                        System.err.println("HOLA =======>" + resultSet.getInt("id"));
                        pelicula.setTitulo(resultSet.getString("titulo"));
                        pelicula.setDirector(resultSet.getString("director"));
                        pelicula.setSinopsis(resultSet.getString("sinopsis"));
                        pelicula.setSala(resultSet.getInt("sala"));
                        pelicula.setFecha(resultSet.getString("fecha"));
                        pelicula.setIdioma(resultSet.getInt("idioma"));
                        
                       
                        
                        peliculas.add(pelicula);
                    }                
       } catch (Exception e){
           throw new RuntimeException("Ocurrió un error al consultar la información: " + e.getMessage());
       }
        
        return peliculas;
    }
     
     public void eliminar(Pelicula pelicula){
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "DELETE FROM pelicula WHERE id=" + pelicula.getId();
                    statement.executeUpdate(sql);
       } catch (Exception e){
           throw new RuntimeException("Ocurrió un error al eliminar la película seleccionada: " + e.getMessage());
       }
    }
    
}
