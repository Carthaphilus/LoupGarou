/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou.Models;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import loup.garou.Trucable;
import loup.garou.Vues.MasterGame;

public class ClientProcessor implements Runnable {

    private Socket sock;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Trucable callback;
    private String name;
    private static List<Joueur> VoteJoueur = new ArrayList<>();
    private static boolean closeConnexion;

    public ClientProcessor(Socket pSock, Trucable callback) throws IOException {
        closeConnexion = false;
        this.sock = pSock;
        this.callback = callback;
        this.out = new ObjectOutputStream(sock.getOutputStream());
        this.in = new ObjectInputStream(sock.getInputStream());
    }

    //Le traitement lancé dans un thread séparé
    public void run() {
        System.err.println("Lancement du traitement de la connexion cliente");

        //tant que la connexion est active, on traite les demandes
        while (closeSocket() == false) {

            try {

                //Ici, nous n'utilisons pas les mêmes objets que précédemment
                //Je vous expliquerai pourquoi ensuite
                //On attend la demande du client
                Message response = (Message) in.readObject();

                //On traite la demande du client en fonction de la commande envoyée
                String toSend = "";
                Serveur ServeurInstance = Serveur.getInstance();
                Master leMaster = Master.getInstance();
                List<Joueur> TabJoueurLive = leMaster.getTabJoueurLive();
                int nbJoueur = TabJoueurLive.size();
                int nbVoteJoueur = 0;

                switch (response.getEtape()) {
                    case "NAME":
                        name = (String) response.getContent();
                        if (ServeurInstance.verifNomJoueur(name).equals("0")) {
                            ServeurInstance.setClientInList(this);
                            callback.etat(response.getContent());
                            toSend = "BIENVENUE";
                        } else {
                            toSend = "REINITIALISER";
                        }
                        break;
                    case "VoteVillage":
                        Joueur Expediteur = (Joueur) response.getEnvoyeur();
                        Joueur VoteChef = null;
//                        System.out.println(Expediteur.getNom() + Expediteur.getChef());
                        
                        for(Joueur MasterJoueur : leMaster.getTabJoueurLive()){
                            if(MasterJoueur.getNom().equals(Expediteur.getNom())){
                                Expediteur = MasterJoueur;
                            }
                        }
                        
//                        System.out.println(Expediteur.getNom() + Expediteur.getChef());
                        
                        if (Expediteur.getChef() == false) {
                            VoteJoueur.add((Joueur) response.getContent());
                        }else{
                            VoteJoueur.add((Joueur) response.getContent());
                            VoteJoueur.add((Joueur) response.getContent());
                            VoteChef = (Joueur) response.getContent();
                        }
                        nbVoteJoueur = VoteJoueur.size();
//                        System.out.println("nbJoueur : " + nbJoueur + " || nbVoteJoueur : " + nbVoteJoueur);
                        if (nbVoteJoueur == (nbJoueur+1)) {
                            HashMap<Joueur, Integer> listeVoteJoueur = new HashMap<>();
                            for (Joueur unJoueur : VoteJoueur) {
                                int nbVote = 0;
                                if (!listeVoteJoueur.containsKey(unJoueur)) {
                                    for (Joueur unJoueur2 : VoteJoueur) {
                                        if (unJoueur.getNom().equals(unJoueur2.getNom())) {
                                            nbVote = nbVote + 1;
                                        }
                                    }
                                    listeVoteJoueur.put(unJoueur, nbVote);
                                }
                            }
                            int joueurNbVote = 0;
                            Joueur joueurMort = null;
                            for (Joueur i : listeVoteJoueur.keySet()) {
                                if (joueurNbVote < listeVoteJoueur.get(i)) {
                                    joueurNbVote = listeVoteJoueur.get(i);
                                    joueurMort = i;
//                                    System.out.println("i : " + i);
                                }else if (joueurNbVote == listeVoteJoueur.get(i)) {
                                    joueurNbVote = listeVoteJoueur.get(i);
                                    joueurMort = VoteChef;
                                    System.out.println("i : " + i);
                                }
                            }

                            for (Joueur joueurEnvie : leMaster.getTabJoueurLive()) {
                                if (joueurEnvie.getNom().equals(joueurMort.getNom())) {
//                                    System.out.println("joueurMort : " + joueurEnvie);
                                    joueurEnvie.setTourMort(MasterGame.getTour());
                                    leMaster.getTabJoueurMort().add(joueurEnvie);

                                    if (joueurEnvie.getAmoureux() == true) {
                                        leMaster.killAmoureux();
                                    }
                                    System.out.println("joueurMort : " + joueurEnvie);
//                                    System.out.println("TabJoueurMortClientPros : " + leMaster.getTabJoueurMort());
                                }
                            }
                            ServeurInstance.sendMessageToAllClient("OkNbVote");
                            MasterGame InstanceMG = MasterGame.getMasterGameInstance();
                            InstanceMG.setEnabledEtapeSuivante();
                        }
                        toSend = "Vote recue";
                        break;
                    case "VoteChef":
                        VoteJoueur.add((Joueur) response.getContent());
                        nbVoteJoueur = VoteJoueur.size();
//                        System.out.println("nbJoueur : " + nbJoueur + " || nbVoteJoueur : " + nbVoteJoueur);
                        if (nbVoteJoueur == nbJoueur) {
                            HashMap<Joueur, Integer> listeVoteJoueur = new HashMap<>();
                            for (Joueur unJoueur : VoteJoueur) {
                                int nbVote = 0;
                                if (!listeVoteJoueur.containsKey(unJoueur)) {
                                    for (Joueur unJoueur2 : VoteJoueur) {
                                        if (unJoueur.getNom().equals(unJoueur2.getNom())) {
                                            nbVote = nbVote + 1;
                                        }
                                    }
                                    listeVoteJoueur.put(unJoueur, nbVote);
                                }
                            }
                            int joueurNbVote = 0;
                            Joueur joueurChef = null;
                            for (Joueur i : listeVoteJoueur.keySet()) {
                                if (joueurNbVote < listeVoteJoueur.get(i)) {
                                    joueurNbVote = listeVoteJoueur.get(i);
                                    joueurChef = i;
//                                    System.out.println("i : " + i);
                                }
                            }

                            for (Joueur joueurEnvie : leMaster.getTabJoueurLive()) {
                                if (joueurEnvie.getNom().equals(joueurChef.getNom())) {
                                    System.out.println(joueurEnvie.getChef());
                                    joueurEnvie.setChef();
                                    System.out.println(joueurEnvie.getChef());
                                }
                            }
                            ServeurInstance.sendMessageToAllClient("OkNbVote");
                            MasterGame InstanceMG = MasterGame.getMasterGameInstance();
                            InstanceMG.setEnabledEtapeSuivante();
                        }
                        toSend = "Vote recue";
                        break;
                    case "HOUR":
                        toSend = "";
                        break;
                    case "DEFAULT":
                        toSend = "Commande inconnu !";
                        break;
                }

                if (closeConnexion == false) {
                    Message unMsg = new Message();
                    unMsg.setEtape("String");
                    unMsg.setContent(toSend);
                    //On envoie la réponse au client
                    write(unMsg);
                    //Il FAUT IMPERATIVEMENT UTILISER flush()
                    //Sinon les données ne seront pas transmises au client
                    //et il attendra indéfiniment 
                }/* else {
                        System.err.println("COMMANDE CLOSE DETECTEE ! ");
                        out.close();
                        in.close();
                        sock.close();
                        break;
                    }*/

            } catch (SocketException e) {
                System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
                break;
            } catch (IOException e) {
                //e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void write(Message obj) {
        try {
            out.writeObject(obj);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //La méthode que nous utilisons pour lire les réponses
    private String read() throws IOException {
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = in.read(b);
        response = new String(b, 0, stream);
        return response;
    }

    public String getName() {
        return name;
    }

    public static void setCloseConnexion() {
        closeConnexion = true;
    }

    public boolean closeSocket() {
        if (closeConnexion == true) {
            try {
                //System.err.println("COMMANDE CLOSE DETECTEE ! : "+name);
                out.close();
                in.close();
                sock.close();
                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static List<Joueur> resetVoteJoueur() {
        return VoteJoueur = new ArrayList<>();
    }
}
