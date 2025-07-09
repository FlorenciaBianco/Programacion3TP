package tpe;

import java.util.Collections;
import java.util.List;

/*
   Implementacion del Algoritmo de Backtracking
*/

public class EstrategiaBacktracking {
    private List<Maquina> maquinas;
    private int objetivoPiezas;
    private Solucion mejorSolucion;
    private int estadosGenerados;

    public EstrategiaBacktracking(List<Maquina> maquinas, int objetivoPiezas) {
        this.maquinas = maquinas;
        this.objetivoPiezas = objetivoPiezas;
        this.mejorSolucion = null;
        this.estadosGenerados = 0;
    }

    /*
       Estrategia de Resolucion Algoritmo Backtracking:

     - Generacion Arbol de Exploracion:
        - La exploracion comienza en la raíz del arbol, que representa el estado inicial. En este punto, no se ha seleccionado ninguna maquina.
        - Cada nodo del arbol representa una solucion actual parcial, es decir, un conjunto de maquinas seleccionadas hasta el momento.
        - En cada paso recursivo, el algoritmo intenta "expandir" la solucion actual probando cada una de las maquinas disponibles. Para cada maquina, se genera un nuevo "hijo" en el arbol.
     - Mantenimiento de la mejor solucion encontrada.
     - Estados Solucion: Secuencias de maquinas que suman exactamente el objetivo de la producción requerida.
        - Entre todas las combinaciones que logran el objetivo de piezas, el algoritmo guarda y devuelve la mejor solucion, que es aquella que consigue el objetivo con la menor cantidad de puestas en funcionamiento posible.
     - Estados Finales: Son aquellos estados en los que el algoritmo deja de expandir la rama actual. Ocurre en dos momentos:
        - Cuando el numero de piezas acumuladas de la solucion actual es exactamente igual al numero objetivo de piezas (Encontre una solucion).
        - Cuando la solucion actual ya no puede llevar a una solución válida u óptima, y por lo tanto la rama es descartada.
     - Podas:
       - Por Exceso de Piezas: Si en cualquier punto de la exploración, las piezas acumuladas  exceden el objetivo de piezas, la rama actual se descarta.
       - Por Peor Solución Parcial: Si ya se ha encontrado una mejor solucion hasta el momento y esta no es nula, y la solucion actual que se está construyendo ya tiene un número de total activaciones igual o superior al de la mejor solucion encontrada, entonces la rama actual se descarta.
     */

    public Solucion backtracking() {
        estadosGenerados = 0;
        mejorSolucion = null;
        Solucion solucionActual = new Solucion();
        Collections.sort(maquinas); /* Ordenamiento de las Maquinas de Mayor a Menor para mayor Eficacia */

        backtrackingRecursivo(solucionActual, 0);

        if (mejorSolucion != null) {
            mejorSolucion.setCosto(estadosGenerados);
            mejorSolucion.setEsOptima(true);
        }

        return mejorSolucion;
    }

    private void backtrackingRecursivo(Solucion solucionActual, int piezasAcumuladas) {
        estadosGenerados++;

        /* Caso Base/Corte: Encontramos Solución para nuestro objetivo */
        if (piezasAcumuladas == objetivoPiezas) {
            if (mejorSolucion == null || solucionActual.getTotalActivaciones() < mejorSolucion.getTotalActivaciones()) {
                mejorSolucion = solucionActual.copia();
            }
            return;
        }

        /* Poda: Si la solucion encontrada excede el numero objetivo de piezas o si el numero de activaciones de esta es igual o mayor al obtenido en la mejor solucion, efectua poda */
        if (piezasAcumuladas > objetivoPiezas ||
                (mejorSolucion != null && solucionActual.getTotalActivaciones() >= mejorSolucion.getTotalActivaciones())) {
            return;
        }

        /* Probar por cada maquina disponible */
        for (Maquina maquina : maquinas) {
            /* Metodo continuarActivaciones(): Evaluar la cantidad minima de activaciones
            /* de la mejor maquina seleccionada al momento necesarias con respecto a la solucion parcialmente obtenida hasta el momento */
            /* Si es mayor directamente se descarta antes de seguir generandose, efectuando Poda de tipo Anticipativa, continuando a la siguiente maquina */
            if(continuarActivaciones(maquina,piezasAcumuladas,solucionActual)){
              continue;
            }
            solucionActual.agregarMaquina(maquina);
            backtrackingRecursivo(solucionActual, piezasAcumuladas + maquina.getPiezasPorCiclo()); /* Llamado Recursivo */
            solucionActual.removerUltimaMaquina();
        }
    }

 private boolean continuarActivaciones(Maquina maquina, int piezasAcumuladas,Solucion solucionActual){
        int maxPiezasMaquina = maquina.getPiezasPorCiclo();
        int piezasFaltantes = objetivoPiezas-piezasAcumuladas;
        int numActivacionesNecesarias = (int) Math.ceil((double) piezasFaltantes/maxPiezasMaquina);

        /* Determinamos si con el mayor numero de activaciones de la maquina se logra un resultado Mayor o Igual que la solucion actual, efectuamos Poda */
        if(mejorSolucion != null && solucionActual.getTotalActivaciones()+ numActivacionesNecesarias >= mejorSolucion.getTotalActivaciones()){
            return true;
        }
        else{
            return false;
        }
 }
}