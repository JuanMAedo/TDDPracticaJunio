package es.codeurjc.ais;

import java.util.List;

public class Combate {

    private Combate() {
    }

    public static String combatir(List<Carta> atacantes, List<Carta> defensores) {
        StringBuilder resultado = new StringBuilder();
        for (Carta atacante : atacantes) {
            int posicionDef = encontrarPosicion(atacante.getTablero(), defensores);
            if (posicionDef != -1) {
                resultado.append(atacante).append(" vs ").append(defensores.get(posicionDef).toString())
                        .append(" -> Carta ").append(defensores.get(posicionDef).getNombre()).append(" pierde ")
                        .append(atacante.getAtaque()).append(" punto(s) de Vida");
                if (defensores.get(posicionDef).getDefensa() <= atacante.getAtaque()) {
                    resultado.append(". Carta ").append(defensores.get(posicionDef).getNombre())
                            .append(" destruido/a. ");
                } else {
                    resultado.append(" (").append(vidaRestante(defensores.get(posicionDef).getDefensa(), atacante.getAtaque()))
                            .append(" punto(s) de Vida restante(s)). ");
                }
            } else {
                resultado.append(atacante).append(" vs Nadie (Vacío) -> Daño directo de ")
                        .append(atacante.getAtaque()).append(" punto(s).");
            }
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

}
