package loup.garou.Models;

import java.io.Serializable;

public class Villageois extends Role implements Serializable{

    public Villageois() {
        this.nom = "Villageois";
        this.description = "Son objectif est d'�liminer tous les Loups-Garous. "
                + "Il ne dispose d'aucun pouvoir particulier : uniquement sa perspicacit� et sa force de persuasion. "
                + "Toutefois, tous les joueurs voient son r�le d'innocent.";
    }
}
