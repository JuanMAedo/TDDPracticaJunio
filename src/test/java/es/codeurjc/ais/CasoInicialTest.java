package es.codeurjc.ais;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        String resultadoEsperado = "Carta uno (3/2/Izquierda) vs Carta cuatro (8/4/Izquierda) -> " +
                "Carta cuatro pierde 3 puntos de Vida (1 punto(s) de Vida restante(s)).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoTresTest() {
        Carta dos = new Carta("dos", 3, 2, Tablero.CENTRO);
        Carta cinco = new Carta("cinco", 8, 1, Tablero.CENTRO);
        atacantes.add(dos);
        defensores.add(cinco);
        String resultadoEsperado = "Carta dos (3/2/Centro) vs Carta cinco (8/1/Centro) -> " +
                "Carta cinco pierde 3 puntos de Vida. Carta cinco destruido/a.";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoCuatroTest() {
        Carta tres = new Carta("tres", 6, 3, Tablero.DERECHA);
        Carta cinco = new Carta("cinco", 3, 1, Tablero.CENTRO);
        atacantes.add(tres);
        defensores.add(cinco);
        String resultadoEsperado = "Carta tres (6/3/Derecha) vs Nadie (Vacío) -> " +
                "Daño directo de 6 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

}
