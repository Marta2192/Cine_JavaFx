/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.cartelera;

/**
 *
 * @author user
 */
public class Administrador {
    
    String nombreAdm;
    String contrasenha;

    public Administrador(String nombreAdm, String contrasenha) {
        this.nombreAdm = nombreAdm;
        this.contrasenha = contrasenha;
    }

    public String getNombreAdm() {
        return nombreAdm;
    }

    public void setNombreAdm(String nombreAdm) {
        this.nombreAdm = nombreAdm;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    
    
}
