/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Implementación de una lista enlazada simple para almacenar tripletes de ADN.
 * Proporciona operaciones básicas para agregar elementos, verificar contención,
 * y obtener índices de elementos en la lista.
 * 
 * @author ANTONY CEN
 */
public class MiListaSimple {
    private NodoLista pFirst;
    private int size;

    /**
     * Constructor que inicializa una lista vacía.
     */
    public MiListaSimple() {
        this.pFirst = null;
        this.size = 0;
    }
    
    /**
     * Verifica si la lista está vacía.
     * 
     * @return {@code true} si la lista no contiene elementos, {@code false} en caso contrario
     */
    public boolean EsVacio(){
        return this.size==0;
    }
    
    /**
     * Obtiene el número de elementos en la lista.
     * 
     * @return El tamaño actual de la lista
     */
    public int Size(){
        return this.size;
    }
    
    /**
     * Agrega un nuevo triplete al final de la lista.
     * 
     * @param triplete Cadena de 3 caracteres (A,T,C,G) a agregar
     * @throws IllegalArgumentException Si el triplete es nulo o no tiene 3 caracteres
     */
    public void agregar(String triplete){
        NodoLista nuevo= new NodoLista(triplete);
        if (getpFirst()==null) {
            setpFirst(nuevo);
            
        }else{
            NodoLista actual= getpFirst();
            while(actual.getSiguiente()!=null){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        setSize(getSize() + 1);
    }
    
    /**
     * Verifica si la lista contiene un triplete específico.
     * 
     * @param valor Triplete a buscar en la lista
     * @return {@code true} si el triplete está en la lista, {@code false} en caso contrario
     */
    public boolean contiene(String valor) {
        NodoLista actual = pFirst;
        while (actual != null) {
            if (actual.getTriplete().equals(valor)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Obtiene el índice de la primera ocurrencia de un triplete en la lista.
     * 
     * @param valor Triplete a buscar
     * @return El índice basado en 0 del triplete, o -1 si no se encuentra
     */
    public int indiceDe(String valor) {
        NodoLista actual = pFirst;
        int index = 0;
        while (actual != null) {
            if (actual.getTriplete().equals(valor)) {
                return index;
            }
            actual = actual.getSiguiente();
            index++;
        }
        return -1;
    }


    /**
     * Obtiene el primer nodo de la lista.
     * 
     * @return Primer nodo de la lista, o {@code null} si la lista está vacía
     */
    public NodoLista getpFirst() {
        return pFirst;
    }

    /**
     * Establece el primer nodo de la lista.
     * 
     * @param pFirst Nuevo nodo a establecer como primero
     */
    public void setpFirst(NodoLista pFirst) {
        this.pFirst = pFirst;
    }

    /**
     * Obtiene el tamaño actual de la lista.
     * 
     * @return Número de elementos en la lista
     */
    public int getSize() {
        return size;
    }

    /**
     * Establece el tamaño de la lista (uso interno).
     * 
     * @param size Nuevo tamaño de la lista
     */
    public void setSize(int size) {
        this.size = size;
    }
 
    
}
