package loup.garou.Models;

import java.io.Serializable;

public class Villageois extends Role implements Serializable{

    public Villageois() {
        String newLine = System.getProperty("line.separator");
        this.nom = "Villageois";
        this.description = "Son objectif est d'éliminer tous les Loups-Garous. "+newLine
                + "Il ne dispose d'aucun pouvoir particulier :"+newLine
                + "uniquement sa perspicacité et sa force de persuasion."+newLine
                + "Toutefois,"+newLine+"tous les joueurs voient son rôle d'innocent.";
    }
}
