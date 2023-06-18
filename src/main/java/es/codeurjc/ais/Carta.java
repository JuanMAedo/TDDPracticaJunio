package es.codeurjc.ais;

public class Carta {

    private final String nombre;
    private final int ataque;
    private final int vida;
    private Tablero tablero;
    private final Efecto[] efectos;

    public Carta(String nombre, int ataque, int vida, Tablero tablero) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.vida = vida;
        this.tablero = tablero;
        this.efectos = null;
    }

    public Carta(String nombre, int ataque, int vida, Tablero tablero, Efecto[] efectos) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.vida = vida;
        this.tablero = tablero;
        this.efectos = efectos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVida() {
        return vida;
    }

    public Tablero getTablero() {
        return tablero;
    }

    @Override
    public String toString() {
        return "Carta " + this.getNombre() + " (" + this.getAtaque() + "/" + this.getVida() +
                "/" + tablero.toString() + "/Efectos: " + ((efectos == null) ? "N/A" : stringEfectos()) + ")";
    }

    private String stringEfectos() {
        String cadenaEfectos = "";
        for (int i = 1; i < efectos.length; i++) {
            cadenaEfectos += efectos[i - 1].toString() + ", ";
        }
        cadenaEfectos += efectos[efectos.length - 1].toString();
        return cadenaEfectos;

    }

    public boolean esToqueMortal() {
        if (this.efectos != null) {
            for (int j = 0; j < this.efectos.length; j++) {
                if (this.efectos[j] == Efecto.TOQUEMORTAL) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean esAtaqueBifurcado() {
        if (this.efectos != null) {
            for (int j = 0; j < this.efectos.length; j++) {
                if (this.efectos[j] == Efecto.ATAQUEBIFURCADO) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean esAsustadizo() {
        if (this.efectos != null) {
            for (int j = 0; j < this.efectos.length; j++) {
                if (this.efectos[j] == Efecto.ASUSTADIZO) {
                    return true;
                }
            }
        }
        return false;
    }
}
