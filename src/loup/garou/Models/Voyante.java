package loup.garou.Models;

import java.io.Serializable;

public class Voyante extends Villageois implements Serializable{

    public Voyante() {
        this.nom = "Voyante";
        this.description = "Son objectif est d'�liminer tous les Loups-Garous. "
                + "Chaque nuit, elle peut espionner un joueur et d�couvrir sa v�ritable identit�...";
    }

}
