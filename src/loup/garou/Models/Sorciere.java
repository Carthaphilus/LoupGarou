package loup.garou.Models;

import java.io.Serializable;

public class Sorciere extends Villageois implements Serializable{

    private boolean potionVie;
    private boolean potionMort;

    public Sorciere() {
        String newLine = System.getProperty("line.separator");
        this.nom = "Sorciere";
        this.description = "Son objectif est d'éliminer tous les Loups-Garous."+newLine
                + "Elle dispose de deux potions : "+newLine
                + "- une potion de vie pour sauver la victime des Loups"+newLine
                + "- une potion de mort pour assassiner quelqu'un";
        this.potionMort = true;
        this.potionVie = true;
    }

    public void usePotionVie() {
        this.potionVie = false;
    }

    public void usePotionMort() {
        this.potionMort = false;
    }

    public boolean getPotionVie() {
        return this.potionVie;
    }

    public boolean getPotionMort() {
        return this.potionMort;
    }
}
