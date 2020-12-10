package infrastructure;

import domain.services.CalculadoraDistancia;
import domain.services.Posicion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculadoraDistanciaTest {

    @Test
    public void calcularDistancia() {
        Posicion p1 = new Posicion(41.356943, 2.102227);
        Posicion p2 = new Posicion(41.356258, 2.103440);
        assertEquals(126, CalculadoraDistancia.getDistanciaMetros(p1,p2));
        assertEquals(0, CalculadoraDistancia.getDistanciaMetros(p1,p1));
    }
}

