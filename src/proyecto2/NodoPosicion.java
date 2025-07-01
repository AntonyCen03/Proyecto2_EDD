/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author ANTONY
 */
public class NodoPosicion {
    private int posicion;
    private NodoPosicion siguiente;

    public NodoPosicion(int posicion) {
        this.posicion = posicion;
        this.siguiente = null;
    }

    /**
     * @return the posicion
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the siguiente
     */
    public NodoPosicion getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoPosicion siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
    
}
