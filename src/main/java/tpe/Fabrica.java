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

    public Fabrica(List<Maquina> maquinas, int objetivoPiezas) {
        this.maquinas = maquinas;
        this.objetivoPiezas = objetivoPiezas;
    }

    public static void main(String[] args) {
        CargadorConfiguracion cargconfiguracion = new CargadorConfiguracion();

        try {
            Fabrica fabrica = cargconfiguracion.cargarConfiguracion(args[0]);

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