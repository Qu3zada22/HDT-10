import networkx as nx

class Grafo:
    def __init__(self, nombre_archivo):
        self.grafo = nx.DiGraph()
        self.leer_grafo(nombre_archivo)

    def leer_grafo(self, nombre_archivo):
        with open(nombre_archivo, 'r') as archivo:
            next(archivo)  # Ignorar la primera línea que contiene el encabezado
            for linea in archivo:
                ciudad1, ciudad2, distancia = linea.split()
                self.grafo.add_edge(ciudad1, ciudad2, distancia=float(distancia))

    def algoritmo_floyd(self):
        return nx.floyd_warshall(self.grafo)

    def calcular_centro(self):
        distancia_mas_lejana = float('inf')
        centro = None

        distancias = self.algoritmo_floyd()

        for ciudad in self.grafo.nodes():
            max_distancia_desde_ciudad = max(distancias[ciudad].values())
            if max_distancia_desde_ciudad < distancia_mas_lejana:
                distancia_mas_lejana = max_distancia_desde_ciudad
                centro = ciudad

        return centro

    def agregar_conexion(self, ciudad1, ciudad2, distancia):
        self.grafo.add_edge(ciudad1, ciudad2, distancia=distancia)

    def eliminar_conexion(self, ciudad1, ciudad2):
        if self.grafo.has_edge(ciudad1, ciudad2):
            self.grafo.remove_edge(ciudad1, ciudad2)
            return True
        else:
            return False

class Programa:
    def __init__(self, nombre_archivo):
        self.grafo = Grafo(nombre_archivo)

    def iniciar(self):
        while True:
            print("\nOpciones:")
            print("1. Calcular ruta más corta entre ciudades.")
            print("2. Calcular el centro del grafo.")
            print("3. Modificar el grafo.")
            print("4. Salir.")

            opcion = input("Seleccione una opción: ")

            if opcion == "1":
                ciudad_origen = input("Ingrese la ciudad de origen: ")
                ciudad_destino = input("Ingrese la ciudad de destino: ")
                try:
                    distancia = nx.shortest_path_length(self.grafo.grafo, source=ciudad_origen, target=ciudad_destino, weight='distancia')
                    ruta = nx.shortest_path(self.grafo.grafo, source=ciudad_origen, target=ciudad_destino, weight='distancia')
                    print(f"La distancia más corta entre {ciudad_origen} y {ciudad_destino} es {distancia} km.")
                    print("La ruta es:", ruta)
                except nx.NetworkXNoPath:
                    print("No hay ruta entre las ciudades especificadas.")

            elif opcion == "2":
                centro = self.grafo.calcular_centro()
                print(f"El centro del grafo es: {centro}")

            elif opcion == "3":
                self.modificar_grafo()

            elif opcion == "4":
                print("¡Hasta luego!")
                break

            else:
                print("Opción no válida. Por favor, seleccione una opción válida.")

    def modificar_grafo(self):
        print("\nOpciones de modificación:")
        print("1. Agregar una conexión.")
        print("2. Eliminar una conexión.")

        opcion = input("Seleccione una opción de modificación: ")

        if opcion == "1":
            ciudad1 = input("Ingrese el nombre de la ciudad de origen: ")
            ciudad2 = input("Ingrese el nombre de la ciudad de destino: ")
            distancia = float(input("Ingrese la distancia en KM: "))
            self.grafo.agregar_conexion(ciudad1, ciudad2, distancia)
            print(f"Conexión agregada: {ciudad1} -> {ciudad2} con distancia {distancia} km.")

        elif opcion == "2":
            ciudad1 = input("Ingrese el nombre de la ciudad de origen: ")
            ciudad2 = input("Ingrese el nombre de la ciudad de destino: ")
            if self.grafo.eliminar_conexion(ciudad1, ciudad2):
                print(f"Conexión eliminada: {ciudad1} -> {ciudad2}.")
            else:
                print("No existe una conexión directa entre las ciudades especificadas.")

        else:
            print("Opción no válida. Por favor, seleccione una opción válida.")

if __name__ == "__main__":
    nombre_archivo = "guategrafo.txt"
    programa = Programa(nombre_archivo)
    programa.iniciar()
