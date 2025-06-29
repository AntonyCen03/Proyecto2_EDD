/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author ANTONY
 */
public class NodoLista {
    private String triplete;
    private int frecuencia;
    private NodoLista siguiente;

    public NodoLista(String triplete) {
        this.triplete = triplete;
        this.frecuencia = 1;
        this.siguiente = null;
    }
    
    
    

    /**
     * @return the triplete
     */
    public String getTriplete() {
        return triplete;
    }

    /**
     * @param triplete the triplete to set
     */
    public void setTriplete(String triplete) {
        this.triplete = triplete;
    }

    /**
     * @return the frecuencia
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * @return the siguiente
     */
    public NodoLista getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
