
package es.codeurjc.ais;

public enum Efecto {

    TOQUEMORTAL("Toque mortal"), ATAQUEBIFURCADO("Ataque bifurcado");

    private final String nombre;

    // Constructor para setear el string
    Efecto(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
