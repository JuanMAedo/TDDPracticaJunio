package es.codeurjc.ais;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoInicialTest {

    @Test
    public void casoUnoTest() {
        Carta uno = new Carta("Carta 1", 5, 1, Posicion.Izquierda);
        String resultadoEsperado = "Carta 1 (5/1/Izquierda) vs Nadie (Vacío) -> " +
                "Daño directo de 5 punto(s).";
        assertEquals(resultadoEsperado,Combate.combatir(uno));
    }

}
