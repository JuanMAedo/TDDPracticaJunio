package es.codeurjc.ais;

public enum Tablero {
    IZQUIERDA("Izquierda"), CENTRO("Centro");


    private final String nombre;

    // Constructor para setear el string
    Tablero(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}

