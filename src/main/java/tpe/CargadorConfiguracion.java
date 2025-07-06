package tpe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargadorConfiguracion {

    public Fabrica cargarConfiguracion(String pathArchivo) throws IOException {
        List<Maquina> maquinas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathArchivo))) {
            /* Primera línea: Objetivo de piezas a producir */
            int objetivoPiezas = Integer.parseInt(reader.readLine().trim());
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
            return new Fabrica(maquinas, objetivoPiezas);
        }
    }
}
