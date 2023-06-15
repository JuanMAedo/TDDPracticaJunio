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
            if (!atacante.esAtaqueBifurcado()) {
                posicionDef = encontrarPosicion(atacante.getTablero(), defensores);
            } else {
                resultado.append(ataqueBifurcado(atacante, defensores));
                break;
            }
            if (posicionDef != -1) { // Hay una carta Defensora en la posición Atacante
                Carta defensor = defensores.get(posicionDef);
                resultado.append(combateCartaDefensora(atacante, defensor));
            }
            if (posicionDef == -1) { // No hay Carta Defensora. Daño sobre el jugador
                resultado.append(combateSinCartaDefensora(atacante, "Nadie (Vacío)"));
            }
        }
        return resultado.toString();
    }

    private static String ataqueBifurcado(Carta atacante, List<Carta> defensores) {
        String resultado, rival;
        int posicion;
        if (!atacante.getTablero().equals(Tablero.CENTRO)) { // Atacamos sólo al Centro

            posicion = encontrarPosicion(Tablero.CENTRO, defensores);
            if (posicion != -1) { // Tenemos rival en el Centro
                resultado = combateCartaDefensora(atacante, defensores.get(posicion));
            } else { // No tenemos rival en el Centro pero miramos si tenemos enfrente o no
                posicion = encontrarPosicion(atacante.getTablero(), defensores);
                rival = (posicion != -1) ? defensores.get(posicion).toString() : "Nadie (Vacío)";
                resultado = combateSinCartaDefensora(atacante, rival);
            }
        } else { // Atacamos a Izquierda y Derecha

            Carta defensorIzq, defensorDer;
            if ((encontrarPosicion(Tablero.IZQUIERDA, defensores) != -1) &&
                    (encontrarPosicion(Tablero.DERECHA, defensores) != -1)) { // Defensor ambos lados
                defensorIzq = defensores.get(encontrarPosicion(Tablero.IZQUIERDA, defensores));
                defensorDer = defensores.get(encontrarPosicion(Tablero.DERECHA, defensores));
                resultado = combateCartaDefensora(atacante, defensorIzq);
                resultado += combateCartaDefensora(atacante, defensorDer);
            } else if (encontrarPosicion(Tablero.IZQUIERDA, defensores) != -1) {// Defensor sólo Izquierda
                defensorIzq = defensores.get(encontrarPosicion(Tablero.IZQUIERDA, defensores));
                resultado = combateCartaDefensora(atacante, defensorIzq);
                resultado += combateSinCartaDefensora(atacante, "Nadie (Vacío)");
            } else if (encontrarPosicion(Tablero.DERECHA, defensores) != -1) {// Defensor sólo Derecha
                defensorDer = defensores.get(encontrarPosicion(Tablero.DERECHA, defensores));
                resultado = combateCartaDefensora(atacante, defensorDer);
                resultado += combateSinCartaDefensora(atacante, "Nadie (Vacío)");
            } else { // No hay defensor ni en Izq ni en Der
                resultado = combateSinCartaDefensora(atacante, "Nadie (Vacío)") + " ";
                resultado += combateSinCartaDefensora(atacante, "Nadie (Vacío)");
            }
        }
        return resultado;
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
            if (defensor.esAsustadizo()) {
                resultado = new StringBuilder(combateSinCartaDefensora(atacante, defensor.toString()));
            } else {
                resultado.append(". Carta ").append(defensor.getNombre()).append(" destruido/a. ");
            }
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
        return atacante + " vs " + rival + " -> Daño directo de " + atacante.getAtaque() + " punto(s).";
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
