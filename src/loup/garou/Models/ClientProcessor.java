/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou.Models;

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

    public ClientProcessor(Socket pSock, Trucable callback) throws IOException {
        this.sock = pSock;
        this.callback = callback;
        this.out = new ObjectOutputStream(sock.getOutputStream());
        this.in = new ObjectInputStream(sock.getInputStream());
    }

    //Le traitement lancé dans un thread séparé
    public void run() {
        System.err.println("Lancement du traitement de la connexion cliente");

        boolean closeConnexion = false;
        //tant que la connexion est active, on traite les demandes
        while (!sock.isClosed()) {

            try {

                //Ici, nous n'utilisons pas les mêmes objets que précédemment
                //Je vous expliquerai pourquoi ensuite
                
                //On attend la demande du client            
                Message response = (Message) in.readObject();
                //On traite la demande du client en fonction de la commande envoyée
                String toSend = "";
                Serveur ServeurInstance = Serveur.getInstance();

                switch (response.getEtape()) {
                    case "NAME":
                        name = (String) response.getContent();
                        if(ServeurInstance.verifNomJoueur(name).equals("0")){
                            ServeurInstance.setClientInList(this);
                            callback.etat(response.getContent());
                            toSend = "Bienvenue " + (String)response.getContent();
                        } else {
                            toSend = "nom utilisé";
                        }
                        break;
                    case "VOTE":
                        VoteJoueur.add((Joueur)response.getContent());
                        Master leMaster = Master.getInstance();
                        List<Joueur> TabJoueurLive = leMaster.getTabJoueurLive();
                        int nbJoueur = TabJoueurLive.size();
                        int nbVoteJoueur = VoteJoueur.size();
                        System.out.println("nbJoueur : " + nbJoueur + " || nbVoteJoueur : " + nbVoteJoueur);
                        if(nbVoteJoueur==nbJoueur){
                            HashMap<Joueur, Integer> listeVoteJoueur = new HashMap<>();
                            for (Joueur unJoueur : VoteJoueur) {
                                int nbVote=0;
                                if(!listeVoteJoueur.containsKey(unJoueur)){
                                    for (Joueur unJoueur2 : VoteJoueur) {
                                        if(unJoueur.getNom().equals(unJoueur2.getNom())){
                                            nbVote = nbVote+1;
                                        }
                                    }
                                    listeVoteJoueur.put(unJoueur, nbVote);
                                }
                            }
                            int joueurNbVote = 0;
                            Joueur joueurMort = new Joueur();
                            for (Joueur i : listeVoteJoueur.keySet()) {
                                if(joueurNbVote<listeVoteJoueur.get(i)){
                                    joueurNbVote = listeVoteJoueur.get(i);
                                    joueurMort = i;
                                    System.out.println("i : " + i);
                                }
                            }
                            System.out.println("joueurMort : " + joueurMort);
                            joueurMort.setTourMort(MasterGame.getTour());
                            ServeurInstance.sendMessageToAllClient("OkNbVote");
                        }
                        toSend = "Vote recue";
                        break;
                    case "HOUR":
                        toSend = "";
                        break;
                    case "CLOSE":
                        toSend = "Communication terminée";
                        closeConnexion = true;
                        break;
                    case "DEFAULT":
                        toSend = "Commande inconnu !";
                        break;
                }
                Message unMsg = new Message();
                unMsg.setEtape("String");
                unMsg.setContent(toSend);
                //On envoie la réponse au client
                write(unMsg);
                //Il FAUT IMPERATIVEMENT UTILISER flush()
                //Sinon les données ne seront pas transmises au client
                //et il attendra indéfiniment
                

                if (closeConnexion) {
                    System.err.println("COMMANDE CLOSE DETECTEE ! ");
                    out.close();
                    in.close();
                    sock.close();
                    break;
                }
            } catch (SocketException e) {
                System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
                break;
            } catch (IOException e) {
                e.printStackTrace();
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
}
