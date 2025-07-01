/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author ANTONY
 */
public class NodoHash {
    private String triplete;
    private int frecuencia;
    private ListaPosiciones posiciones;
    private NodoHash pNext;

    public NodoHash(String triplete, int posicion) {
        this.triplete = triplete;
        this.frecuencia = 1;
        this.posiciones = new ListaPosiciones();
        this.posiciones.agregar(posicion);
        this.pNext = null;
    }
    
    public String getPosicion_to_String(){
        return posiciones.toString();
    }

    /**
     * @return the triplete
     */
    public String getTriplete() {
        return triplete;
    }

    /**
     * @param triplete the triplete to set
     */
    public void setTriplete(String triplete) {
        this.triplete = triplete;
    }

    /**
     * @return the frecuencia
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * @return the posiciones
     */
    public ListaPosiciones getPosiciones() {
        return posiciones;
    }

    /**
     * @param posiciones the posiciones to set
     */
    public void setPosiciones(ListaPosiciones posiciones) {
        this.posiciones = posiciones;
    }

    /**
     * @return the pNext
     */
    public NodoHash getpNext() {
        return pNext;
    }

    /**
     * @param pNext the pNext to set
     */
    public void setpNext(NodoHash pNext) {
        this.pNext = pNext;
    }
    
    
    
    
}
