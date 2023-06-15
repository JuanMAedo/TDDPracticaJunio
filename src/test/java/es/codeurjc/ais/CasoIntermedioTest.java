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

    @Test
    public void casoDoceTest() throws IllegalPositionException{
        Carta uno = new Carta("uno", 3, 5, Tablero.IZQUIERDA, Efecto.ATAQUEBIFURCADO);
        Carta cuatro = new Carta("cuatro", 4, 2, Tablero.IZQUIERDA);
        atacantes.add(uno);
        defensores.add(cuatro);
        String resultadoEsperado = "Carta uno (3/5/Izquierda/Efecto: Ataque bifurcado) vs Carta cuatro " +
                "(4/2/Izquierda/Efecto: N/A) -> Daño directo de 3 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoTreceTest() throws IllegalPositionException{
        Carta tres = new Carta("tres", 5, 2, Tablero.DERECHA, Efecto.ATAQUEBIFURCADO);
        Carta cinco = new Carta("cinco", 7, 7, Tablero.CENTRO);
        atacantes.add(tres);
        defensores.add(cinco);
        String resultadoEsperado = "Carta tres (5/2/Derecha/Efecto: Ataque bifurcado) vs Carta cinco " +
                "(7/7/Centro/Efecto: N/A) -> Carta cinco pierde 5 punto(s) de "+
                "Vida (2 punto(s) de Vida restante(s)). ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoCatorceTest() throws IllegalPositionException{
        Carta dos = new Carta("dos", 2, 1, Tablero.CENTRO, Efecto.ATAQUEBIFURCADO);
        Carta cuatro = new Carta("cuatro", 4, 3, Tablero.IZQUIERDA);
        atacantes.add(dos);
        defensores.add(cuatro);
        String resultadoEsperado = "Carta dos (2/1/Centro/Efecto: Ataque bifurcado) vs Carta cuatro " +
                "(4/3/Izquierda/Efecto: N/A) -> Carta cuatro pierde 2 punto(s) de "+
                "Vida (1 punto(s) de Vida restante(s)). Carta dos (2/1/Centro/Efecto: Ataque bifurcado) "+
                "vs Nadie (Vacío) -> Daño directo de 2 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoQuinceTest() throws IllegalPositionException{
        Carta dos = new Carta("dos", 2, 1, Tablero.CENTRO, Efecto.ATAQUEBIFURCADO);
        Carta seis = new Carta("seis", 4, 3, Tablero.DERECHA);
        atacantes.add(dos);
        defensores.add(seis);
        String resultadoEsperado = "Carta dos (2/1/Centro/Efecto: Ataque bifurcado) vs Carta seis " +
                "(4/3/Derecha/Efecto: N/A) -> Carta seis pierde 2 punto(s) de "+
                "Vida (1 punto(s) de Vida restante(s)). Carta dos (2/1/Centro/Efecto: Ataque bifurcado) "+
                "vs Nadie (Vacío) -> Daño directo de 2 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }
    @Test
    public void casoDieciseisTest() throws IllegalPositionException{
        Carta dos = new Carta("dos", 2, 1, Tablero.CENTRO, Efecto.ATAQUEBIFURCADO);
        atacantes.add(dos);
        String resultadoEsperado = "Carta dos (2/1/Centro/Efecto: Ataque bifurcado) vs Nadie (Vacío) " +
                "-> Daño directo de 2 punto(s). "+
                "Carta dos (2/1/Centro/Efecto: Ataque bifurcado) vs Nadie (Vacío) " +
                "-> Daño directo de 2 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoDiecisieteTest() throws IllegalPositionException{
        Carta dos = new Carta("dos", 2, 1, Tablero.CENTRO, Efecto.ATAQUEBIFURCADO);
        Carta cuatro = new Carta("cuatro", 4, 3, Tablero.IZQUIERDA);
        Carta seis = new Carta("seis", 5, 1, Tablero.DERECHA);
        atacantes.add(dos);
        defensores.add(cuatro);
        defensores.add(seis);
        String resultadoEsperado = "Carta dos (2/1/Centro/Efecto: Ataque bifurcado) vs " +
                "Carta cuatro (4/3/Izquierda/Efecto: N/A) " +
                "-> Carta cuatro pierde 2 punto(s) de Vida (1 punto(s) de Vida restante(s)). " +
                "Carta dos (2/1/Centro/Efecto: Ataque bifurcado) vs Carta seis (5/1/Derecha/Efecto: N/A) " +
                "-> Carta seis pierde 2 punto(s) de Vida. Carta seis destruido/a. ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoDieciochoTest() throws IllegalPositionException{
        Carta dos = new Carta("dos", 13, 2, Tablero.CENTRO);
        Carta cinco = new Carta("cinco", 0, 1, Tablero.CENTRO, Efecto.ASUSTADIZO);
        atacantes.add(dos);
        defensores.add(cinco);
        String resultadoEsperado = "Carta dos (13/2/Centro/Efecto: N/A) vs " +
                "Carta cinco (0/1/Centro/Efecto: Asustadizo) " +
                "-> Daño directo de 13 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoDiecinueveTest() throws IllegalPositionException{
        Carta dos = new Carta("dos", 1, 2, Tablero.CENTRO);
        Carta cinco = new Carta("cinco", 0, 10, Tablero.CENTRO, Efecto.ASUSTADIZO);
        atacantes.add(dos);
        defensores.add(cinco);
        String resultadoEsperado = "Carta dos (1/2/Centro/Efecto: N/A) vs " +
                "Carta cinco (0/10/Centro/Efecto: Asustadizo) " +
                "-> Carta cinco pierde 1 punto(s) de Vida (9 punto(s) de Vida restante(s)). ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }
}
