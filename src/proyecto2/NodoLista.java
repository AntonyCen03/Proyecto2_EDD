/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Implementación de un nodo para una lista enlazada simple que almacena
 * tripletes de ADN y su frecuencia de aparición.
 * 
 * @author ANTONY CEN
 */
public class NodoLista {
    private String triplete;
    private int frecuencia;
    private NodoLista siguiente;

    /**
    * Implementación de un nodo para una lista enlazada simple que almacena
    * tripletes de ADN y su frecuencia de aparición.
    * 
    * @author ANTONY
    */
    public NodoLista(String triplete) {
        this.triplete = triplete;
        this.frecuencia = 1;
        this.siguiente = null;
    }

    /**
     * Obtiene el triplete almacenado en este nodo.
     * 
     * @return Cadena de 3 caracteres (A,T,C,G)
     */
    public String getTriplete() {
        return triplete;
    }

    /**
     * Establece el triplete para este nodo.
     * 
     * @param triplete Nueva cadena de 3 caracteres (A,T,C,G)
     */
    public void setTriplete(String triplete) {
        this.triplete = triplete;
    }

    /**
     * Obtiene la frecuencia del triplete.
     * 
     * @return Número de veces que aparece el triplete
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * Establece la frecuencia del triplete.
     * 
     * @param frecuencia Nueva frecuencia del triplete
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * Obtiene el siguiente nodo en la lista enlazada.
     * 
     * @return Siguiente nodo o {@code null} si es el último
     */
    public NodoLista getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo en la lista enlazada.
     * 
     * @param siguiente Nuevo nodo siguiente
     */
    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
