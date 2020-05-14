package loup.garou.Models;

import java.io.Serializable;

public class Cupidon extends Villageois implements Serializable{

    public Cupidon() {
        String newLine = System.getProperty("line.separator");
        this.nom = "Cupidon";
        this.description = "Son objectif est d'éliminer tous les Loups-Garous. "+newLine
                + " Dès le début de la partie,"+newLine+" il doit former un couple de deux joueurs. "
                +newLine+"Leur objectif sera de survivre ensemble, "
                +newLine+"car si l'un d'eux meurt, l'autre se suicidera.";
    }

}
