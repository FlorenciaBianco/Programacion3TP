package tpe;

import java.util.ArrayList;
import java.util.List;

/*
    Clase Solucion: Representa una solución al problema de Optimización de Producción.
    Guarda la secuencia de máquinas utilizadas, las piezas producidas, el número de activaciones y el costo computacional de la solución.
 */

public class Solucion {
    private List<Maquina> secuencia;      /* Máquinas usadas en orden  */
    private int totalPiezas;              /* Total de piezas producidas */
    private int totalActivaciones;        /* Cantidad de máquinas totales activadas */
    private int costo;                    /* Costo computacional de la solucion */
    private boolean esOptima;             /* Es o no una solucion optima */

    /* Constructor por defecto: Crea una solución vacía */
    public Solucion() {
        this.secuencia = new ArrayList<>();
        this.totalPiezas = 0;
        this.totalActivaciones = 0;
        this.costo = 0;
        this.esOptima = false;
    }

    /*
       Constructor que recibe como parametros una secuencia inicial de maquinas y su costo:
       Calcula total de piezas y activaciones automáticamente.
    */
    public Solucion(List<Maquina> secuencia, int costo) {
        this.secuencia = new ArrayList<>(secuencia);
        this.totalActivaciones = secuencia.size();

        // Suma las piezas producidas por cada máquina de la secuencia //
        int sumaPiezas = 0;
        for (Maquina maquina : secuencia) {
            sumaPiezas += maquina.getPiezasPorCiclo();
        }
        this.totalPiezas = sumaPiezas; // Define total de piezas //

        this.costo = costo;
        this.esOptima = false;
    }

    /* Metodo que Agrega una máquina a la solución actual, y actualiza los valores de la solucion */
    public void agregarMaquina(Maquina maquina) {
        this.secuencia.add(maquina);
        this.totalPiezas += maquina.getPiezasPorCiclo();
        this.totalActivaciones++;
    }

    /* Metodo que Quita la última máquina agregada a la solucion, y actualiza los valores de la solucion */
    public void removerUltimaMaquina() {
        if (!this.secuencia.isEmpty()) {
            Maquina ultima = this.secuencia.remove(this.secuencia.size() - 1);
            this.totalPiezas -= ultima.getPiezasPorCiclo();
            this.totalActivaciones--;
        }
    }

    /* Metodo que genera y devuelve una copia completa de la solución actual */
    public Solucion copia() {
        Solucion nueva = new Solucion();
        nueva.secuencia = new ArrayList<>(this.secuencia);
        nueva.totalPiezas = this.totalPiezas;
        nueva.totalActivaciones = this.totalActivaciones;
        nueva.costo = this.costo;
        nueva.esOptima = this.esOptima;
        return nueva;
    }

    /* Metodos Getters */
    public List<Maquina> getSecuencia() {
        return this.secuencia;
    }

    public int getTotalPiezas() {
        return this.totalPiezas;
    }

    public int getTotalActivaciones() {
        return this.totalActivaciones;
    }

    public int getCosto() {
        return this.costo;
    }

    public boolean esOptima() {
        return this.esOptima;
    }

    /* Metodos Setters */
    public void setCosto(int costo) {
        this.costo = costo;
    }

    public void setEsOptima(boolean esOptima) {
        this.esOptima = esOptima;
    }

    /*
       Metodo toString() que devuelve una representación detallada de la solución encontrada, incluyendo la secuencia de máquinas, piezas totales,
       activaciones de maquinas y el costo computacional.
     */
    @Override
    public String toString() {
        /* Solucion Obtenida */
        /* Mostrar la Secuencia de Máquinas */
        String detalle = "Secuencia: [";
        for (int i = 0; i < this.secuencia.size(); i++) {
            Maquina maquina = this.secuencia.get(i);
            detalle += maquina.getNombre();

            if (i < this.secuencia.size() - 1) {
                detalle += ", ";
            }
        }
        detalle += "]\n";

        /* Mostrar Detalles de la Solucion */
        detalle += String.format("Total piezas producidas: %d \n",this.totalPiezas); /* Mostrar la Cantidad de piezas producidas */
        detalle += String.format("Total activaciones: %d \n", this.totalActivaciones); /* Mostrar la Cantidad de puestas en funcionamiento requeridas */
        detalle += String.format("Costo computacional: %d" , this.costo);  /* Mostrar las Métricas para analisis de costo de la solucion */

        return detalle;
    }
}