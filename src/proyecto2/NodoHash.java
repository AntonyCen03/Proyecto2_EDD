/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Implementación de un nodo para una tabla hash que almacena información
 * sobre tripletes de ADN, incluyendo su frecuencia y posiciones en la secuencia.
 * 
 * @author ANTONY CEN
 */
public class NodoHash {
    private String triplete;
    private int frecuencia;
    private ListaPosiciones posiciones;
    private NodoHash pNext;

    /**
     * Constructor que inicializa un nodo con un triplete y su posición inicial.
     * 
     * @param triplete Cadena de 3 caracteres (A,T,C,G)
     * @param posicion Posición inicial donde aparece el triplete
     */
    public NodoHash(String triplete, int posicion) {
        this.triplete = triplete;
        this.frecuencia = 1;
        this.posiciones = new ListaPosiciones();
        this.posiciones.agregar(posicion);
        this.pNext = null;
    }
    
    /**
     * Obtiene las posiciones del triplete como una cadena formateada.
     * 
     * @return Cadena con las posiciones separadas por comas
     */
    public String getPosicion_to_String(){
        return posiciones.toString();
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
     * Obtiene la lista de posiciones donde aparece el triplete.
     * 
     * @return Lista de posiciones
     */
    public ListaPosiciones getPosiciones() {
        return posiciones;
    }

    /**
     * Establece la lista de posiciones para este triplete.
     * 
     * @param posiciones Nueva lista de posiciones
     */
    public void setPosiciones(ListaPosiciones posiciones) {
        this.posiciones = posiciones;
    }

    /**
     * Obtiene el siguiente nodo en la lista enlazada.
     * 
     * @return Siguiente nodo o {@code null} si es el último
     */
    public NodoHash getpNext() {
        return pNext;
    }

    /**
     * Establece el siguiente nodo en la lista enlazada.
     * 
     * @param pNext Nuevo nodo siguiente
     */
    public void setpNext(NodoHash pNext) {
        this.pNext = pNext;
    }
    
    
    
    
}
