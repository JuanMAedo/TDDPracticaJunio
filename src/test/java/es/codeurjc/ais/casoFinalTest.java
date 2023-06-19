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

    @Test
    public void casoVeintiunoTest() throws IllegalPositionException {
        Carta dos = new Carta("dos", 2, 1, Tablero.CENTRO,new Efecto[]{Efecto.TOQUEMORTAL, Efecto.ATAQUEBIFURCADO});
        Carta cuatro = new Carta("cuatro", 4,5, Tablero.IZQUIERDA);
        Carta cinco = new Carta("cinco", 7,2, Tablero.DERECHA);
        atacantes.add(dos);
        defensores.add(cuatro);
        defensores.add(cinco);
        String resultadoEsperado = "Carta dos (2/1/Centro/Efectos: Toque mortal, Ataque bifurcado) vs " +
                "Carta cuatro (4/5/Izquierda/Efectos: N/A) -> Carta cuatro destruido/a. "+
                "Carta dos (2/1/Centro/Efectos: Toque mortal, Ataque bifurcado) vs Carta cinco (7/2/Derecha/Efectos: N/A) -> " +
                "Carta cinco destruido/a. ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

    @Test
    public void casoVeintidosTest() throws IllegalPositionException {
        Carta dos = new Carta("dos", 2, 1, Tablero.CENTRO,new Efecto[]{Efecto.TOQUEMORTAL, Efecto.ATAQUEBIFURCADO});
        Carta cuatro = new Carta("cuatro", 4,5, Tablero.IZQUIERDA);
        Carta cinco = new Carta("cinco", 3,8, Tablero.DERECHA,new Efecto[]{Efecto.TOQUEMORTAL});
        atacantes.add(dos);
        defensores.add(cuatro);
        defensores.add(cinco);
        String resultadoEsperado = "Carta dos (2/1/Centro/Efectos: Toque mortal, Ataque bifurcado) vs " +
                "Carta cuatro (4/5/Izquierda/Efectos: N/A) -> Carta cuatro destruido/a. "+
                "Carta dos (2/1/Centro/Efectos: Toque mortal, Ataque bifurcado) vs Carta cinco (3/8/Derecha/Efectos: Toque mortal) -> " +
                "Carta cinco destruido/a. Carta dos destruido/a. ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }
    @Test
    public void casoVeintitresTest() throws IllegalPositionException {
        Carta dos = new Carta("dos", 2, 1, Tablero.CENTRO,new Efecto[]{Efecto.TOQUEMORTAL, Efecto.ATAQUEBIFURCADO});
        Carta cuatro = new Carta("cuatro", 4,5, Tablero.IZQUIERDA,new Efecto[]{Efecto.TOQUEMORTAL});
        Carta cinco = new Carta("cinco", 3,8, Tablero.DERECHA,new Efecto[]{Efecto.TOQUEMORTAL});
        atacantes.add(dos);
        defensores.add(cuatro);
        defensores.add(cinco);
        String resultadoEsperado = "Carta dos (2/1/Centro/Efectos: Toque mortal, Ataque bifurcado) vs " +
                "Carta cuatro (4/5/Izquierda/Efectos: Toque mortal) -> Carta cuatro destruido/a. Carta dos destruido/a. ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }
    @Test
    public void casoVeinticuatroTest() throws IllegalPositionException {
        Carta dos = new Carta("dos", 4, 2, Tablero.IZQUIERDA,new Efecto[]{ Efecto.ATAQUEBIFURCADO});
        Carta tres = new Carta("tres", 2, 1, Tablero.CENTRO,new Efecto[]{Efecto.TOQUEMORTAL, Efecto.ATAQUEBIFURCADO});
        Carta cuatro = new Carta("cuatro", 4,5, Tablero.IZQUIERDA,new Efecto[]{Efecto.ASUSTADIZO});
        Carta cinco = new Carta("cinco", 3,8, Tablero.DERECHA,new Efecto[]{Efecto.TOQUEMORTAL});
        Carta seis = new Carta("seis", 12,2, Tablero.CENTRO,new Efecto[]{Efecto.ASUSTADIZO});
        atacantes.add(dos);
        atacantes.add(tres);
        defensores.add(cuatro);
        defensores.add(cinco);
        defensores.add(seis);
        String resultadoEsperado = "Carta dos (4/2/Izquierda/Efectos: Ataque bifurcado) vs " +
                "Carta seis (12/2/Centro/Efectos: Asustadizo) -> Daño directo de 4 punto(s)." +
                "Carta tres (2/1/Centro/Efectos: Toque mortal, Ataque bifurcado) vs " +
                "Carta cuatro (4/5/Izquierda/Efectos: Asustadizo) -> Carta cuatro destruido/a. "+
                "Carta tres (2/1/Centro/Efectos: Toque mortal, Ataque bifurcado) vs Carta cinco (3/8/Derecha/Efectos: Toque mortal) -> " +
                "Carta cinco destruido/a. Carta tres destruido/a. ";
        assertEquals(resultadoEsperado, Combate.combatir(atacantes, defensores));
    }

}
