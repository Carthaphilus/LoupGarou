package loup.garou.Models;

import java.io.Serializable;

public class Joueur implements Serializable{

    private String nom;
    private Role role;
    private int tourMort;

    public Joueur() {
        this.role = new Villageois();
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String unNom) {
        this.nom = unNom;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role unRole) {
        this.role = unRole;
    }

    public Integer getTourMort() {
        return this.tourMort;
    }

    public void setTourMort(int unTour) {
        this.tourMort = unTour;
    }

    @Override
    public String toString() {
        return "Joueur{" + "nom=" + nom + '}';
    }
}
