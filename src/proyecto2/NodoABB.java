/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author ANTONY
 */
public class NodoABB {
    private int dato;
    private NodoABB Padre;
    private NodoABB HijoIzq;
    private NodoABB HijoDer;

    public NodoABB(int dato) {
        this.dato = dato;
        this.Padre = null;
        this.HijoIzq = null;
        this.HijoDer = null;
    }

    /**
     * @return the dato
     */
    public int getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(int dato) {
        this.dato = dato;
    }

    /**
     * @return the Padre
     */
    public NodoABB getPadre() {
        return Padre;
    }

    /**
     * @param Padre the Padre to set
     */
    public void setPadre(NodoABB Padre) {
        this.Padre = Padre;
    }

    /**
     * @return the HijoIzq
     */
    public NodoABB getHijoIzq() {
        return HijoIzq;
    }

    /**
     * @param HijoIzq the HijoIzq to set
     */
    public void setHijoIzq(NodoABB HijoIzq) {
        this.HijoIzq = HijoIzq;
    }

    /**
     * @return the HijoDer
     */
    public NodoABB getHijoDer() {
        return HijoDer;
    }

    /**
     * @param HijoDer the HijoDer to set
     */
    public void setHijoDer(NodoABB HijoDer) {
        this.HijoDer = HijoDer;
    }
    
    
    
    
}
