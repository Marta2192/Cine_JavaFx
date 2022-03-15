/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.marta.dida.cartelera;

import javafx.util.StringConverter;

/**
 *
 * @author user
 */
public class ConvertidorComboBox extends StringConverter<Director> {
    
    @Override
    public String toString(Director director){
        if(director == null){
            return "";
        }
        return director.getNombre();
        
    }
    
    
    @Override
    public Director fromString(String string) {
        return null;
    }
    
}
