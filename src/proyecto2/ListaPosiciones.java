/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Implementación de una lista enlazada simple para almacenar posiciones de secuencias de ADN.
 * Esta clase permite almacenar y gestionar las posiciones donde aparece un triplete específico
 * en una secuencia de ADN, proporcionando métodos para agregar, obtener y visualizar las posiciones.
 * 
 * @author ANTONY CEN
 */
public class ListaPosiciones {
    private NodoPosicion cabeza;
    private int tamaño;

    /**
     * Constructor que inicializa una lista de posiciones vacía.
     */
    public ListaPosiciones() {
        this.cabeza = null;
        this.tamaño = 0;
    }
    
    
    /**
     * Obtiene el número de posiciones almacenadas en la lista.
     * 
     * @return El tamaño actual de la lista (número de posiciones)
     */
    public int tamaño(){
        return this.tamaño;
    }
    
    /**
     * Verifica si la lista de posiciones está vacía.
     * 
     * @return {@code true} si la lista no contiene posiciones, {@code false} en caso contrario
     */
    public boolean EsVacio(){
        return cabeza==null;
    }
    
    /**
     * Agrega una nueva posición al final de la lista.
     * 
     * @param posicion La posición en la secuencia de ADN a almacenar (debe ser un entero no negativo)
     * @throws IllegalArgumentException Si la posición es negativa
     */
    public void agregar(int posicion){
        NodoPosicion nuevoNodo=new NodoPosicion(posicion);
        if (this.EsVacio()) {
            cabeza=nuevoNodo;
        }else{
            NodoPosicion actual=cabeza;
            while(actual.getSiguiente()!=null){
                actual= actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        this.tamaño++;
    }
    
    /**
     * Obtiene la posición almacenada en un índice específico de la lista.
     * 
     * @param indice El índice de la posición deseada (basado en 0)
     * @return La posición en la secuencia de ADN almacenada en ese índice
     * @throws IndexOutOfBoundsException Si el índice está fuera del rango válido (0 ≤ indice < tamaño)
     */
    public int obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        NodoPosicion actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente(); 
        }
        return actual.getPosicion();
    }
    
    /**
     * Genera una representación en cadena de todas las posiciones almacenadas.
     * 
     * @return String con las posiciones separadas por comas, o cadena vacía si la lista está vacía
     */
    @Override
    public String toString(){
        StringBuilder  sb = new StringBuilder ();
        NodoPosicion actual= cabeza;
        
        while(actual!=null){
            sb.append(actual.getPosicion());
            if (actual.getSiguiente()!=null) {
                sb.append(",");
            }
            actual=actual.getSiguiente();
        }
        return sb.toString();
    }

    /**
     * Obtiene el nodo cabeza de la lista de posiciones.
     * 
     * @return Primer nodo de la lista, o {@code null} si la lista está vacía
     */
    public NodoPosicion getCabeza() {
        return cabeza;
    }

    /**
     * Establece el nodo cabeza de la lista de posiciones.
     * 
     * @param cabeza Nuevo nodo cabeza para la lista
     */
    public void setCabeza(NodoPosicion cabeza) {
        this.cabeza = cabeza;
    }

    /**
     * Obtiene el tamaño actual de la lista de posiciones.
     * 
     * @return Número de posiciones almacenadas
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * Establece el tamaño de la lista (uso interno, no recomendado para uso general).
     * 
     * @param tamaño Nuevo tamaño de la lista
     */
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    
}
