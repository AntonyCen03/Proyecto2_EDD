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
    
    
    
//    private int funcionHash(String triplete){
//        if (triplete==null || triplete.length()!=3) {
//            return 0;
//        }
//        
//        //Convertir a ARN (cambiar T por U)
//        String tripleteARN = triplete.replace('T', 'U');
//        
//        //Calcular valor hash basado en los 3 nucleotidos
//        int hash=0;
//        for (int i = 0; i < 3; i++) {
//            char c = tripleteARN.charAt(i);
//            int valorBase=0;
//            switch(c){
//                case 'A': valorBase= 0; break;
//                case 'U': valorBase= 1; break;
//                case 'C': valorBase= 2; break;
//                case 'G': valorBase= 3; break;
//                default: valorBase=0;
//            }
//            hash = (hash << 2) | valorBase;
//        }
//        return Math.abs(hash % capacidad);
//    }
    
    public void insertar(String triplete, int posicion) {
        if (triplete == null || triplete.length() != 3) {  
            System.out.println("Triplete inválido: " + triplete); 
            return;
        }

        int indice = funcionHash(triplete);
        System.out.println("Insertando: " + triplete + " en índice: " + indice); 

        if (!tabla[indice].EsVacio()) {
            setColisionesTotales(getColisionesTotales() + 1);
        }

        getTabla()[indice].insertar(triplete, posicion);
    }

//    public void insertar(String triplete, int posicion) {
//        if (triplete == null || triplete.length() != 3) {
//            return;
//        }
//        
//        int indice = funcionHash(triplete);
//        
//        // Verificar si habrá colisión (si la lista no está vacía)
//        if (!tabla[indice].EsVacio()) {
//            colisionesTotales++;
//        }
//        
//        tabla[indice].insertar(triplete, posicion);
//    }

    public NodoHash buscar(String triplete) {
        if (triplete == null || triplete.length() != 3) {
            return null;
        }
        
        int indice = funcionHash(triplete);
        return getTabla()[indice].buscar(triplete);
    }
    
    public String generarReporteColisiones() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("Total de colisiones: ").append(getColisionesTotales()).append("\n");
        
        for (int i = 0; i < getCapacidad(); i++) {
            if (getTabla()[i].getSize() > 1) {
                reporte.append("Índice ").append(i).append(": ")
                      .append(getTabla()[i].getSize()).append(" colisiones\n");
            }
        }
        
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
                if (size > 0) {
                    System.out.println("Índice " + i + " tiene " + size + " elementos");
                }
            }
        }
        System.out.println("Total de tripletes únicos: " + contador); 
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

        // Contar todos los tripletes (incluyendo repetidos)
        for (int i = 0; i < getCapacidad(); i++) {
            if (getTabla()[i] != null) {
                NodoHash actual = getTabla()[i].getCabeza();
                while (actual != null) {
                    totalGeneral += actual.getFrecuencia();
                    actual = actual.getpNext();
                }
            }
        }

        reporte.append("=== REPORTE DE TRIPLETES ===\n")
              .append("Total de tripletes (incluyendo repetidos): ").append(totalGeneral).append("\n")
              .append("Tripletes únicos: ").append(totalTripletesUnicos()).append("\n")
              .append("Factor de carga: ").append(String.format("%.2f", factorDeCarga())).append("\n\n")
              .append(generarReporteColisiones()).append("\n");

        return reporte.toString();
    }
    
    public String generarReporteAminoacidos() {
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
