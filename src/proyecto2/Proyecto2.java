/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;


/**
 * Clase principal del proyecto de análisis de secuencias de ADN.
 * Esta clase inicia la aplicación mostrando la interfaz gráfica principal.
 * 
 * <p>El proyecto está diseñado para analizar secuencias de ADN, identificar patrones
 * de tripletes y generar reportes estadísticos sobre frecuencias y conversiones
 * a aminoácidos.</p>
 * 
 * @author ANTONY CEN
 * @version 1.0
 * @see Interfaz_1
 */
public class Proyecto2 {

    /**
     * Punto de entrada principal para la aplicación.
     * Crea y muestra la ventana principal de la interfaz gráfica.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados en esta aplicación)
     */
    public static void main(String[] args) {
        // Crear y mostrar la ventana principal
        Interfaz_1 ventana=new Interfaz_1();
        ventana.show();
        ventana.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
     
    }
}
