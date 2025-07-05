/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author ANTONY
 */
public class ListaEnlazada {
    private NodoHash cabeza;
    private int size;

    public ListaEnlazada() {
        this.cabeza = null;
        this.size = 0;
    }
    
    public boolean EsVacio(){
        return size==0;
    }

    
//    public void insertar(String triplete, int posicion){
//        NodoHash actual=getCabeza();
//        while (actual != null){
//            if (actual.getTriplete().equals(triplete)) {
//                actual.setFrecuencia(actual.getFrecuencia()+1);
//                actual.getPosiciones().agregar(posicion);
//                return;
//            }
//            actual=actual.getpNext();
//        }
//        
//        //Si no existe agregar nuevo nodo
//        NodoHash nuevo=new NodoHash(triplete, posicion);
//        nuevo.setpNext(getCabeza());
//        setCabeza(nuevo);
//        size++;
//        
//    }
    
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

        // Si no existe, agregar nuevo nodo
        NodoHash nuevo = new NodoHash(triplete, posicion);
        nuevo.setpNext(getCabeza());
        setCabeza(nuevo);
        size++;
        return true; 
    }
    
    public void insertar2(String triplete, int frecuencia) {
        NodoHash nuevo = new NodoHash(triplete, 0); 
        nuevo.setFrecuencia(frecuencia); 
        setCabeza(nuevo);
        size++;
    }
    
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
     * @return the cabeza
     */
    public NodoHash getCabeza() {
        return cabeza;
    }

    /**
     * @param cabeza the cabeza to set
     */
    public void setCabeza(NodoHash cabeza) {
        this.cabeza = cabeza;
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
