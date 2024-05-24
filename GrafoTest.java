import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GrafoTest {
    private Grafo grafo;

    @Before
    public void setUp() {
        grafo = new Grafo("guategrafo.txt");
    }

    @Test
    public void testDistancia() {
        assertEquals(30.0, grafo.distancia("Mixco", "Antigua"), 0.01);
        assertEquals(25.0, grafo.distancia("Antigua", "Escuintla"), 0.01);
        assertEquals(15.0, grafo.distancia("Escuintla", "SantaLucia"), 0.01);
    }

    @Test
    public void testRuta() {
        assertEquals("[Mixco, Antigua]", grafo.ruta("Mixco", "Antigua").toString());
        assertEquals("[Antigua, Escuintla]", grafo.ruta("Antigua", "Escuintla").toString());
        assertEquals("[Escuintla, SantaLucia]", grafo.ruta("Escuintla", "SantaLucia").toString());
    }

    @Test
    public void testCentro() {
        assertEquals("Antigua", grafo.calcularCentro());
    }

    @Test
    public void testAgregarEliminarConexion() {
        grafo.agregarConexion("Mixco", "CiudadNueva", 40.0);
        assertEquals(40.0, grafo.distancia("Mixco", "CiudadNueva"), 0.01);

        grafo.eliminarConexion("Mixco", "CiudadNueva");
        assertEquals(Double.POSITIVE_INFINITY, grafo.distancia("Mixco", "CiudadNueva"), 0.01);
    }
}
