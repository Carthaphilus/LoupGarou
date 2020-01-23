/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    
    public void initGame(){
        Random r = new Random();
        List<Joueur> roleAttribuer = new ArrayList<>();

        Integer j = 0;
        Long nbLoup_Garou = Math.round(0.3333333333333333 * nbJoueur);
        for (j = 0; j < nbLoup_Garou; j++) {
            int n = r.nextInt(tabJoueur.size());
            System.out.println(n);
            Loup_Garou unLoup = new Loup_Garou();
            tabJoueur.get(n).setRole(unLoup);
            roleAttribuer.add(tabJoueur.get(n));
            tabJoueur.remove(n);
        }

//        boolean nextStep = false;
            Sorciere uneSorciere = new Sorciere();
//        while (nextStep == false) {
            int n = r.nextInt(tabJoueur.size());
            if (tabJoueur.get(n).getRole() instanceof Villageois) {
                tabJoueur.get(n).setRole(uneSorciere);
                roleAttribuer.add(tabJoueur.get(n));
                tabJoueur.remove(n);
//                nextStep = true;
            }
//        }

//        nextStep = false;
            Voyante uneVoyante = new Voyante();
//        while (nextStep == false) {
            n = r.nextInt(tabJoueur.size());
            if (tabJoueur.get(n).getRole() instanceof Villageois) {
                tabJoueur.get(n).setRole(uneVoyante);
                roleAttribuer.add(tabJoueur.get(n));
                tabJoueur.remove(n);
//                nextStep = true;
            }
//        }
        
        for (Joueur unJoueur : tabJoueur) {
            roleAttribuer.add(unJoueur);
        }

         tabJoueur = roleAttribuer;
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
