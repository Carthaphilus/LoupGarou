package loup.garou.Models;

import java.io.Serializable;

public class Loup_Garou extends Role implements Serializable{

    public Loup_Garou() {
        String newLine = System.getProperty("line.separator");
        this.nom = "Loup-Garou";
        this.description = "Son objectif est d'éliminer tous les innocents"+newLine
                + "(ceux qui ne sont pas Loups-Garous). "+newLine
                + "Chaque nuit,"+newLine
                +"il se réunit avec ses compères Loups"+newLine
                +"pour décider d'une victime à éliminer.";
    }
}
