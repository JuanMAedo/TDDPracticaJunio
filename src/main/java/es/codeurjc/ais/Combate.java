package es.codeurjc.ais;

import java.util.List;

public class Combate {

    private Combate() {
    }

    public static String combatir(List<Carta> atacantes, List<Carta> defensores) throws IllegalPositionException {
        if (posicionTableroRepetidas(atacantes) || posicionTableroRepetidas(defensores)) {
            throw new IllegalPositionException("No pueden existir múltiples cartas en la misma posición del tablero");
        }
        StringBuilder resultado = new StringBuilder();
        int posicionDef;
        for (Carta atacante : atacantes) {
            posicionDef = posicionAtaque(atacante, defensores);
            if (posicionDef != -1) { // Hay una carta Defensora en la posición Atacante
                Carta defensor = defensores.get(posicionDef);
                resultado.append(combateCartaDefensora(atacante, defensor));
            }
            if ((atacante.esAtaqueBifurcado() && atacante.getTablero().equals(Tablero.CENTRO))
                    || posicionDef == -1) { // No hay Carta Defensora. Daño sobre el jugador
                int posicionDef2 = encontrarPosicion(atacante.getTablero(), defensores);
                String rival;
                rival = (posicionDef2 != -1) ? defensores.get(posicionDef2).toString() :
                        "Nadie (Vacío)";
                resultado.append(combateSinCartaDefensora(atacante, rival));
            }
        }
        return resultado.toString();
    }


    private static String combateCartaDefensora(Carta atacante, Carta defensor) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(atacante).append(" vs ").append(defensor).append(" ->");
        if (atacante.esToqueMortal()) {
            resultado.append(" Carta ").append(defensor.getNombre()).append(" destruido/a. ");
            return resultado.toString();
        }
        resultado.append(" Carta ").append(defensor.getNombre()).append(" pierde ")
                .append(atacante.getAtaque()).append(" punto(s) de Vida");
        if (defensor.getDefensa() <= atacante.getAtaque()) {
            resultado.append(". Carta ").append(defensor.getNombre()).append(" destruido/a. ");
        } else {
            resultado.append(" (").append(vidaRestante(defensor.getDefensa(), atacante.getAtaque()))
                    .append(" punto(s) de Vida restante(s)). ");
        }
        if (defensor.esToqueMortal()) {
            resultado.append("Carta ").append(atacante.getNombre()).append(" destruido/a. ");
        }
        return resultado.toString();
    }

    private static String combateSinCartaDefensora(Carta atacante, String rival) {
        return atacante + " vs " + rival +
                " -> Daño directo de " + atacante.getAtaque() + " punto(s).";
    }

    private static int posicionAtaque(Carta atacante, List<Carta> defensores) {
        if (!atacante.esAtaqueBifurcado()) {
            return encontrarPosicion(atacante.getTablero(), defensores);
        } else if (atacante.getTablero().equals(Tablero.CENTRO)) {
            return encontrarPosicion(Tablero.IZQUIERDA, defensores);
        } else {
            return encontrarPosicion(Tablero.CENTRO, defensores);
        }
    }

    private static int vidaRestante(int defensa, int ataque) {

        return defensa - ataque;
    }

    private static int encontrarPosicion(Tablero posicion, List<Carta> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTablero().equals(posicion)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean posicionTableroRepetidas(List<Carta> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getTablero() == lista.get(j).getTablero()) {
                    return true;
                }
            }
        }
        return false;
    }

}
