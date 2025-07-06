/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Implementación de una lista enlazada simple para almacenar nodos de tipo {@link NodoHash}.
 * La lista permite insertar, buscar y obtener información sobre tripletes de ADN,
 * manteniendo registro de sus frecuencias y posiciones en la secuencia.
 * 
 * @author ANTONY CEN
 */
public class ListaEnlazada {
    private NodoHash cabeza;
    private int size;
    
    /**
     * Constructor que inicializa una lista enlazada vacía.
     */
    public ListaEnlazada() {
        this.cabeza = null;
        this.size = 0;
    }
    
    /**
     * Verifica si la lista está vacía.
     * 
     * @return {@code true} si la lista está vacía, {@code false} en caso contrario
     */
    public boolean EsVacio(){
        return size==0;
    }

    
    /**
     * Inserta un nuevo triplete en la lista o incrementa su frecuencia si ya existe.
     * 
     * @param triplete Cadena de 3 caracteres (A,T,C,G) a insertar
     * @param posicion Posición en la secuencia de ADN donde se encontró el triplete
     * @return {@code true} si se insertó un nuevo triplete, {@code false} si se incrementó la frecuencia de uno existente
     */
    public boolean insertar(String triplete, int posicion) {
        NodoHash actual = getCabeza();
        while (actual != null) {
            if (actual.getTriplete().equals(triplete)) {
                actual.setFrecuencia(actual.getFrecuencia() + 1);
                actual.getPosiciones().agregar(posicion);
                return false;
            }
            actual = actual.getpNext();
        }

        NodoHash nuevo = new NodoHash(triplete, posicion);
        nuevo.setpNext(getCabeza());
        setCabeza(nuevo);
        size++;
        return true; 
    }
    
    
    /**
     * Inserta un nuevo triplete con una frecuencia específica (para uso en reconstrucción de estructuras).
     * 
     * @param triplete Cadena de 3 caracteres (A,T,C,G) a insertar
     * @param frecuencia Frecuencia precalculada del triplete
     */
    public void insertar2(String triplete, int frecuencia) {
        NodoHash nuevo = new NodoHash(triplete, 0); 
        nuevo.setFrecuencia(frecuencia); 
        setCabeza(nuevo);
        size++;
    }
    
    /**
     * Busca un triplete en la lista.
     * 
     * @param triplete Cadena de 3 caracteres (A,T,C,G) a buscar
     * @return El nodo que contiene el triplete, o {@code null} si no se encuentra
     */
    public NodoHash buscar(String triplete){
        if (triplete==null || triplete.length()!=3) {
            return null;
        }
        
        NodoHash actual=getCabeza();
        while(actual!= null){
            if (actual.getTriplete().equals(triplete)) {
                return actual;
            }
            actual=actual.getpNext();
            
        }
        return null;
    }
    
    /**
     * Obtiene información detallada sobre un patrón (triplete) específico.
     * 
     * @param triplete Cadena de 3 caracteres (A,T,C,G) a consultar
     * @return Cadena con la información del patrón (frecuencia y posiciones) o mensaje de no encontrado
     */
    public String getInfoPatron(String triplete){
        NodoHash nodo=buscar(triplete);
        if (nodo!=null) {
            return "Posicion: "+triplete+
                    "\nFrecuencia: "+nodo.getFrecuencia()+
                    "\nPosiciones: "+nodo.getPosicion_to_String();
        }
        return "Patron no encontrado";
    }

    /**
     * Obtiene el nodo cabeza de la lista.
     * 
     * @return Primer nodo de la lista
     */
    public NodoHash getCabeza() {
        return cabeza;
    }

    /**
     * Establece el nodo cabeza de la lista.
     * 
     * @param cabeza Nuevo nodo cabeza
     */
    public void setCabeza(NodoHash cabeza) {
        this.cabeza = cabeza;
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
