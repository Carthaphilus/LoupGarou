/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou.Models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import loup.garou.Trucable;

public class ClientConnexion implements Runnable {

    private Thread t;
    private boolean run = true;
    private Socket connexion = null;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Trucable callback = null;

    //Notre liste de commandes. Le serveur nous répondra différemment selon la commande utilisée.
    private String[] listCommands = {"NAME", "DEFAULT"};
    private static int count;
    private Integer firstMessage;
    private String name;

    public ClientConnexion(String name, String host, int port, Trucable callback) {
        //ClientConnexion.count = ClientConnexion.count +1;

        this.name = name;
        this.firstMessage = 1;
        this.callback = callback;
        try {
            connexion = new Socket(host, port);
            this.out = new ObjectOutputStream(connexion.getOutputStream());
            this.in = new ObjectInputStream(connexion.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        t = new Thread(this);
        t.start();
    }

    public void run() {

        //nous n'allons faire que 10 demandes par thread...
        while (run==true) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                
                //On envoie la commande au serveur
                Message commande = getCommand();
                if (commande != null) {
                    write(commande);
                    System.out.println(this.name + ": commande " + commande + " envoyée au serveur");
                }

                //On attend la réponse
                Message response = (Message) in.readObject();

                if ("String".equals(response.getEtape())) {
                    String chaine = (String)response.getContent();
                    if("REINITIALISER".equals(chaine)){
                        callback.reiniChooseServer(chaine);
                        System.out.println("\t * " + name + " : Réponse reçue " + chaine);
                    } else if("CLOSE".equals(chaine)){
                        System.out.println("Fermeture du client");
                        close();
                        callback.closeClient();
                    } else {
                        System.out.println("\t * " + name + " : Réponse reçue " + chaine);
                    }
                } else if ("Joueur".equals(response.getEtape())) {
                    Joueur unJoueur = (Joueur) response.getContent();
                    callback.setJoueur(unJoueur);
                    System.out.println("\t * " + name + " : Réponse reçue " + unJoueur.toString());
                } else if ("VoteVillage".equals(response.getEtape())){
                    List<Joueur> listeJoueur = (List<Joueur>) response.getContent();
                    String etape = response.getEtape();
                    callback.VoteJoueur(listeJoueur, etape);
                    System.out.println("\t * " + name + " : Réponse reçue la liste de joueur ");
                }else if ("VoteChef".equals(response.getEtape())){
                    List<Joueur> listeJoueur = (List<Joueur>) response.getContent();
                    String etape = response.getEtape();
                    callback.VoteJoueur(listeJoueur, etape);
                    System.out.println("\t * " + name + " : Réponse reçue la liste de joueur ");
                }

            } catch (IOException e1) {
                //e1.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Méthode qui permet d'envoyer des commandeS de façon aléatoire
    private Message getCommand() {
        Message msg = new Message();
        if (this.firstMessage.equals(1)) {
            msg.setEtape(listCommands[0]);
            msg.setContent(callback.getinputName());
            this.firstMessage = 0;
            return msg;
        }
        msg = null;
        return msg;
    }
    
    public void write(Message msg) throws IOException{
        out.writeObject(msg);
        out.flush();
    }
    
    private void close(){
        run = false;
        try {
            in.close();
            out.close();
            connexion.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*public static void newClientConnexion(String host, int port, Trucable callback) {
        ClientConnexion connexion = new ClientConnexion(host, port, callback);
    }*/
    //Méthode pour lire les réponses du serveur
//    private String read() throws IOException {
//        String response = "";
//        int stream;<question> <  / question
//                > byte[] b = new byte[4096];
//        stream = in.read(b);
//        response = new String(b, 0, stream);
//        return response;
//    }

    public String getName() {
        return name;
    }
}
