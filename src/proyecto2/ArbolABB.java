/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author ANTONY
 */
public class ArbolABB {
    private NodoABB Root;

    public ArbolABB() {
        this.Root=null;
    }
    
    public boolean EsVacio(NodoABB node){
        return node==null;
    }
    
    public void Vaciar(){
        this.setRoot(null);
    }
    
    public String PreOrden(NodoABB root, String cadena){
        if (root!=null) {
            cadena += root.getDato()+",";
            cadena = PreOrden(root.getHijoIzq(), cadena);
            cadena = PreOrden(root.getHijoDer(), cadena);
        }
        return cadena;
    }
    
    public String PosOrden(NodoABB root, String cadena){
        if (root!=null) {
            cadena=PosOrden(root.getHijoIzq(), cadena);
            cadena=PosOrden(root.getHijoDer(), cadena);
            cadena+=root.getDato()+",";
        }
        return cadena;
    }
    
    public String InOrden(NodoABB root, String cadena){
        if (root!=null) {
            cadena=InOrden(root.getHijoIzq(),cadena);
            cadena+=root.getDato()+",";
            cadena=InOrden(root.getHijoDer(), cadena);
        }
        return cadena;
    }
    
    public NodoABB Buscar(NodoHash valor,NodoABB root){
        if (this.EsVacio(root)) {
            return null;
        }else{
            if (root.getDato()==valor) {
                return root;
            }else{
                if (valor<root.getDato()) {
                    return Buscar(valor,root.getHijoIzq());
                }else{
                    return Buscar(valor, root.getHijoDer());
                }
            }
        } 
    }
    
    // Modificar el método de inserción para comparar frecuencias
    public NodoABB Insertar(NodoHash valor) {
        NodoABB nodo = new NodoABB(valor);
        
        if (this.getRoot() == null) {
            this.setRoot(nodo);
            return nodo;
        }
        
        NodoABB padre = null;
        NodoABB raiz = this.getRoot();
        
        while(raiz != null) {
            padre = raiz;
            if (nodo.getDato().getFrecuencia() < raiz.getDato().getFrecuencia()) {
                raiz = raiz.getHijoIzq();
            } else {
                raiz = raiz.getHijoDer();
            }
            nodo.setPadre(padre);   
        }
        
        // Resto de la inserción igual
    }

    // Método para obtener patrones ordenados por frecuencia
    public String inOrdenFrecuencias() {
        return InOrden(this.getRoot(), new StringBuilder()).toString();
    }

    private StringBuilder InOrden(NodoABB root, StringBuilder sb) {
        if (root != null) {
            InOrden(root.getHijoIzq(), sb);
            NodoHash nodoHash = root.getDato();
            sb.append(nodoHash.getTriplete())
              .append(" - Frecuencia: ")
              .append(nodoHash.getFrecuencia())
              .append("\n");
            InOrden(root.getHijoDer(), sb);
        }
        return sb;
    }

    // Método para encontrar el patrón más frecuente (el máximo en BST)
    public NodoHash getPatronMasFrecuente() {
        if (this.getRoot() == null) return null;
        
        NodoABB actual = this.getRoot();
        while (actual.getHijoDer() != null) {
            actual = actual.getHijoDer();
        }
        return actual.getDato();
    }

    // Método para encontrar el patrón menos frecuente (el mínimo en BST)
    public NodoHash getPatronMenosFrecuente() {
        if (this.getRoot() == null) return null;
        
        NodoABB actual = this.getRoot();
        while (actual.getHijoIzq() != null) {
            actual = actual.getHijoIzq();
        }
        return actual.getDato();
    }

    /**
     * @return the Root
     */
    public NodoABB getRoot() {
        return Root;
    }

    /**
     * @param Root the Root to set
     */
    public void setRoot(NodoABB Root) {
        this.Root = Root;
    }
    
    
    
}
    


    
    
   
