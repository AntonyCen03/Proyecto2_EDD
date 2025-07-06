/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Implementación de una tabla hash especializada para el análisis de secuencias de ADN.
 * Esta clase permite almacenar, buscar y analizar tripletes de ADN (secuencias de 3 nucleótidos),
 * incluyendo el cálculo de frecuencias, posiciones y conversión a aminoácidos.
 * 
 * @author ANTONY CEN
 */
public class TablaHashADN {
    private int capacidad;
    private ListaEnlazada[] tabla;
    private int colisionesTotales;

    /**
     * Constructor que inicializa la tabla hash con una capacidad específica.
     * 
     * @param capacidad Tamaño inicial de la tabla hash (recomendado usar números primos)
     * @throws IllegalArgumentException Si la capacidad es menor o igual a cero
     */
    public TablaHashADN(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new ListaEnlazada[capacidad];
        this.colisionesTotales=0;
        
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new ListaEnlazada();
        }
        
    }
    
    /**
     * Calcula el índice hash para un triplete de ADN.
     * 
     * @param triplete Cadena de 3 caracteres (A, T, C, G)
     * @return Índice hash calculado entre 0 y capacidad-1
     * @throws IllegalArgumentException Si el triplete es nulo o no tiene 3 caracteres
     */
    public int funcionHash(String triplete) {
        if (triplete == null || triplete.length() != 3) return 0;

        int hash = 0;
        int p = 31; 
        int m = getCapacidad();

        for (int i = 0; i < 3; i++) {
            char c = triplete.charAt(i);
            int charValue = "ATCG".indexOf(c); // A=0, T=1, C=2, G=3
            hash = (hash * p + charValue) % m;
        }
        return Math.abs(hash);
    }
    
    
    /**
     * Inserta un triplete en la tabla hash con su posición en la secuencia.
     * Si el triplete ya existe, incrementa su frecuencia y agrega la nueva posición.
     * 
     * @param triplete Cadena de 3 caracteres (A, T, C, G)
     * @param posicion Posición en la secuencia de ADN (debe ser no negativa)
     * @throws IllegalArgumentException Si el triplete es inválido o la posición es negativa
     */
    public void insertar(String triplete, int posicion) {
        if (triplete == null || triplete.length() != 3) return;

        int indice = funcionHash(triplete);
        boolean nuevoElemento = getTabla()[indice].insertar(triplete, posicion);

        if (nuevoElemento && getTabla()[indice].getSize() > 1) {
            setColisionesTotales(getColisionesTotales() + 1);
        }
    }


    /**
     * Busca un triplete en la tabla hash.
     * 
     * @param triplete Cadena de 3 caracteres (A, T, C, G) a buscar
     * @return NodoHash que contiene el triplete, o null si no se encuentra
     * @throws IllegalArgumentException Si el triplete es inválido
     */
    public NodoHash buscar(String triplete) {
        if (triplete == null || triplete.length() != 3) {
            return null;
        }
        
        int indice = funcionHash(triplete);
        return getTabla()[indice].buscar(triplete);
    }
    
    /**
     * Genera un reporte detallado de colisiones en la tabla hash.
     * 
     * @return String con el reporte de colisiones que incluye:
     *         - Total de colisiones reales
     *         - Colisiones por índice de la tabla
     */
    public String generarReporteColisiones() {
        StringBuilder reporte = new StringBuilder();
        int colisionesReales = 0;

        for (int i = 0; i < getCapacidad(); i++) {
            int elementos = getTabla()[i].getSize();
            if (elementos > 1) {
                int colisionesBucket = elementos - 1; 
                colisionesReales += colisionesBucket;
                reporte.append("Índice ")
                       .append(i)
                       .append(": ")
                       .append(elementos)
                       .append(" elementos (")
                       .append(colisionesBucket)
                       .append(" colisiones)\n");
            }
        }

        reporte.insert(0, "Total de colisiones reales: " + colisionesReales + "\n");
        return reporte.toString();
    }
    
    /**
     * Obtiene el patrón (triplete) más frecuente en la tabla hash.
     * 
     * @return NodoHash con el patrón más frecuente, o null si la tabla está vacía
     */
    public NodoHash getPatronMasFrecuente() {
        NodoHash masFrecuente = null;
        for (ListaEnlazada lista : getTabla()) {
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

    /**
     * Obtiene el patrón (triplete) menos frecuente en la tabla hash.
     * 
     * @return NodoHash con el patrón menos frecuente, o null si la tabla está vacía
     */
    public NodoHash getPatronMenosFrecuente() {
        NodoHash menosFrecuente = null;
        for (ListaEnlazada lista : getTabla()) {
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
    
    
    /**
     * Genera una representación en cadena del patrón más frecuente.
     * 
     * @return String con formato:
     *         Triplete: [triplete]
     *         Frecuencia: [frecuencia]
     *         Aminoácido: [aminoácido correspondiente]
     *         o "No hay patrones registrados" si la tabla está vacía
     */
    public String getPatronMasFrecuenteAsString() {
        NodoHash masFrecuente = getPatronMasFrecuente();
        if (masFrecuente == null) {
            return "No hay patrones registrados";
        }
        return "Triplete: " + masFrecuente.getTriplete() + "\n"+
               "Frecuencia: " + masFrecuente.getFrecuencia() + "\n"+
               "Aminoácido: " + getAminoacido(masFrecuente.getTriplete());
    }

    /**
     * Genera una representación en cadena del patrón menos frecuente.
     * 
     * @return String con formato:
     *         Triplete: [triplete]
     *         Frecuencia: [frecuencia]
     *         Aminoácido: [aminoácido correspondiente]
     *         o "No hay patrones registrados" si la tabla está vacía
     */
    public String getPatronMenosFrecuenteAsString() {
        NodoHash menosFrecuente = getPatronMenosFrecuente();
        if (menosFrecuente == null) {
            return "No hay patrones registrados";
        }
        return "Triplete: " + menosFrecuente.getTriplete() + "\n"+
               "Frecuencia: " + menosFrecuente.getFrecuencia() + "\n"+
               "Aminoácido: " + getAminoacido(menosFrecuente.getTriplete());
    }
    
    
    /**
     * Construye un árbol binario de búsqueda (ABB) con los tripletes ordenados por frecuencia.
     * 
     * @return ArbolABB recién construido con todos los tripletes de la tabla hash
     */
    public ArbolABB construirArbolFrecuencias() {
    ArbolABB arbol = new ArbolABB();
    
    for (ListaEnlazada lista : getTabla()) {
        NodoHash actual = lista.getCabeza();
        while (actual != null) {
            arbol.Insertar(actual);
            actual = actual.getpNext();
        }
    }
    
    return arbol;
    }
    
    /**
     * Calcula el número total de tripletes únicos en la tabla hash.
     * 
     * @return Cantidad de tripletes distintos almacenados
     */
    public int totalTripletesUnicos() {
        int contador = 0;
        for (int i = 0; i < getCapacidad(); i++) {
            if (getTabla()[i] != null) {
                int size = getTabla()[i].getSize();
                contador += size;
            }
        }
        return contador;
    }
    
    /**
     * Obtiene una lista con todos los tripletes únicos almacenados en la tabla hash.
     * 
     * @return MiListaSimple conteniendo todos los tripletes únicos
     */
    public MiListaSimple getTripletesUnicos() {
        MiListaSimple tripletes = new MiListaSimple(); 
        
        for (int i = 0; i < getCapacidad(); i++) {
            if (getTabla()[i] != null) {
                NodoHash actual = getTabla()[i].getCabeza();
                while (actual != null) {
                    tripletes.agregar(actual.getTriplete());
                    actual = actual.getpNext();
                }
            }
        }
        return tripletes;
    }
    
    /**
     * Calcula el factor de carga de la tabla hash.
     * 
     * @return Valor entre 0 y n que representa la relación entre elementos almacenados y capacidad
     */
    public double factorDeCarga() {
        return (double) totalTripletesUnicos() / getCapacidad();
    }
    
    /**
     * Determina el aminoácido correspondiente a un triplete de ADN.
     * 
     * @param triplete Cadena de 3 caracteres (A, T, C, G)
     * @return Nombre del aminoácido con su abreviatura, o "Desconocido" si no es válido
     * @throws IllegalArgumentException Si el triplete es inválido
     */
    public String getAminoacido(String triplete) {
        String tripleteARN = triplete.replace('T', 'U');

        switch(tripleteARN) {
            case "UUU": case "UUC": return "Phe (F)";
            case "UUA": case "UUG": case "CUU": case "CUC": case "CUA": case "CUG": return "Leu (L)";
            case "UCU": case "UCC": case "UCA": case "UCG": case "AGU": case "AGC": return "Ser (S)";
            case "UAU": case "UAC": return "Tyr (Y)";
            case "UAA": case "UAG": case "UGA": return "STOP";
            case "UGU": case "UGC": return "Cys (C)";
            case "UGG": return "Trp (W)";
            case "CCU": case "CCC": case "CCA": case "CCG": return "Pro (P)";
            case "CAU": case "CAC": return "His (H)";
            case "CAA": case "CAG": return "Gln (Q)";
            case "CGU": case "CGC": case "CGA": case "CGG": case "AGA": case "AGG": return "Arg (R)";
            case "AUU": case "AUC": case "AUA": return "Ile (I)";
            case "AUG": return "Met (M) [Inicio]";
            case "ACU": case "ACC": case "ACA": case "ACG": return "Thr (T)";
            case "AAU": case "AAC": return "Asn (N)";
            case "AAA": case "AAG": return "Lys (K)";
            case "GUU": case "GUC": case "GUA": case "GUG": return "Val (V)";
            case "GCU": case "GCC": case "GCA": case "GCG": return "Ala (A)";
            case "GAU": case "GAC": return "Asp (D)";
            case "GAA": case "GAG": return "Glu (E)";
            case "GGU": case "GGC": case "GGA": case "GGG": return "Gly (G)";
            default: return "Desconocido";
        }
    }

    /**
     * Genera un reporte completo con estadísticas de la tabla hash.
     * 
     * @return String con:
     *         - Total de tripletes
     *         - Tripletes únicos
     *         - Factor de carga
     *         - Patrones más/menos frecuentes
     *         - Reporte de colisiones
     */
    public String generarReporteCompleto() {
        StringBuilder reporte = new StringBuilder();
        int totalGeneral = 0;
        int tripletesUnicos = totalTripletesUnicos();

        for (int i = 0; i < getCapacidad(); i++) {
            if (getTabla()[i] != null) {
                NodoHash actual = getTabla()[i].getCabeza();
                while (actual != null) {
                    totalGeneral += actual.getFrecuencia();
                    actual = actual.getpNext();
                }
            }
        }

        NodoHash masFrecuente = getPatronMasFrecuente();
        NodoHash menosFrecuente = getPatronMenosFrecuente();

        reporte.append("=== REPORTE COMPLETO ===\n")
              .append("Total de tripletes: ").append(totalGeneral).append("\n")
              .append("Tripletes únicos: ").append(tripletesUnicos).append("\n")
              .append("Factor de carga: ").append(String.format("%.2f", factorDeCarga())).append("\n")
              .append("Patrón más frecuente: ").append(masFrecuente.getTriplete())
              .append(" (Frec: ").append(masFrecuente.getFrecuencia()).append(")\n")
              .append("Patrón menos frecuente: ").append(menosFrecuente.getTriplete())
              .append(" (Frec: ").append(menosFrecuente.getFrecuencia()).append(")\n\n")
              .append(generarReporteColisiones());

        return reporte.toString();
    }


    /**
     * Genera un reporte organizado por aminoácidos.
     * 
     * @return String con tabla formateada que muestra:
     *         - Combinaciones de bases
     *         - Información del aminoácido
     *         - Frecuencia de cada triplete
     */
    public String generarReporteAminoacidos() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE COMPLETO DE AMINOÁCIDOS ===\n\n");
        reporte.append(String.format("%-12s %-12s %-12s %-25s %-15s %-15s %-10s\n", 
            "Primera Base", "Segunda Base", "Tercera Base", "Aminoácido", "Abrev (3 letras)", "Abrev (1 letra)", "Frecuencia"));
        reporte.append("------------------------------------------------------------------------------------------------\n");

        char[] bases = {'U', 'C', 'A', 'G'};
        for (char base1 : bases) {
            for (char base2 : bases) {
                for (char base3 : bases) {
                    String tripleteARN = "" + base1 + base2 + base3;
                    String tripleteADN = tripleteARN.replace('U', 'T');

                    // Obtener información del aminoácido
                    String[] aminoInfo = getAminoacidoInfoARN(tripleteARN);
                    String nombreAmino = aminoInfo[0];
                    String abrev3 = aminoInfo[1];
                    String abrev1 = aminoInfo[2];

                    // Obtener frecuencia desde la tabla hash
                    NodoHash nodo = buscar(tripleteADN);
                    int frecuencia = (nodo != null) ? nodo.getFrecuencia() : 0;

                    reporte.append(String.format("%-12s %-12s %-12s %-25s %-15s %-15s %-10d\n", 
                        base1, base2, base3, nombreAmino, abrev3, abrev1, frecuencia));
                }
            }
        }

        return reporte.toString();
    }
    
    /**
     * Obtiene información detallada de un aminoácido a partir de un triplete de ARN.
     * 
     * @param tripleteARN Cadena de 3 caracteres (U, C, A, G)
     * @return Arreglo de Strings con:
     *         [0] Nombre completo del aminoácido
     *         [1] Abreviatura de 3 letras
     *         [2] Abreviatura de 1 letra
     */
    private String[] getAminoacidoInfoARN(String tripleteARN) {
        switch(tripleteARN) {
            case "UUU": case "UUC": 
                return new String[]{"Fenilalanina", "Phe", "F"};
            case "UUA": case "UUG": case "CUU": case "CUC": case "CUA": case "CUG": 
                return new String[]{"Leucina", "Leu", "L"};
            case "UCU": case "UCC": case "UCA": case "UCG": case "AGU": case "AGC": 
                return new String[]{"Serina", "Ser", "S"};
            case "UAU": case "UAC": 
                return new String[]{"Tirosina", "Tyr", "Y"};
            case "UAA": case "UAG": case "UGA": 
                return new String[]{"STOP", "-", "-"};
            case "UGU": case "UGC": 
                return new String[]{"Cisteína", "Cys", "C"};
            case "UGG": 
                return new String[]{"Triptófano", "Trp", "W"};
            case "CCU": case "CCC": case "CCA": case "CCG": 
                return new String[]{"Prolina", "Pro", "P"};
            case "CAU": case "CAC": 
                return new String[]{"Histidina", "His", "H"};
            case "CAA": case "CAG": 
                return new String[]{"Glutamina", "Gln", "Q"};
            case "CGU": case "CGC": case "CGA": case "CGG": case "AGA": case "AGG": 
                return new String[]{"Arginina", "Arg", "R"};
            case "AUU": case "AUC": case "AUA": 
                return new String[]{"Isoleucina", "Ile", "I"};
            case "AUG": 
                return new String[]{"Metionina (Inicio)", "Met", "M"};
            case "ACU": case "ACC": case "ACA": case "ACG": 
                return new String[]{"Treonina", "Thr", "T"};
            case "AAU": case "AAC": 
                return new String[]{"Asparagina", "Asn", "N"};
            case "AAA": case "AAG": 
                return new String[]{"Lisina", "Lys", "K"};
            case "GUU": case "GUC": case "GUA": case "GUG": 
                return new String[]{"Valina", "Val", "V"};
            case "GCU": case "GCC": case "GCA": case "GCG": 
                return new String[]{"Alanina", "Ala", "A"};
            case "GAU": case "GAC": 
                return new String[]{"Ácido Aspártico", "Asp", "D"};
            case "GAA": case "GAG": 
                return new String[]{"Ácido Glutámico", "Glu", "E"};
            case "GGU": case "GGC": case "GGA": case "GGG": 
                return new String[]{"Glicina", "Gly", "G"};
            default: 
                return new String[]{"Desconocido", "???", "?"};
        }
    }

    /**
     * Obtiene la capacidad actual de la tabla hash.
     * 
     * @return Número de buckets en la tabla hash
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Establece una nueva capacidad para la tabla hash.
     * Nota: Cambiar la capacidad requiere rehashing de todos los elementos.
     * 
     * @param capacidad Nueva capacidad (debe ser mayor que cero)
     * @throws IllegalArgumentException Si la capacidad es menor o igual a cero
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene el arreglo de listas enlazadas que representa la tabla hash.
     * 
     * @return Arreglo de ListaEnlazada con los buckets de la tabla hash
     */
    public ListaEnlazada[] getTabla() {
        return tabla;
    }

    /**
     * Establece un nuevo arreglo de listas enlazadas para la tabla hash.
     * 
     * @param tabla Nuevo arreglo de ListaEnlazada
     * @throws IllegalArgumentException Si el arreglo es nulo
     */
    public void setTabla(ListaEnlazada[] tabla) {
        this.tabla = tabla;
    }

    /**
     * Obtiene el número total de colisiones registradas.
     * 
     * @return Contador de colisiones totales
     */
    public int getColisionesTotales() {
        return colisionesTotales;
    }

    /**
     * Establece el contador de colisiones totales.
     * 
     * @param colisionesTotales Nuevo valor para el contador (debe ser no negativo)
     * @throws IllegalArgumentException Si el valor es negativo
     */
    public void setColisionesTotales(int colisionesTotales) {
        this.colisionesTotales = colisionesTotales;
    }

}
