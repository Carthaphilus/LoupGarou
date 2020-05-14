/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author FullCodex
 */
public class Master implements Serializable {

    private List<Joueur> tabJoueur = new ArrayList<>();
    private List<Joueur> tabJoueurMort = new ArrayList<>();
    private Integer nbJoueur;
    private Integer tour;
    static Master MasterInstance;

    private Master() {
        this.nbJoueur = 0;
        this.tour = 0;
    }

    public static Master getInstance() {
        if (MasterInstance == null) {
            return MasterInstance = new Master();
        } else {
            return MasterInstance;
        }
    }

    public void resetMaster() {
        this.nbJoueur = 0;
        this.tour = 0;
        tabJoueur = new ArrayList<>();
        tabJoueurMort = new ArrayList<>();
    }

    public void ajouterJoueur(Object unNom) {
        Joueur Joueur = new Joueur();
        Joueur.setNom((String) unNom);
        tabJoueur.add(Joueur);
        this.nbJoueur++;
    }

    public void initGame() {
        Random r = new Random();
        List<Joueur> roleAttribuer = new ArrayList<>();

        Integer j = 0;
        Long nbLoup_Garou = Math.round(0.3333333333333333 * nbJoueur);
        for (j = 0; j < nbLoup_Garou; j++) {
            int n = r.nextInt(tabJoueur.size());
            Loup_Garou unLoup = new Loup_Garou();
            tabJoueur.get(n).setRole(unLoup);
            roleAttribuer.add(tabJoueur.get(n));
            tabJoueur.remove(n);
        }

        Sorciere uneSorciere = new Sorciere();
        int n = r.nextInt(tabJoueur.size());
        if (tabJoueur.get(n).getRole() instanceof Villageois) {
            tabJoueur.get(n).setRole(uneSorciere);
            roleAttribuer.add(tabJoueur.get(n));
            tabJoueur.remove(n);
        }

        Voyante uneVoyante = new Voyante();
        n = r.nextInt(tabJoueur.size());
        if (tabJoueur.get(n).getRole() instanceof Villageois) {
            tabJoueur.get(n).setRole(uneVoyante);
            roleAttribuer.add(tabJoueur.get(n));
            tabJoueur.remove(n);
        }

        if (nbJoueur >= 8) {
            Chasseur unChasseur = new Chasseur();
            n = r.nextInt(tabJoueur.size());
            if (tabJoueur.get(n).getRole() instanceof Villageois) {
                tabJoueur.get(n).setRole(unChasseur);
                roleAttribuer.add(tabJoueur.get(n));
                tabJoueur.remove(n);
            }
        }
        
        if (nbJoueur >= 4) {
            Cupidon unCupidon = new Cupidon();
            n = r.nextInt(tabJoueur.size());
            if (tabJoueur.get(n).getRole() instanceof Villageois) {
                tabJoueur.get(n).setRole(unCupidon);
                roleAttribuer.add(tabJoueur.get(n));
                tabJoueur.remove(n);
            }
        }

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

    public List<Joueur> getTabJoueurLive() {
        List<Joueur> JoueurEnVie = new ArrayList();

        for (Joueur unJoueur : tabJoueur) {
            if (unJoueur.getTourMort() == 0) {
                JoueurEnVie.add(unJoueur);
            }
        }
        return JoueurEnVie;
    }

    public Boolean roleExiste(String role) {
        Boolean ok = false;
        List<Joueur> JoueurLive = this.getTabJoueurLive();
        for (Joueur unJoueur : JoueurLive) {
            if (unJoueur.getRole().getNom().equals(role)) {
                ok = true;
            }
        }
        return ok;
    }
    
    public Boolean amoureuxDefined(){
        Boolean ok = false;
        Integer nbAmoureux = 0;
        List<Joueur> JoueurLive = this.getTabJoueurLive();
        for (Joueur unJoueur : JoueurLive) {
            if (unJoueur.getAmoureux() == true) {
                nbAmoureux++;
            }
        }
        
        if(nbAmoureux == 2){
            ok = true;
        }
        return ok;
    }

}
