package tpe;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
   Implementacion del Algoritmo Greedy
*/

public class EstrategiaGreedy {
    private List<Maquina> maquinas;
    private int objetivoPiezas;
    private int candidatosEvaluados;

    public EstrategiaGreedy(List<Maquina> maquinas, int objetivoPiezas) {
        this.maquinas = new ArrayList<>(maquinas);
        this.objetivoPiezas = objetivoPiezas;
        this.candidatosEvaluados = 0;

        /* Ordenar máquinas por producción descendente (de mayor a menor) para Estrategia Greedy */
        Collections.sort(maquinas);
    }

    /*
       Estrategia de Resolucion Algoritmo Greedy:

        - Candidatos: Son todas las maquinas que se pueden seleccionar del conjunto proporcionado (ordenadas por producción descendente).
        - Estrategia de Seleccion de Candidatos: Seleccionar siempre la máquina más productiva disponible (toma la "mejor" opcion en cada paso).
        - Consideraciones:
          - Puede haber casos en los que el algoritmo no logre encontrar una solución válida. Esto ocurre si, en alguna iteración, no se encuentra ninguna mejor maquina que pueda producir piezas sin exceder el numero de piezas restantes (es decir, mejorMaquina permanece null).
            En este caso, el algoritmo retorna null, indicando que no pudo completar la producción con las maquinas y la estrategia de seleccion utilizada.
          - Ventaja: Eficiencia Computacional Superior en comparacion con Backtracking
                En cada iteración, simplemente selecciona la "mejor" maquina localmente y la añade a la solución. No realiza "pruebas y errores" ni retrocede para considerar alternativas.
          - Desventaja:
                Este algoritmo siempre encuentra una solución si existe una para su estrategia, sin embargo esta solución no garantiza ser la óptima global (la que minimiza las activaciones totales).
                La elección localmente "mejor" que realiza Greedy no siempre conduce al mejor resultado general.
    */

    public Solucion greedy() {
        candidatosEvaluados = 0;
        Solucion solucion = new Solucion();
        int piezasRestantes = objetivoPiezas;

        while (piezasRestantes > 0) {
            Maquina mejorMaquina = null;
            candidatosEvaluados++;

            /* Buscar la máquina más productiva que no exceda el numero de las piezas restantes */
            for (Maquina maquina : maquinas) {
                if (maquina.getPiezasPorCiclo() <= piezasRestantes) {
                    if (mejorMaquina == null || maquina.getPiezasPorCiclo() > mejorMaquina.getPiezasPorCiclo()) {
                        mejorMaquina = maquina;
                    }
                }
            }

            /* Si no encontramos maquina valida, no hay solucion */
            if (mejorMaquina == null) {
                return null; /* No se puede completar con las maquinas disponibles */
            }

            solucion.agregarMaquina(mejorMaquina);
            piezasRestantes -= mejorMaquina.getPiezasPorCiclo();
        }

        solucion.setCosto(candidatosEvaluados);
        return solucion;
    }
}