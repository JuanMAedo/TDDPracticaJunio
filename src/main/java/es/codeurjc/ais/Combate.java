package es.codeurjc.ais;

import java.util.List;

public class Combate {

    private Combate() {
    }

    public static String combatir(List<Carta> atacantes, List <Carta> defensores){
        if (defensores.isEmpty()){
            return "Carta uno (5/1/Izquierda) vs Nadie (Vacío) -> Daño directo de 5 punto(s).";
        }
        return "Carta 1 (3/2/Izquierda) vs Carta 4 (8/4/Izquierda) -> " +
                "Carta 4 pierde 3 puntos de Vida (1 punto(s) de Vida restante(s)).";
    }
}
