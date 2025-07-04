/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author ANTONY
 */
public class TablaHashADN {
    private int capacidad;
    private ListaEnlazada[] tabla;
    private int colisionesTotales;

    public TablaHashADN(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new ListaEnlazada[capacidad];
        this.colisionesTotales=0;
        
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new ListaEnlazada();
        }
        
    }
    
    // Función hash para tripletes de ADN
    private int funcionHash(String triplete){
        if (triplete==null || triplete.length()!=3) {
            return 0;
        }
        
        //Convertir a ARN (cambiar T por U)
        String tripleteARN = triplete.replace('T', 'U');
        
        //Calcular valor hash basado en los 3 nucleotidos
        int hash=0;
        for (int i = 0; i < 3; i++) {
            char c = tripleteARN.charAt(i);
            int valorBase=0;
            switch(c){
                case 'A': valorBase= 0; break;
                case 'U': valorBase= 1; break;
                case 'C': valorBase= 2; break;
                case 'G': valorBase= 3; break;
                default: valorBase=0;
            }
            hash = (hash << 2) | valorBase;
        }
        return Math.abs(hash % capacidad);
    }
    
    public void insertar(String triplete, int posicion) {
        if (triplete == null || triplete.length() != 3) {
            return;
        }
        
        int indice = funcionHash(triplete);
        
        // Verificar si habrá colisión (si la lista no está vacía)
        if (!tabla[indice].EsVacio()) {
            colisionesTotales++;
        }
        
        tabla[indice].insertar(triplete, posicion);
    }

    public NodoHash buscar(String triplete) {
        if (triplete == null || triplete.length() != 3) {
            return null;
        }
        
        int indice = funcionHash(triplete);
        return tabla[indice].buscar(triplete);
    }
    
    public String generarReporteColisiones() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("Total de colisiones: ").append(colisionesTotales).append("\n");
        
        for (int i = 0; i < capacidad; i++) {
            if (tabla[i].getSize() > 1) {
                reporte.append("Índice ").append(i).append(": ")
                      .append(tabla[i].getSize()).append(" colisiones\n");
            }
        }
        
        return reporte.toString();
    }
    
    // Métodos adicionales para los requisitos del proyecto
    public NodoHash getPatronMasFrecuente() {
        NodoHash masFrecuente = null;
        for (ListaEnlazada lista : tabla) {
            NodoHash actual = lista.getCabeza();
            while (actual != null) {
                if (masFrecuente == null || actual.getFrecuencia() > masFrecuente.getFrecuencia()) {
                    masFrecuente = actual;
                }
                actual = actual.getpNext();
            }
        }
        return masFrecuente;
    }

    public NodoHash getPatronMenosFrecuente() {
        NodoHash menosFrecuente = null;
        for (ListaEnlazada lista : tabla) {
            NodoHash actual = lista.getCabeza();
            while (actual != null) {
                if (menosFrecuente == null || actual.getFrecuencia() < menosFrecuente.getFrecuencia()) {
                    menosFrecuente = actual;
                }
                actual = actual.getpNext();
            }
        }
        return menosFrecuente;
    }
    
    public ArbolABB construirArbolFrecuencias() {
    ArbolABB arbol = new ArbolABB();
    
    for (ListaEnlazada lista : tabla) {
        NodoHash actual = lista.getCabeza();
        while (actual != null) {
            arbol.Insertar(actual);
            actual = actual.getpNext();
        }
    }
    
    return arbol;
    }
    
}
