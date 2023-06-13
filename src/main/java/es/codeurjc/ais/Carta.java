package es.codeurjc.ais;

public class Carta {

    private final String nombre;
    private final int ataque;
    private final int defensa;
    private final Tablero tablero;

    public Carta (String nombre, int ataque, int defensa, Tablero tablero){
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tablero = tablero;
    }
}
