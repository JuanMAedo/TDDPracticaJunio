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
        for (Carta atacante : atacantes) {
            int posicionDef;
            if(atacante.esAtaqueBifurcado()){
                 posicionDef = encontrarPosicion(Tablero.CENTRO, defensores);
            }else{
                 posicionDef = encontrarPosicion(atacante.getTablero(), defensores);
            }
            if (posicionDef != -1) { // Hay una carta Defensora en la posición Atacante
                Carta defensor = defensores.get(posicionDef);
                resultado.append(combateCartaDefensora(atacante, defensor));
            } else { // No hay Carta Defensora. Daño sobre el jugador
                resultado.append(atacante).append(" vs Nadie (Vacío) -> Daño directo de ")
                        .append(atacante.getAtaque()).append(" punto(s).");
            }
        }
        return resultado.toString();
    }


    private static String combateCartaDefensora( Carta atacante, Carta defensor) {
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
