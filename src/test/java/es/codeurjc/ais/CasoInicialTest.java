package es.codeurjc.ais;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoInicialTest {

    @Test
    public void casoUnoTest() {
        Carta uno = new Carta("uno", 5, 1, Tablero.IZQUIERDA);
        List<Carta> atacantes = new LinkedList<>();
        atacantes.add(uno);
        List<Carta> defensores = new LinkedList<>();
        String resultadoEsperado = "Carta uno (5/1/Izquierda) vs Nadie (Vacío) -> Daño directo de 5 punto(s).";
        assertEquals(resultadoEsperado,Combate.combatir(atacantes,defensores));
    }

}
