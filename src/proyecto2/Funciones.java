/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author ANTONY
 */
public class Funciones {
    public static boolean isNumeric(String cadena){
        boolean resultado;
        try{
            Integer.parseInt(cadena);
            resultado=true;
        }catch(NumberFormatException exception){
            resultado=false;
        }
        return resultado;
    }
    

    
}
