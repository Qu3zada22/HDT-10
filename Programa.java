import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Programa {
    private final Grafo grafo;

    public Programa(String nombreArchivo) throws IOException {
        grafo = new Grafo(nombreArchivo);
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Calcular ruta más corta entre ciudades.");
            System.out.println("2. Calcular el centro del grafo.");
            System.out.println("3. Modificar el grafo.");
            System.out.println("4. Salir.");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Ingrese la ciudad de origen: ");
                    String ciudadOrigen = scanner.nextLine();
                    System.out.print("Ingrese la ciudad de destino: ");
                    String ciudadDestino = scanner.nextLine();
                    try {
                        double distancia = grafo.distancia(ciudadOrigen, ciudadDestino);
                        List<String> ruta = grafo.ruta(ciudadOrigen, ciudadDestino);
                        if (distancia == Double.POSITIVE_INFINITY) {
                            System.out.println("No hay ruta entre las ciudades especificadas.");
                        } else {
                            System.out.println("La distancia más corta entre " + ciudadOrigen + " y " + ciudadDestino + " es " + distancia + " km.");
                            System.out.println("La ruta es: " + ruta);
                        }
                    } catch (Exception e) {
                        System.out.println("No hay ruta entre las ciudades especificadas.");
                    }
                    break;

                case "2":
                    String centro = grafo.calcularCentro();
                    System.out.println("El centro del grafo es: " + centro);
                    break;

                case "3":
                    modificarGrafo(scanner);
                    break;

                case "4":
                    System.out.println("¡Hasta luego!");
                    return;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    private void modificarGrafo(Scanner scanner) {
        System.out.println("\nOpciones de modificación:");
        System.out.println("1. Agregar una conexión.");
        System.out.println("2. Eliminar una conexión.");

        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                System.out.print("Ingrese el nombre de la ciudad de origen: ");
                String ciudad1 = scanner.nextLine();
                System.out.print("Ingrese el nombre de la ciudad de destino: ");
                String ciudad2 = scanner.nextLine();
                System.out.print("Ingrese la distancia en KM: ");
                double distancia = Double.parseDouble(scanner.nextLine());
                grafo.agregarConexion(ciudad1, ciudad2, distancia);
                System.out.println("Conexión agregada: " + ciudad1 + " -> " + ciudad2 + " con distancia " + distancia + " km.");
                break;

            case "2":
                System.out.print("Ingrese el nombre de la ciudad de origen: ");
                ciudad1 = scanner.nextLine();
                System.out.print("Ingrese el nombre de la ciudad de destino: ");
                ciudad2 = scanner.nextLine();
                grafo.eliminarConexion(ciudad1, ciudad2);
                System.out.println("Conexión eliminada: " + ciudad1 + " -> " + ciudad2 + ".");
                break;

            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                break;
        }
    }

    public static void main(String[] args) {
        try {
            Programa programa = new Programa("guategrafo.txt");
            programa.iniciar();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
