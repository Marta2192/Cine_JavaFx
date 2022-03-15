/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.cartelera;

/**
 *
 * @author user
 */
public class Pelicula {
    
    int id;
    String titulo;
    Director director;
    String sinopsis;
    int idioma;
    String fecha;
    int sala;

    public Pelicula() {
    }

    public Pelicula(int id, String titulo, Director director, String sinopsis, int idioma, String fecha, int sala) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.sinopsis = sinopsis;
        this.idioma = idioma;
        this.fecha = fecha;
        this.sala = sala;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

   

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getIdioma() {
        return idioma;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    
    
  
    
   
}
