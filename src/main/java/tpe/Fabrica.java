package tpe;

import java.io.*;
import java.net.URL;
import java.util.*;

/*
   Clase Fabrica para el Sistema de Optimización de Producción
*/
public class Fabrica {
    private List<Maquina> maquinas;
    private int objetivoPiezas;

    public static void main(String[] args) {
        Fabrica fabrica = new Fabrica();

        try {
            fabrica.cargarConfiguracion(args[0]);

            System.out.println("=== SISTEMA DE OPTIMIZACION PRODUCTIVA ===");
            System.out.println("Objetivo: " + fabrica.objetivoPiezas + " piezas");
            System.out.println("Máquinas disponibles: ");
            for (Maquina m : fabrica.maquinas) {
                System.out.println("  - " + m);
            }

            String separator = new String(new char[50]).replace("\0", "=");
            System.out.println("\n" + separator);

            /* Ejecutar Algoritmo Backtracking */
            fabrica.ejecutarBacktracking();

            String separator2 = new String(new char[50]).replace("\0", "=");
            System.out.println("\n" + separator2);

            /* Ejecutar Algoritmo Greedy */
            fabrica.ejecutarGreedy();

        } catch (IOException e) {
            System.err.println("Error al cargar configuración: " + e);
        }
    }

    private void cargarConfiguracion(String pathArchivo) throws IOException {
        maquinas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathArchivo))) {


            /* Primera línea: Objetivo de piezas a producir */
            objetivoPiezas = Integer.parseInt(reader.readLine().trim());

            /* Líneas siguientes: Máquinas disponibles, con sus respectivos numeros de piezas que producen */
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(",");
                    String nombre = partes[0].trim();
                    int piezas = Integer.parseInt(partes[1].trim());
                    maquinas.add(new Maquina(nombre, piezas));
                }
            }
        }
    }

    private void ejecutarBacktracking() {
        System.out.println("ALGORITMO BACKTRACKING");
        System.out.println("Estrategia de Busqueda de la Solucion Mas Eficiente");

        long tiempoInicio = System.currentTimeMillis();

        EstrategiaBacktracking optimizador = new EstrategiaBacktracking(maquinas, objetivoPiezas);
        Solucion solucion = optimizador.backtracking();

        long tiempoFin = System.currentTimeMillis();

        if (solucion != null) {
            System.out.println("\nSOLUCION BACKTRACKING ENCONTRADA:");
            System.out.println(solucion);
            System.out.println("Estados generados: " + solucion.getCosto());
        } else {
            System.out.println("\nNo se encontró solución factible");
        }

        System.out.println("Tiempo de Ejecución: " + (tiempoFin - tiempoInicio) + " ms");
    }

    private void ejecutarGreedy() {
        System.out.println("ALGORITMO GREEDY");
        System.out.println("Estrategia de Selección Localmente Optima");

        long tiempoInicio = System.currentTimeMillis();

        EstrategiaGreedy optimizador = new EstrategiaGreedy(maquinas, objetivoPiezas);
        Solucion solucion = optimizador.greedy();

        long tiempoFin = System.currentTimeMillis();

        if (solucion != null) {
            System.out.println("\nSOLUCION GREEDY ENCONTRADA:");
            System.out.println(solucion);
            System.out.println("Candidatos Evaluados: " + solucion.getCosto());
        } else {
            System.out.println("\nNo se encontró solución factible");
        }

        System.out.println("Tiempo de Ejecución: " + (tiempoFin - tiempoInicio) + " ms");
    }
}