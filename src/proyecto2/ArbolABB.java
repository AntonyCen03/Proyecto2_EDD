/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Implementación de un árbol binario de búsqueda (ABB) que almacena nodos de tipo {@link NodoHash}.
 * El árbol está ordenado principalmente por la frecuencia de los nodos, y en caso de frecuencias iguales,
 * se mantienen como nodos distintos que pueden ser buscados por su triplete.
 * 
 * @author ANTONY CEN
 */
public class ArbolABB {
    private NodoABB Root;

    /**
     * Constructor que inicializa un árbol vacío.
     */
    public ArbolABB() {
        this.Root=null;
    }
    
    /**
     * Verifica si un nodo es nulo.
     * 
     * @param node Nodo a verificar
     * @return {@code true} si el nodo es nulo, {@code false} en caso contrario
     */
    public boolean EsVacio(NodoABB node){
        return node==null;
    }
    
    /**
     * Vacía el árbol eliminando todas las referencias a nodos.
     */
    public void Vaciar(){
        this.setRoot(null);
    }
    
    /**
     * Recorre el árbol en preorden (raíz, izquierdo, derecho) y construye una cadena con los datos.
     * 
     * @param root Nodo raíz del subárbol a recorrer
     * @param cadena Cadena acumuladora para el resultado
     * @return Cadena con los datos de los nodos en preorden separados por comas
     */
    public String PreOrden(NodoABB root, String cadena){
        if (root!=null) {
            cadena += root.getDato()+",";
            cadena = PreOrden(root.getHijoIzq(), cadena);
            cadena = PreOrden(root.getHijoDer(), cadena);
        }
        return cadena;
    }
    
    /**
     * Recorre el árbol en posorden (izquierdo, derecho, raíz) y construye una cadena con los datos.
     * 
     * @param root Nodo raíz del subárbol a recorrer
     * @param cadena Cadena acumuladora para el resultado
     * @return Cadena con los datos de los nodos en posorden separados por comas
     */
    public String PosOrden(NodoABB root, String cadena){
        if (root!=null) {
            cadena=PosOrden(root.getHijoIzq(), cadena);
            cadena=PosOrden(root.getHijoDer(), cadena);
            cadena+=root.getDato()+",";
        }
        return cadena;
    }
    
    /**
     * Recorre el árbol en inorden (izquierdo, raíz, derecho) y construye una cadena con los datos.
     * 
     * @param root Nodo raíz del subárbol a recorrer
     * @param cadena Cadena acumuladora para el resultado
     * @return Cadena con los datos de los nodos en inorden separados por comas
     */
    public String InOrden(NodoABB root, String cadena){
        if (root!=null) {
            cadena=InOrden(root.getHijoIzq(),cadena);
            cadena+=root.getDato()+",";
            cadena=InOrden(root.getHijoDer(), cadena);
        }
        return cadena;
    }
    
    /**
     * Busca un nodo en el árbol que contenga un {@link NodoHash} específico.
     * La búsqueda considera primero la frecuencia y luego el triplete en caso de colisiones.
     * 
     * @param valor NodoHash a buscar
     * @param root Nodo raíz del subárbol donde se realiza la búsqueda
     * @return El nodo encontrado o {@code null} si no existe
     */
    public NodoABB Buscar(NodoHash valor, NodoABB root) {
        if (this.EsVacio(root)) {
            return null;
        }

        // Comparar por frecuencia (que es cómo está ordenado el árbol)
        int comparacion = Integer.compare(valor.getFrecuencia(), root.getDato().getFrecuencia());

        if (comparacion == 0) {
            // Misma frecuencia, verificar si es el mismo triplete
            if (root.getDato().getTriplete().equals(valor.getTriplete())) {
                return root;
            }
            // Si no es el mismo triplete, buscar en ambos subárboles
            NodoABB encontrado = Buscar(valor, root.getHijoIzq());
            return (encontrado != null) ? encontrado : Buscar(valor, root.getHijoDer());
        } 
        else if (comparacion < 0) {
            return Buscar(valor, root.getHijoIzq());
        } 
        else {
            return Buscar(valor, root.getHijoDer());
        }
    }
    
    /**
     * Busca un nodo en el árbol por su triplete asociado.
     * Recorre todo el árbol (no optimizado por frecuencia).
     * 
     * @param triplete Cadena del triplete a buscar
     * @param root Nodo raíz del subárbol donde se realiza la búsqueda
     * @return El nodo encontrado o {@code null} si no existe
     */
    public NodoABB buscarPorTriplete(String triplete, NodoABB root) {
        if (this.EsVacio(root)) {
            return null;
        }

        // Buscar primero en el subárbol izquierdo
        NodoABB encontrado = buscarPorTriplete(triplete, root.getHijoIzq());
        if (encontrado != null) {
            return encontrado;
        }

        // Verificar el nodo actual
        if (root.getDato().getTriplete().equals(triplete)) {
            return root;
        }

        // Finalmente buscar en el subárbol derecho
        return buscarPorTriplete(triplete, root.getHijoDer());
    }
    
    /**
     * Inserta un nuevo {@link NodoHash} en el árbol manteniendo el orden por frecuencia.
     * 
     * @param valor NodoHash a insertar
     * @return El nodo recién insertado
     */
    public NodoABB Insertar(NodoHash valor) {
        NodoABB nuevoNodo = new NodoABB(valor);
    
        if (this.Root == null) {
            this.Root = nuevoNodo;
            return nuevoNodo;
        }

        NodoABB actual = this.Root;
        NodoABB padre = null;

        while (actual != null) {
            padre = actual;
            if (valor.getFrecuencia() < actual.getDato().getFrecuencia()) {
                actual = actual.getHijoIzq();
            } else {
                actual = actual.getHijoDer();
            }
        }

        if (valor.getFrecuencia() < padre.getDato().getFrecuencia()) {
            padre.setHijoIzq(nuevoNodo);
        } else {
            padre.setHijoDer(nuevoNodo);
        }
        nuevoNodo.setPadre(padre);

        return nuevoNodo;
    }

    /**
     * Genera una representación en cadena de los patrones ordenados por frecuencia (inorden).
     * 
     * @return Cadena con formato "triplete - Frecuencia: X" para cada nodo, ordenados ascendentemente por frecuencia
     */
    public String inOrdenFrecuencias() {
        return InOrden(this.getRoot(), new StringBuilder()).toString();
    }

    /**
     * Método auxiliar para recorrido inorden que construye la cadena de frecuencias.
     * 
     * @param root Nodo raíz del subárbol
     * @param sb StringBuilder acumulador para el resultado
     * @return StringBuilder con la representación de los nodos
     */
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

    /**
     * Obtiene el patrón (NodoHash) con la frecuencia más alta en el árbol.
     * 
     * @return NodoHash con mayor frecuencia o {@code null} si el árbol está vacío
     */
    public NodoHash getPatronMasFrecuente() {
        if (this.getRoot() == null) return null;
        
        NodoABB actual = this.getRoot();
        while (actual.getHijoDer() != null) {
            actual = actual.getHijoDer();
        }
        return actual.getDato();
    }

    /**
     * Obtiene el patrón (NodoHash) con la frecuencia más baja en el árbol.
     * 
     * @return NodoHash con menor frecuencia o {@code null} si el árbol está vacío
     */
    public NodoHash getPatronMenosFrecuente() {
        if (this.getRoot() == null) return null;
        
        NodoABB actual = this.getRoot();
        while (actual.getHijoIzq() != null) {
            actual = actual.getHijoIzq();
        }
        return actual.getDato();
    }

    /**
     * Obtiene la raíz del árbol.
     * 
     * @return Nodo raíz del árbol
     */
    public NodoABB getRoot() {
        return Root;
    }

    /**
     * Establece una nueva raíz para el árbol.
     * 
     * @param Root Nueva raíz del árbol
     */
    public void setRoot(NodoABB Root) {
        this.Root = Root;
    }
    
       
}
    


    
    
   
