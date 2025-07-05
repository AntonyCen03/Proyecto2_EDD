/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author ANTONY
 */
public class Proyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Configurar encoding para UTF-8
    try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error configurando encoding: " + e.getMessage());
        }
        
        
        // Pruebas unitarias
        pruebaTablaHash();
        pruebaArbolABB();
        pruebaListas();
        
        Interfaz_1 ventana=new Interfaz_1();
        ventana.show();
        ventana.setLocationRelativeTo(null);

        
    }
    
    public static void pruebaTablaHash() {
        System.out.println("=== INICIO PRUEBA TABLA HASH ===");

        // 1. Crear tabla hash pequeña
        TablaHashADN tabla = new TablaHashADN(11); // Tamaño primo para mejor distribución

        // 2. Insertar tripletes de prueba
        tabla.insertar("ATG", 0);
        tabla.insertar("TGC", 1);
        tabla.insertar("GCG", 2);
        tabla.insertar("ATG", 3); // Debe incrementar frecuencia

        // 3. Verificar inserciones
        System.out.println("Total tripletes únicos: " + tabla.totalTripletesUnicos() + " (esperado: 3)");

        // 4. Verificar frecuencia
        NodoHash nodoATG = tabla.buscar("ATG");
        System.out.println("Frecuencia ATG: " + (nodoATG != null ? nodoATG.getFrecuencia() : "null") + " (esperado: 2)");

        // 5. Verificar posiciones
        System.out.println("Posiciones ATG: " + (nodoATG != null ? nodoATG.getPosicion_to_String() : "null") + " (esperado: 0, 3)");

        // 6. Probar función hash
        System.out.println("Hash para ATG: " + tabla.funcionHash("ATG"));
        System.out.println("Hash para TGC: " + tabla.funcionHash("TGC"));

        // 7. Probar reporte de aminoácidos
        System.out.println("\nReporte aminoácidos:");
        System.out.println(tabla.generarReporteAminoacidos());

        System.out.println("=== FIN PRUEBA TABLA HASH ===");
    }

    public static void pruebaArbolABB() {
        System.out.println("\n=== INICIO PRUEBA ÁRBOL ABB ===");

        // 1. Crear árbol
        ArbolABB arbol = new ArbolABB();

        // 2. Crear nodos de prueba con diferentes frecuencias
        NodoHash nodo1 = new NodoHash("ATG", 0);
        nodo1.setFrecuencia(5);

        NodoHash nodo2 = new NodoHash("TGC", 1);
        nodo2.setFrecuencia(3);

        NodoHash nodo3 = new NodoHash("CGA", 2);
        nodo3.setFrecuencia(7);

        // 3. Insertar en el árbol
        arbol.Insertar(nodo1);
        arbol.Insertar(nodo2);
        arbol.Insertar(nodo3);

        // 4. Verificar recorrido inorden (debe estar ordenado por frecuencia)
        System.out.println("Recorrido inorden:");
        System.out.println(arbol.inOrdenFrecuencias());

        // 5. Verificar búsqueda
        System.out.println("Buscando TGC: " + (arbol.buscarPorTriplete("TGC", arbol.getRoot()) != null ? "Encontrado" : "No encontrado"));

        // 6. Verificar patrones más/menos frecuentes
        System.out.println("Más frecuente: " + arbol.getPatronMasFrecuente().getTriplete() + " (esperado: CGA)");
        System.out.println("Menos frecuente: " + arbol.getPatronMenosFrecuente().getTriplete() + " (esperado: TGC)");

        System.out.println("=== FIN PRUEBA ÁRBOL ABB ===");
    }
    
    public static void pruebaListas() {
        System.out.println("\n=== INICIO PRUEBA LISTAS ===");

        // 1. Prueba ListaEnlazada
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertar("ATG", 0);
        lista.insertar("TGC", 1);
        lista.insertar("ATG", 3); // Debe incrementar frecuencia

        System.out.println("Tamaño lista: " + lista.getSize() + " (esperado: 2)");

        NodoHash nodo = lista.buscar("ATG");
        System.out.println("Frecuencia ATG: " + nodo.getFrecuencia() + " (esperado: 2)");

        // 2. Prueba ListaPosiciones
        ListaPosiciones posiciones = new ListaPosiciones();
        posiciones.agregar(0);
        posiciones.agregar(3);
        posiciones.agregar(10);

        System.out.println("Posiciones: " + posiciones.toString() + " (esperado: 0, 3, 10)");
        System.out.println("Obtener posición 1: " + posiciones.obtener(1) + " (esperado: 3)");

        System.out.println("=== FIN PRUEBA LISTAS ===");
    }
    
    
    
}
