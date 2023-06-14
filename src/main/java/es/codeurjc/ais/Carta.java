package es.codeurjc.ais;

public class Carta {

    private final String nombre;
    private final int ataque;
    private final int defensa;
    private final Tablero tablero;

    public Carta(String nombre, int ataque, int defensa, Tablero tablero) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tablero = tablero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public Tablero getTablero() {
        return tablero;
    }

    @Override
    public String toString() {
        return "Carta " + this.getNombre() + " (" + this.getAtaque() + "/" + this.getDefensa() +
                "/" + tablero.toString() + ")";
    }

}
