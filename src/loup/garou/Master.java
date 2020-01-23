/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FullCodex
 */
public class Master {

    private List<Joueur> tabJoueur = new ArrayList<>();
    private List<Joueur> tabJoueurMort = new ArrayList<>();
    private Integer nbJoueur;
    private Integer tour;

    public Master() {
        this.nbJoueur = 0;
        this.tour=0;
    }

    public void ajouterJoueur(Object unNom) {
        Joueur Joueur = new Joueur();
        Joueur.setNom((String) unNom);
        tabJoueur.add(Joueur);
        this.nbJoueur++;

    }

    public List<Joueur> getTabJoueur() {
        return tabJoueur;
    }

    public void setTabJoueur(List<Joueur> tabJoueur) {
        this.tabJoueur = tabJoueur;
    }

    public List<Joueur> getTabJoueurMort() {
        return tabJoueurMort;
    }

    public void setTabJoueurMort(List<Joueur> tabJoueurMort) {
        this.tabJoueurMort = tabJoueurMort;
    }

    public Integer getNbJoueur() {
        return nbJoueur;
    }

    public void setNbJoueur(Integer nbJoueur) {
        this.nbJoueur = nbJoueur;
    }

    public Integer getTour() {
        return tour;
    }

    public void setTour(Integer tour) {
        this.tour = tour;
    }
    

}
