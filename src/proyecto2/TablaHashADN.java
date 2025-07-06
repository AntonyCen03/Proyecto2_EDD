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
    
    
    public void insertar(String triplete, int posicion) {
        if (triplete == null || triplete.length() != 3) return;

        int indice = funcionHash(triplete);
        boolean nuevoElemento = getTabla()[indice].insertar(triplete, posicion);

        if (nuevoElemento && getTabla()[indice].getSize() > 1) {
            setColisionesTotales(getColisionesTotales() + 1);
        }
    }


    public NodoHash buscar(String triplete) {
        if (triplete == null || triplete.length() != 3) {
            return null;
        }
        
        int indice = funcionHash(triplete);
        return getTabla()[indice].buscar(triplete);
    }
    
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
    
    // Métodos adicionales para los requisitos del proyecto
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
     * Convierte el patrón más frecuente a una cadena descriptiva
     * @return String con la información del patrón más frecuente
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
     * Convierte el patrón menos frecuente a una cadena descriptiva
     * @return String con la información del patrón menos frecuente
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
    
    public double factorDeCarga() {
        return (double) totalTripletesUnicos() / getCapacidad();
    }
    
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

    public String generarReporteAminoacidos1() {
        MiListaSimple aminoacidosUnicos = new MiListaSimple();
        ListaEnlazada[] tripletesPorAminoacido = new ListaEnlazada[30];

        // Inicializar las listas
        for (int i = 0; i < tripletesPorAminoacido.length; i++) {
            tripletesPorAminoacido[i] = new ListaEnlazada();
        }

        // Recorrer toda la tabla hash
        for (int i = 0; i < getCapacidad(); i++) {
            NodoHash actual = getTabla()[i].getCabeza();
            while (actual != null) {
                String triplete = actual.getTriplete();
                String aminoacido = getAminoacido(triplete);

                // Verificar si el aminoácido ya está en nuestra lista
                if (!aminoacidosUnicos.contiene(aminoacido)) {
                    aminoacidosUnicos.agregar(aminoacido);
                }

                // Agregar el triplete a la lista correspondiente
                int index = aminoacidosUnicos.indiceDe(aminoacido);
                tripletesPorAminoacido[index].insertar2(triplete, actual.getFrecuencia());

                actual = actual.getpNext();
            }
        }

        // Construir el reporte
        StringBuilder reporte = new StringBuilder("=== REPORTE DE AMINOÁCIDOS ===\n\n");

        NodoLista actualAmino = aminoacidosUnicos.getpFirst();
        int index = 0;

        while (actualAmino != null) {
            String aminoacido = actualAmino.getTriplete();
            reporte.append(aminoacido).append(":\n");

            // Obtener los tripletes para este aminoácido
            NodoHash actualTriplete = tripletesPorAminoacido[index].getCabeza();

            if (actualTriplete == null) {
                reporte.append("  - No hay tripletes\n");
            } else {
                while (actualTriplete != null) {
                    reporte.append("  - ").append(actualTriplete.getTriplete())
                          .append(" (Frec: ").append(actualTriplete.getFrecuencia()).append(")\n");
                    actualTriplete = actualTriplete.getpNext();
                }
            }

            reporte.append("\n");
            actualAmino = actualAmino.getSiguiente();
            index++;
        }

        return reporte.toString();
    }
    
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
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the tabla
     */
    public ListaEnlazada[] getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(ListaEnlazada[] tabla) {
        this.tabla = tabla;
    }

    /**
     * @return the colisionesTotales
     */
    public int getColisionesTotales() {
        return colisionesTotales;
    }

    /**
     * @param colisionesTotales the colisionesTotales to set
     */
    public void setColisionesTotales(int colisionesTotales) {
        this.colisionesTotales = colisionesTotales;
    }

}
