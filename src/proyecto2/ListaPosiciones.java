/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import javax.swing.JOptionPane;

/**
 *
 * @author ANTONY
 */
public class ListaPosiciones {
    private NodoPosicion cabeza;
    private int tamaño;

    public ListaPosiciones() {
        this.cabeza = null;
        this.tamaño = 0;
    }
    
    
    /**
     * @return El número de posiciones almacenadas
     */
    public int tamaño(){
        return this.tamaño;
    }
    
    /**
     * @return Si la lista es vacio
     */
    public boolean EsVacio(){
        return cabeza==null;
    }
    
    /**
     * Agrega una nueva posición a la lista
     * @param posicion La posición en la secuencia de ADN
     */
    public void agregar(int posicion){
        NodoPosicion nuevoNodo=new NodoPosicion(posicion);
        if (this.EsVacio()) {
            cabeza=nuevoNodo;
        }else{
            NodoPosicion actual=cabeza;
            while(actual.getSiguiente()!=null){
                actual.setSiguiente(actual);
            }
            actual.setSiguiente(nuevoNodo);
        }
        this.tamaño++;
    }
    
    /**
     * Obtiene la posición en un índice específico
     * @param indice El índice de la posición deseada
     * @return La posición en la secuencia de ADN
     */
    public int obtener(int indice){
        if (indice < 0 || indice>= tamaño) {
            JOptionPane.showMessageDialog(null, "Indice fuera de rango"+indice);
        }
        NodoPosicion actual=cabeza;
        for (int i = 0; i < indice; i++) {
            actual.getSiguiente();
        }
        return actual.getPosicion();
    }
    
    /**
     * Convierte la lista de posiciones a un String
     * @return String con las posiciones separadas por comas
     */
    public String toString(){
        StringBuilder  sb = new StringBuilder ();
        NodoPosicion actual= cabeza;
        
        while(actual!=null){
            sb.append(actual.getPosicion());
            if (actual.getSiguiente()!=null) {
                sb.append(", ");
            }
            actual.getSiguiente();
        }
        return sb.toString();
    }

    /**
     * @return the cabeza
     */
    public NodoPosicion getCabeza() {
        return cabeza;
    }

    /**
     * @param cabeza the cabeza to set
     */
    public void setCabeza(NodoPosicion cabeza) {
        this.cabeza = cabeza;
    }

    /**
     * @return the tamaño
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * @param tamaño the tamaño to set
     */
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    
}
