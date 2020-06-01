package io.github.ceraalex99.petandgo;

import helpers.Niveles;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NivelesTest {

    @Test
    public void NivelesTestPuntos() {
        int puntos = 100;
        Niveles niveles = Niveles.getInstance();
        assertEquals(6,niveles.getNivelPorPuntos(puntos));
        assertEquals(0,niveles.getNivelPorPuntos(5));
    }
}

