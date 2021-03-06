/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.carteleraDAO;

import edu.marta.dida.cartelera.Director;
import edu.marta.dida.cartelera.Pelicula;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class PeliculaDAO {
    
    public static final String URL_CONEXION = "jdbc:h2:./carteleraBBDD";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    DirectorDAO dirDao = new DirectorDAO();
    
 

    public void crearTablaSiNoExiste() {
        System.err.println("Tabla pelicula creada");
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "CREATE TABLE IF NOT EXISTS pelicula" + 
                                 "(id INTEGER auto_increment, " + 
                                 "titulo VARCHAR(255), " +
                                 "director INT(11), " +
                                 "sinopsis VARCHAR(255), " +
                                 "sala INT(11), " +
                                 "fecha VARCHAR(255), " +
                                 "idioma INT(11), " + 
                                 "FOREIGN KEY (director) REFERENCES director (iddir));";
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
                                 + "VALUES ('" + pelicula.getTitulo() + "', '" + pelicula.getDirector().getIdDir() + "', '" + pelicula.getSinopsis() 
                            + "', '" + pelicula.getIdioma()+ "', '" + pelicula.getFecha()+ "', '" + pelicula.getSala()+ "')";        
                    statement.executeUpdate(sql);
                    
       } catch (Exception e){
           throw new RuntimeException("Ocurri?? un error al guardar las pel??culas: " + e.getMessage());
       }
    }
     
     public void actualizar(Pelicula pelicula) {
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "UPDATE pelicula set titulo='" + pelicula.getTitulo()
                            + "', director='" + pelicula.getDirector().getIdDir()
                            + "', sinopsis='" + pelicula.getSinopsis() 
                            + "', idioma='" + pelicula.getIdioma() + "', fecha='" + pelicula.getFecha() 
                            + "', sala='" + pelicula.getSala() + "' WHERE id=" + pelicula.getId();       
                    statement.executeUpdate(sql);
       } catch (Exception e){
           throw new RuntimeException("Ocurri?? un error al actualizar las pel??culas: " + e.getMessage());
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
                        pelicula.setTitulo(resultSet.getString("titulo"));
                        Director director = new DirectorDAO().encontrarDirectorPorId(resultSet.getInt("director"));
                        
                        pelicula.setDirector(director); 
                        pelicula.setSinopsis(resultSet.getString("sinopsis"));
                        pelicula.setSala(resultSet.getInt("sala"));
                        pelicula.setFecha(resultSet.getString("fecha"));
                        pelicula.setIdioma(resultSet.getInt("idioma"));
                        
                       
                        
                        peliculas.add(pelicula);
                    }                
       } catch (Exception e){
           throw new RuntimeException("Ocurri?? un error al consultar la informaci??n: " + e.getMessage());
       }
        
        return peliculas;
    }
     
    
     
     public void eliminar(Pelicula pelicula){
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "DELETE FROM pelicula WHERE id=" + pelicula.getId();
                    statement.executeUpdate(sql);
       } catch (Exception e){
           throw new RuntimeException("Ocurri?? un error al eliminar la pel??cula seleccionada: " + e.getMessage());
       }
    }

     
   public Map<String, Integer> contarPelisPorSala() {
       List<Pelicula> peliculas = buscarPelis();
       Map<String, Integer> pelisPorSala = new HashMap<>();
       try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)){
                    Statement statement = conexionDB.createStatement();
                    String sql = "SELECT sala, count(*) as cantidad FROM pelicula GROUP BY sala";
                    ResultSet resultSet = statement.executeQuery(sql);
                    while(resultSet.next()){
                        int sala = resultSet.getInt("sala");
                        int cantidadPeliculas = resultSet.getInt("cantidad");
                        
                        for (Pelicula pelicula : peliculas) {
                            if(pelicula.getSala() == sala){
                                pelisPorSala.put("Sala " + String.valueOf(pelicula.getSala()), cantidadPeliculas);
                                break;
                            }
                        }
                                
                    }

          } catch (Exception e){
           throw new RuntimeException("Ocurri?? un error al contar el n??mero de pelis por sala: " + e.getMessage());
       }
        return pelisPorSala;
    }
   
  
    
}
