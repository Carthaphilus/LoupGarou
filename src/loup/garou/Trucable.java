/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou;

import java.util.List;
import loup.garou.Models.Joueur;

/**
 *
 * @author FullCodex
 */
public interface Trucable {
    void etat(Object m);
    String getinputName();
    void setJoueur(Joueur unJoueur);
    void VoteJoueur(List<Joueur> ListeJoueur);
    void reiniChooseServer(String unMsg);
}
