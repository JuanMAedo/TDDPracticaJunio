package es.codeurjc.ais;

import java.util.List;

public class Combate {

    private Combate() {
    }

    public static String combatir( List<Carta> atacantes, List <Carta> defensores){
        String resultado = "";
        if (defensores.isEmpty()){
            return atacantes.get(0).toString() + " vs Nadie (Vacío) -> Daño directo de " + atacantes.get(0).getAtaque()+ " punto(s).";
        }
        resultado += atacantes.get(0).toString()+ " vs " + defensores.get(0).toString() + " -> Carta " +
               defensores.get(0).getNombre() + " pierde " +  atacantes.get(0).getAtaque() +" puntos de Vida";
        if(defensores.get(0).getDefensa() <= atacantes.get(0).getAtaque()){
            resultado += ". Carta " + defensores.get(0).getNombre() + " destruido/a.";
        }else{
            resultado += " (" + vidaRestante(defensores.get(0).getDefensa(),atacantes.get(0).getAtaque()) + " punto(s) de Vida restante(s)).";
        }
        return resultado;
    }

    private static int vidaRestante(int defensa,int ataque){
        return defensa - ataque ;
    }
}
