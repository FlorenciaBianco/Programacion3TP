Trabajo Practico Especial Programacion 3

Grupo 32

Integrantes: 
 - Bianco, Florencia 
 - Sanchez Fiadone, Gonzalo Nicolas

Configuracion del Proyecto:

- Clonar el Repositorio del proyecto usando Git.
- Importar el proyecto clonado como un proyecto Maven a su Entorno de Desarrollo Integrado (IDE), (preferentemente IntelliJ).
- Se puede modificar la configuración de ejecución del proyecto para especificar la ruta absoluta al archivo de entrada que contiene la solicitud de piezas y las descripciones de las máquinas. En la configuración principal de la aplicación, en el campo "Argumentos del programa", proporcionar la ruta absoluta al archivo de entrada que se desea correr. 
- En la carpeta de configs definimos tres archivos ejemplos (uno correspnde al presentado en el enunciado del problema) que pueden correrse y observar sus resultados.

Estructura del Proyecto:

    /configs  ---> Carpeta con Archivos .txt de Configuración
        ejemplo1Enunciado.txt
        ejemplo2.txt
        ejemplo3SinSolucion.txt

    /src /java /tpe

        Maquina.java -->  Clase que representa una Máquina
        Solucion.java  -->   Clase que representa una Solución
        EstrategiaBacktracking.java  --> Clase que desarrolla el Algoritmo de Backtracking
        EstrategiaGreedy.java   --> Clase que desarrolla el Algoritmo Greedy
        Fabrica.java  --> Clase que representa la Fabrica productora de piezas


Ejemplo de Resultados Obtenidos para el Ejemplo del Enunciado:
    
    === SISTEMA DE OPTIMIZACIÓN PRODUCTIVA ===
    Objetivo: 12 piezas
    Máquinas disponibles:
      - M1(7 piezas)
      - M2(3 piezas)
      - M3(4 piezas)
      - M4(1 piezas)
    
    ==================================================
    ALGORITMO BACKTRACKING
    Estrategia de Busqueda de la Solucion Mas Eficiente
    
    SOLUCION BACKTRACKING ENCONTRADA:
    Secuencia: [M1, M3, M4]
    Total piezas producidas: 12
    Total activaciones: 3
    Costo computacional: 85
    Estados generados: 85
    Tiempo de Ejecución: 2 ms
    
    ==================================================
    ALGORITMO GREEDY
    Estrategia de Selección Localmente Optima
    
    SOLUCION GREEDY ENCONTRADA:
    Secuencia: [M1, M3, M4]
    Total piezas producidas: 12
    Total activaciones: 3
    Costo computacional: 3
    Candidatos Evaluados: 3
    Tiempo de Ejecución: 1 ms

