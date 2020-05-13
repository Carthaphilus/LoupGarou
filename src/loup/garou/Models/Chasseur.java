package loup.garou.Models;

import java.io.Serializable;

public class Chasseur extends Villageois implements Serializable{

    public Chasseur() {
        String newLine = System.getProperty("line.separator");
        this.nom = "Chasseur";
        this.description = "Son objectif est d'éliminer tous les Loups-Garous. "+newLine
                + " A sa mort,"+newLine+"il doit éliminer un joueur "+newLine+"en utilisant sa dernière balle.";
    }

}
