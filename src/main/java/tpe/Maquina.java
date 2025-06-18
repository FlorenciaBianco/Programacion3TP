package tpe;

/*
   Clase Maquina
*/

public class Maquina implements Comparable<Maquina> {
    private String nombre;
    private int piezasPorCiclo;

    public Maquina(String nombre, int piezasPorCiclo) {
        this.nombre = nombre;
        this.piezasPorCiclo = piezasPorCiclo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPiezasPorCiclo() {
        return piezasPorCiclo;
    }

    @Override
    public String toString() {
        return nombre + "(" + piezasPorCiclo + " piezas)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Maquina maquina = (Maquina) obj;
        return piezasPorCiclo == maquina.piezasPorCiclo &&
                nombre.equals(maquina.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode() + piezasPorCiclo;
    }

    /*
       Orden: Cantidad de Piezas por Ciclo de Mayor a Menor
    */
    @Override
    public int compareTo(Maquina otra) {
        return Integer.compare(otra.piezasPorCiclo, this.piezasPorCiclo);
    }
}