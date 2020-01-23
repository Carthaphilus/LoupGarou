package loup.garou;

public class Sorciere extends Villageois {

    private boolean potionVie;
    private boolean potionMort;

    public Sorciere() {
        this.nom = "Sorciere";
        this.description = "Son objectif est d'�liminer tous les Loups-Garous."
                + " Elle dispose de deux potions : "
                + "une potion de vie pour sauver la victime des Loups, et une potion de mort pour assassiner quelqu'un.";
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
