package loup.garou.Models;

import java.io.Serializable;

public class Voyante extends Villageois implements Serializable{

    public Voyante() {
        String newLine = System.getProperty("line.separator");
        this.nom = "Voyante";
        this.description = "Son objectif est d'éliminer tous les Loups-Garous. "+newLine
                + "Chaque nuit,"+newLine+"elle peut espionner un joueur"+newLine+"et découvrir sa véritable identité.";
    }

}
