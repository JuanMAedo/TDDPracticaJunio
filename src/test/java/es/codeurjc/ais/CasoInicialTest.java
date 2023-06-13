package es.codeurjc.ais;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoInicialTest {

    private static List<Carta> atacantes, defensores;

    @BeforeAll
     static void setUp() {
        atacantes = new LinkedList<>();
        defensores = new LinkedList<>();
    }

    @AfterEach
    public void vaciarListas() {
        atacantes.clear();
        defensores.clear();
    }

    @Test
    public void casoUnoTest() {
        Carta uno = new Carta("uno", 5, 1, Tablero.IZQUIERDA);
        atacantes.add(uno);
        String resultadoEsperado = "Carta uno (5/1/Izquierda) vs Nadie (Vacío) -> Daño directo de 5 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoDosTest() {
        Carta uno = new Carta("uno", 3, 2, Tablero.IZQUIERDA);
        Carta cuatro = new Carta("cuatro", 8, 4, Tablero.IZQUIERDA);
        atacantes.add(uno);
        defensores.add(cuatro);
        String resultadoEsperado = "Carta 1 (3/2/Izquierda) vs Carta 4 (8/4/Izquierda) -> " +
                "Carta 4 pierde 3 puntos de Vida (1 punto(s) de Vida restante(s)).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

}
