package es.codeurjc.ais;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class casoFinalTest {
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
    public void casoVeinteTest() throws IllegalPositionException {
        Carta dos = new Carta("dos", 2, 1, Tablero.CENTRO,new Efecto[]{Efecto.TOQUEMORTAL, Efecto.ATAQUEBIFURCADO});
        Carta cuatro = new Carta("cuatro", 4,5, Tablero.IZQUIERDA);
        atacantes.add(dos);
        defensores.add(cuatro);
        String resultadoEsperado = "Carta dos (2/1/Centro/Efectos: Toque mortal, Ataque bifurcado) vs " +
                "Carta cuatro (4/5/Izquierda/Efectos: N/A) -> Carta cuatro destruido/a. "+
                "Carta dos (2/1/Centro/Efectos: Toque mortal, Ataque bifurcado) vs Nadie (Vacío) -> " +
                "Daño directo de 2 punto(s).";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }
}
