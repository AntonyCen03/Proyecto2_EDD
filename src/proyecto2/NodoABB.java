/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Implementación de un nodo para un árbol binario de búsqueda (ABB).
 * Cada nodo contiene un dato de tipo {@link NodoHash} y referencias a su padre,
 * hijo izquierdo e hijo derecho.
 * 
 * @author ANTONY CEN
 */
public class NodoABB {
    private NodoHash dato;
    private NodoABB Padre;
    private NodoABB HijoIzq;
    private NodoABB HijoDer;

    /**
     * Constructor que inicializa un nodo con un dato específico.
     * 
     * @param dato El nodo hash que contendrá este nodo del árbol
     */
    public NodoABB(NodoHash dato) {
        this.dato = dato;
        this.Padre = null;
        this.HijoIzq = null;
        this.HijoDer = null;
    }

    /**
     * Obtiene el nodo padre de este nodo.
     * 
     * @return Nodo padre o {@code null} si es la raíz
     */
    public NodoABB getPadre() {
        return Padre;
    }

    /**
     * Establece el nodo padre de este nodo.
     * 
     * @param Padre Nuevo nodo padre
     */
    public void setPadre(NodoABB Padre) {
        this.Padre = Padre;
    }

    /**
     * Obtiene el hijo izquierdo de este nodo.
     * 
     * @return Nodo hijo izquierdo o {@code null} si no existe
     */
    public NodoABB getHijoIzq() {
        return HijoIzq;
    }

    /**
     * Establece el hijo izquierdo de este nodo.
     * 
     * @param HijoIzq Nuevo nodo hijo izquierdo
     */
    public void setHijoIzq(NodoABB HijoIzq) {
        this.HijoIzq = HijoIzq;
    }

    /**
     * Obtiene el hijo derecho de este nodo.
     * 
     * @return Nodo hijo derecho o {@code null} si no existe
     */
    public NodoABB getHijoDer() {
        return HijoDer;
    }

    /**
     * Establece el hijo derecho de este nodo.
     * 
     * @param HijoDer Nuevo nodo hijo derecho
     */
    public void setHijoDer(NodoABB HijoDer) {
        this.HijoDer = HijoDer;
    }

    /**
     * Obtiene el dato almacenado en este nodo.
     * 
     * @return El nodo hash con la información del triplete
     */
    public NodoHash getDato() {
        return dato;
    }

    /**
     * Establece el dato para este nodo.
     * 
     * @param dato Nuevo nodo hash a almacenar
     */
    public void setDato(NodoHash dato) {
        this.dato = dato;
    }
    
    
    
    
}
