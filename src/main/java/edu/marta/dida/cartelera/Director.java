/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.cartelera;

/**
 *
 * @author user
 */
public class Director {
    
    int idDir;
    String nombre;
    int edad;
    String nivel;

    public Director() {
    }
    
    

    public Director(int idDir, String nombre, int edad, String nivel) {
        this.idDir = idDir;
        this.nombre = nombre;
        this.edad = edad;
        this.nivel = nivel;
    }

    public int getIdDir() {
        return idDir;
    }

    public void setIdDir(int idDir) {
        this.idDir = idDir;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

   
  
}
