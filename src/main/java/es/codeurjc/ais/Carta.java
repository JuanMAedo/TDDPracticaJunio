package es.codeurjc.ais;

public class Carta {

    private final String nombre;
    private final int ataque;
    private final int defensa;
    private  Tablero tablero;
    private final Efecto efecto;

    public Carta(String nombre, int ataque, int defensa, Tablero tablero) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tablero = tablero;
        this.efecto = null;
    }
    public Carta(String nombre, int ataque, int defensa, Tablero tablero, Efecto efecto) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tablero = tablero;
        this.efecto = efecto;
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

    public boolean esToqueMortal() {
        return this.efecto == Efecto.TOQUEMORTAL;
    }
    @Override
    public String toString() {
        return "Carta " + this.getNombre() + " (" + this.getAtaque() + "/" + this.getDefensa() +
                "/" + tablero.toString() + "/Efecto: " + ((efecto == null) ? "N/A" : efecto.toString()) + ")";
    }

    public boolean esAtaqueBifurcado() {
        return this.efecto == Efecto.ATAQUEBIFURCADO;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
}
