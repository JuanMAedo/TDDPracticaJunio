package es.codeurjc.ais;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoIntermedioTest {

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
    public void casoOchoTest() throws IllegalPositionException{
        Carta uno = new Carta("uno", 5, 3, Tablero.IZQUIERDA, Efecto.TOQUEMORTAL);
        Carta cuatro = new Carta("cuatro", 2, 7, Tablero.IZQUIERDA);
        atacantes.add(uno);
        defensores.add(cuatro);
        String resultadoEsperado = "Carta uno (5/3/Izquierda/Efecto: Toque mortal) vs Carta cuatro " +
                "(2/7/Izquierda/Efecto: N/A) -> Carta cuatro destruido/a. ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }
}
