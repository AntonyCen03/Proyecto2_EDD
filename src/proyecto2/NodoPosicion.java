/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Implementación de un nodo para almacenar posiciones en una secuencia de ADN.
 * Forma parte de una lista enlazada simple de posiciones.
 * 
 * @author ANTONY
 */
public class NodoPosicion {
    private int posicion;
    private NodoPosicion siguiente;

    /**
     * Constructor que inicializa un nodo con una posición específica.
     * 
     * @param posicion Índice en la secuencia de ADN (debe ser no negativo)
     */
    public NodoPosicion(int posicion) {
        this.posicion = posicion;
        this.siguiente = null;
    }

    /**
     * Obtiene la posición almacenada en este nodo.
     * 
     * @return Índice en la secuencia de ADN
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Establece la posición para este nodo.
     * 
     * @param posicion Nuevo índice en la secuencia (debe ser no negativo)
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * Obtiene el siguiente nodo en la lista de posiciones.
     * 
     * @return Siguiente nodo o {@code null} si es el último
     */
    public NodoPosicion getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo en la lista de posiciones.
     * 
     * @param siguiente Nuevo nodo siguiente
     */
    public void setSiguiente(NodoPosicion siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
    
}
