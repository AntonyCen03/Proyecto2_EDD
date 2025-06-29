/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author ANTONY
 */
public class NodoPosiciones {
    private int posicion;
    private NodoPosiciones siguiente;

    public NodoPosiciones(int posicion) {
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
    public NodoPosiciones getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(NodoPosiciones siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
    
}
