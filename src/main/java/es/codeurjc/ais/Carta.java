package es.codeurjc.ais;

public class Carta {
    private final String nombre;
    private final int ataque;
    private final int vida;
    private final Posicion posicion;

    public Carta(String nombre, int ataque, int vida, Posicion posicion) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.vida = vida;
        this.posicion = posicion;
    }

}
