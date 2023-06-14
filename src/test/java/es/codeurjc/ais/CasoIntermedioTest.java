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

    @Test
    public void casoNueveTest() throws IllegalPositionException{
        Carta dos = new Carta("dos", 4, 2, Tablero.CENTRO);
        Carta cinco = new Carta("cinco", 0, 5, Tablero.CENTRO, Efecto.TOQUEMORTAL);
        atacantes.add(dos);
        defensores.add(cinco);
        String resultadoEsperado = "Carta dos (4/2/Centro/Efecto: N/A) vs Carta cinco " +
                "(0/5/Centro/Efecto: Toque mortal) -> Carta cinco pierde 4 punto(s) de " +
                "Vida (1 punto(s) de Vida restante(s)). Carta dos destruido/a. ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoDiezTest() throws IllegalPositionException{
        Carta tres = new Carta("tres", 4, 2, Tablero.DERECHA);
        Carta seis = new Carta("seis", 8, 1, Tablero.DERECHA, Efecto.TOQUEMORTAL);
        atacantes.add(tres);
        defensores.add(seis);
        String resultadoEsperado = "Carta tres (4/2/Derecha/Efecto: N/A) vs Carta seis " +
                "(8/1/Derecha/Efecto: Toque mortal) -> Carta seis pierde 4 punto(s) de " +
                "Vida. Carta seis destruido/a. Carta tres destruido/a. ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoOnceTest() throws IllegalPositionException{
        Carta uno = new Carta("uno", 3, 5, Tablero.IZQUIERDA, Efecto.ATAQUEBIFURCADO);
        Carta cinco = new Carta("cinco", 4, 2, Tablero.CENTRO);
        atacantes.add(uno);
        defensores.add(cinco);
        String resultadoEsperado = "Carta uno (3/5/Izquierda/Efecto: Ataque bifurcado) vs Carta cinco " +
                "(4/2/Centro/Efecto: N/A) -> Carta cinco pierde 3 punto(s) de " +
                "Vida. Carta cinco destruido/a. ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }
}
