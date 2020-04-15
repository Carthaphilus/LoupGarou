package loup.garou.Models;

import java.io.Serializable;

public class Role implements Serializable{

    protected String nom;
    protected String description;

    public Role() {

    }

    public String getNom() {
        return this.nom;
    }

    public String getDescription() {
        return this.description;
    }
}
