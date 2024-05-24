# Programa de Planificación de Rutas para el Centro de Respuesta al Covid-19

Este programa ayuda en la planificación de rutas entre hospitales y puestos de salud en Guatemala durante la pandemia de Covid-19. Utiliza un grafo ponderado dirigido para representar las conexiones entre ciudades y el algoritmo de Floyd-Warshall para encontrar las rutas más cortas entre ellas.

## Autor
- Anggie Quezada

## Requisitos
- Java JDK 8 o superior
- JUnit (para ejecutar las pruebas unitarias)

## Instalación y Uso
1. Clona este repositorio en tu máquina local.
2. Asegúrate de tener Java JDK instalado en tu sistema.
3. Compila los archivos Java utilizando tu IDE preferido o mediante la línea de comandos.
4. Ejecuta el programa utilizando el siguiente comando:
    ```bash
    java Programa guategrafo.txt
    ```
   Donde `guategrafo.txt` es el archivo que contiene la información de las conexiones entre ciudades.
5. Sigue las instrucciones en pantalla para calcular rutas, agregar o eliminar conexiones, y encontrar el centro del grafo.

## Estructura del Proyecto
- `Grafo.java`: Implementación del grafo y algoritmo de Floyd-Warshall.
- `Programa.java`: Clase principal que utiliza el grafo para calcular rutas y el centro del grafo.
- `GrafoTest.java`: Pruebas unitarias utilizando JUnit para verificar el funcionamiento del grafo.
- `guategrafo.txt`: Archivo de ejemplo con las conexiones entre ciudades.

## Contribuciones
Las contribuciones son bienvenidas. Si deseas contribuir a este proyecto, por favor crea un *pull request* con tus cambios.

## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
