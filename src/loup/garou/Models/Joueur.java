package loup.garou.Models;

import java.io.Serializable;
import java.util.Comparator;

public class Joueur implements Serializable {

    private String nom;
    private Role role;
    private int tourMort;
    private boolean amoureux;
    private boolean chef;

    public Joueur() {
        this.role = new Villageois();
        this.amoureux = false;
        this.chef = false;
    }

    public boolean getAmoureux() {
        return amoureux;
    }

    public void setAmoureux() {
        if (this.amoureux == false) {
            this.amoureux = true;
        } else {
            this.amoureux = false;
        }
    }

    public boolean getChef() {
        return chef;
    }

    public void setChef() {
        if (this.chef == false) {
            this.chef = true;
        }else{
            this.chef = false;
        }
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
        return "Joueur{" + "nom=" + nom + " tour=" + tourMort + "}";
    }

    public static Comparator<Joueur> TriNameJoueur = new Comparator<Joueur>() {
	public int compare(Joueur s1, Joueur s2) {
	   String StudentName1 = s1.nom.toUpperCase();
	   String StudentName2 = s2.nom.toUpperCase();

	   //ascending order
	   return StudentName1.compareTo(StudentName2);

	   //descending order
	   //return StudentName2.compareTo(StudentName1);
    }};

    @Override
    public int hashCode() {
        return this.nom.hashCode();
    }
    
    
}
