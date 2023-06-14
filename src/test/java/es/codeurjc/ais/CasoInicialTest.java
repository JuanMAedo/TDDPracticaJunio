package es.codeurjc.ais;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void casoUnoTest() throws IllegalPositionException{
        Carta uno = new Carta("uno", 5, 1, Tablero.IZQUIERDA);
        atacantes.add(uno);
        String resultadoEsperado = "Carta uno (5/1/Izquierda/Efecto: N/A) vs Nadie (Vacío) -> Daño directo de 5 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoDosTest() throws IllegalPositionException{
        Carta uno = new Carta("uno", 3, 2, Tablero.IZQUIERDA);
        Carta cuatro = new Carta("cuatro", 8, 4, Tablero.IZQUIERDA);
        atacantes.add(uno);
        defensores.add(cuatro);
        String resultadoEsperado = "Carta uno (3/2/Izquierda/Efecto: N/A) vs Carta cuatro (8/4/Izquierda/Efecto: N/A)" +
                " -> Carta cuatro pierde 3 punto(s) de Vida (1 punto(s) de Vida restante(s)). ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoTresTest() throws IllegalPositionException{
        Carta dos = new Carta("dos", 3, 2, Tablero.CENTRO);
        Carta cinco = new Carta("cinco", 8, 1, Tablero.CENTRO);
        atacantes.add(dos);
        defensores.add(cinco);
        String resultadoEsperado = "Carta dos (3/2/Centro/Efecto: N/A) vs Carta cinco (8/1/Centro/Efecto: N/A) -> " +
                "Carta cinco pierde 3 punto(s) de Vida. Carta cinco destruido/a. ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoCuatroTest() throws IllegalPositionException{
        Carta tres = new Carta("tres", 6, 3, Tablero.DERECHA);
        Carta cinco = new Carta("cinco", 3, 1, Tablero.IZQUIERDA);
        atacantes.add(tres);
        defensores.add(cinco);
        String resultadoEsperado = "Carta tres (6/3/Derecha/Efecto: N/A) vs Nadie (Vacío) -> " +
                "Daño directo de 6 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoCincoTest() throws IllegalPositionException{
        Carta dos = new Carta("dos", 3, 1, Tablero.CENTRO);
        Carta seis = new Carta("seis", 6, 3, Tablero.DERECHA);
        Carta cinco = new Carta("cinco", 0, 3, Tablero.CENTRO);
        atacantes.add(dos);
        atacantes.add(seis);
        defensores.add(cinco);
        String resultadoEsperado = "Carta dos (3/1/Centro/Efecto: N/A) vs Carta cinco (0/3/Centro/Efecto: N/A) -> " +
                "Carta cinco pierde 3 punto(s) de Vida. Carta cinco destruido/a. " +
                "Carta seis (6/3/Derecha/Efecto: N/A) vs Nadie (Vacío) -> Daño directo de 6 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoSeisTest() throws IllegalPositionException{
        Carta uno = new Carta("uno", 7, 2, Tablero.IZQUIERDA);
        Carta dos = new Carta("dos", 2, 1, Tablero.CENTRO);
        Carta tres = new Carta("tres", 0, 4, Tablero.DERECHA);
        Carta cuatro = new Carta("cuatro", 5, 3, Tablero.IZQUIERDA);
        Carta cinco = new Carta("cinco", 0, 1, Tablero.CENTRO);
        Carta seis = new Carta("seis", 4, 7, Tablero.DERECHA);
        atacantes.add(uno);
        atacantes.add(dos);
        atacantes.add(tres);
        defensores.add(seis);
        defensores.add(cinco);
        defensores.add(cuatro);
        String resultadoEsperado = "Carta uno (7/2/Izquierda/Efecto: N/A) vs Carta cuatro (5/3/Izquierda/Efecto: N/A) -> " +
                "Carta cuatro pierde 7 punto(s) de Vida. Carta cuatro destruido/a. " +
                "Carta dos (2/1/Centro/Efecto: N/A) vs Carta cinco (0/1/Centro/Efecto: N/A) -> " +
                "Carta cinco pierde 2 punto(s) de Vida. Carta cinco destruido/a. " +
                "Carta tres (0/4/Derecha/Efecto: N/A) vs Carta seis (4/7/Derecha/Efecto: N/A) -> " +
                "Carta seis pierde 0 punto(s) de Vida (7 punto(s) de Vida restante(s)). ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }



    @Test
    public void casoSieteTest() {
        Carta uno = new Carta("uno", 3, 2, Tablero.IZQUIERDA);
        Carta dos = new Carta("dos", 5, 3, Tablero.IZQUIERDA);
        Carta tres = new Carta("tres", 5, 3, Tablero.IZQUIERDA);
        atacantes.add(uno);
        atacantes.add(dos);
        defensores.add(tres);

        Throwable excepcion = assertThrows(IllegalPositionException.class, () -> {
            String resultado= Combate.combatir(atacantes, defensores);
        });
        assertEquals("No pueden existir múltiples cartas en la misma posición del tablero", excepcion.getMessage());
    }
}
