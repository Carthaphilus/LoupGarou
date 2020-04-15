package loup.garou.Models;

import java.io.Serializable;

public class Loup_Garou extends Role implements Serializable{

    public Loup_Garou() {
        this.nom = "Loup-Garou";
        this.description = "Son objectif est d'�liminer tous les innocents (ceux qui ne sont pas Loups-Garous). "
                + "Chaque nuit, il se r�unit avec ses comp�res Loups pour d�cider d'une victime � �liminer...";
    }
}
