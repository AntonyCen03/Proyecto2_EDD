/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author ANTONY
 */
public class MiListaSimple {
    private NodoLista pFirst;
    private int size;

    public MiListaSimple() {
        this.pFirst = null;
        this.size = 0;
    }
    
    public boolean EsVacio(){
        return this.size==0;
    }
    
    public int Size(){
        return this.size;
    }
    
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
     * @return the pFirst
     */
    public NodoLista getpFirst() {
        return pFirst;
    }

    /**
     * @param pFirst the pFirst to set
     */
    public void setpFirst(NodoLista pFirst) {
        this.pFirst = pFirst;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
 
    
}
