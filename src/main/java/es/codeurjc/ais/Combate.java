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
                continue;
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
            if (encontrarPosicion(Tablero.IZQUIERDA, defensores) != -1) { // Defensor en Izquierda
                defensorIzq = defensores.get(encontrarPosicion(Tablero.IZQUIERDA, defensores));
                resultado = combateCartaDefensora(atacante, defensorIzq);
                if (!defensorIzq.esToqueMortal() && encontrarPosicion(Tablero.DERECHA, defensores) != -1) {
                    //Defensor en la derecha y no ha muerto el atacante
                    defensorDer = defensores.get(encontrarPosicion(Tablero.DERECHA, defensores));
                    resultado += combateCartaDefensora(atacante, defensorDer);
                } else if (!defensorIzq.esToqueMortal()) {// Defensor sólo Izquierda y no ha muerto el atacante
                    resultado += combateSinCartaDefensora(atacante, "Nadie (Vacío)");
                }
            } else if (encontrarPosicion(Tablero.DERECHA, defensores) != -1) {// Defensor sólo Derecha
                defensorDer = defensores.get(encontrarPosicion(Tablero.DERECHA, defensores));
                resultado = combateCartaDefensora(atacante, defensorDer);
                if (!defensorDer.esToqueMortal()) {
                    // No ha muerto el atacante
                    resultado += combateSinCartaDefensora(atacante, "Nadie (Vacío)");
                }
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
        } else {
            resultado.append(" Carta ").append(defensor.getNombre()).append(" pierde ")
                    .append(atacante.getAtaque()).append(" punto(s) de Vida");
        }
        if (defensor.getVida() <= atacante.getAtaque() && !atacante.esToqueMortal()) {
            if (defensor.esAsustadizo()) {
                resultado = new StringBuilder(combateSinCartaDefensora(atacante, defensor.toString()));
            } else {
                resultado.append(". Carta ").append(defensor.getNombre()).append(" destruido/a. ");
            }
        } else if (!atacante.esToqueMortal()) {
            resultado.append(" (").append(vidaRestante(atacante, defensor))
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

    private static int vidaRestante(Carta atacante, Carta defensor) {
        return defensor.getVida() - atacante.getAtaque();
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
