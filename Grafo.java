import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grafo {
    private final Map<String, Integer> ciudades;
    private final double[][] distancias;
    private final int n;

    public Grafo(String nombreArchivo) throws IOException {
        ciudades = new HashMap<>();
        List<String[]> conexiones = leerArchivo(nombreArchivo);
        n = ciudades.size();
        distancias = new double[n][n];
        inicializarDistancias(conexiones);
    }

    private List<String[]> leerArchivo(String nombreArchivo) throws IOException {
        List<String[]> conexiones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                if (!ciudades.containsKey(partes[0])) {
                    ciudades.put(partes[0], ciudades.size());
                }
                if (!ciudades.containsKey(partes[1])) {
                    ciudades.put(partes[1], ciudades.size());
                }
                conexiones.add(partes);
            }
        }
        return conexiones;
    }

    private void inicializarDistancias(List<String[]> conexiones) {
        for (int i = 0; i < n; i++) {
            Arrays.fill(distancias[i], Double.POSITIVE_INFINITY);
            distancias[i][i] = 0;
        }

        for (String[] conexion : conexiones) {
            int ciudad1 = ciudades.get(conexion[0]);
            int ciudad2 = ciudades.get(conexion[1]);
            double distancia = Double.parseDouble(conexion[2].replaceAll("[^0-9.]", ""));
            distancias[ciudad1][ciudad2] = distancia;
        }
    }

    public void algoritmoFloydWarshall() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][j] > distancias[i][k] + distancias[k][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }
    }

    public String calcularCentro() {
        algoritmoFloydWarshall();
        double menorDistanciaMayor = Double.POSITIVE_INFINITY;
        int centro = -1;

        for (int i = 0; i < n; i++) {
            double mayorDistancia = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && distancias[i][j] > mayorDistancia) {
                    mayorDistancia = distancias[i][j];
                }
            }
            if (mayorDistancia < menorDistanciaMayor) {
                menorDistanciaMayor = mayorDistancia;
                centro = i;
            }
        }

        for (Map.Entry<String, Integer> entry : ciudades.entrySet()) {
            if (entry.getValue() == centro) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void agregarConexion(String ciudad1, String ciudad2, double distancia) {
        if (!ciudades.containsKey(ciudad1)) {
            ciudades.put(ciudad1, ciudades.size());
        }
        if (!ciudades.containsKey(ciudad2)) {
            ciudades.put(ciudad2, ciudades.size());
        }

        int idxCiudad1 = ciudades.get(ciudad1);
        int idxCiudad2 = ciudades.get(ciudad2);

        distancias[idxCiudad1][idxCiudad2] = distancia;
    }

    public void eliminarConexion(String ciudad1, String ciudad2) {
        if (ciudades.containsKey(ciudad1) && ciudades.containsKey(ciudad2)) {
            int idxCiudad1 = ciudades.get(ciudad1);
            int idxCiudad2 = ciudades.get(ciudad2);

            distancias[idxCiudad1][idxCiudad2] = Double.POSITIVE_INFINITY;
        }
    }

    public double distancia(String ciudad1, String ciudad2) {
        int idxCiudad1 = ciudades.get(ciudad1);
        int idxCiudad2 = ciudades.get(ciudad2);

        return distancias[idxCiudad1][idxCiudad2];
    }

    public List<String> ruta(String ciudad1, String ciudad2) {
        int idxCiudad1 = ciudades.get(ciudad1);
        int idxCiudad2 = ciudades.get(ciudad2);

        List<String> path = new ArrayList<>();
        if (distancias[idxCiudad1][idxCiudad2] == Double.POSITIVE_INFINITY) {
            return path;
        }

        path.add(ciudad1);
        while (idxCiudad1 != idxCiudad2) {
            for (int k = 0; k < n; k++) {
                if (distancias[idxCiudad1][k] + distancias[k][idxCiudad2] == distancias[idxCiudad1][idxCiudad2]) {
                    idxCiudad1 = k;
                    break;
                }
            }
            for (Map.Entry<String, Integer> entry : ciudades.entrySet()) {
                if (entry.getValue() == idxCiudad1) {
                    path.add(entry.getKey());
                    break;
                }
            }
        }
        return path;
    }
}
