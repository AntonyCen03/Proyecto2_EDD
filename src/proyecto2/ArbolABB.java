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
        this.Root=null;
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
    
    public NodoABB Buscar(int valor,NodoABB root){
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
    
    public NodoABB Insertar(int valor){
        NodoABB nodo=new NodoABB(valor);
        
        if (this.Root==null) {
            this.Root=nodo;
            return nodo;
        }
        
        NodoABB padre=null;
        NodoABB raiz=this.Root;
        
        while(raiz!=null){
            padre=raiz;
            if (nodo.getDato() < raiz.getDato()) {
                raiz=raiz.getHijoIzq();
            }else{
                raiz=raiz.getHijoDer();
            }
            nodo.setPadre(padre);   
        }
        
        if (padre==null) {
            this.Root=nodo;
        }else if (nodo.getDato() < padre.getDato()) {
            padre.setHijoIzq(nodo);
        }else{
            padre.setHijoDer(nodo);
        }
        if (nodo.getPadre()==null) {
            return this.Root;
        }
        
        return this.Root;
        
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
