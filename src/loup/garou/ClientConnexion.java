/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientConnexion implements Runnable {

    private Socket connexion = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private Trucable callback = null;

    //Notre liste de commandes. Le serveur nous répondra différemment selon la commande utilisée.
    private String[] listCommands = {"NAME"};
    private static int count = 0;
    private static Integer firstMessage = 1;
    private String name = "Client-";

    public ClientConnexion(String host, int port, Trucable callback) {
        name += ++count;
        this.callback = callback;
        try {
            connexion = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        //nous n'allons faire que 10 demandes par thread...
        for (int i = 0; i < 10; i++) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {

                out = new ObjectOutputStream(connexion.getOutputStream());
                in = new ObjectInputStream(connexion.getInputStream());
                //On envoie la commande au serveur

                Message commande = getCommand();
                out.writeObject(commande);
                //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
                out.flush();

                System.out.println("Commande " + commande + " envoyée au serveur");

                //On attend la réponse
                String response = (String)in.readObject();
                System.out.println("\t * " + name + " : Réponse reçue " + response);

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String msg = "CLOSE";
        try {
            out.writeObject(msg);
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Méthode qui permet d'envoyer des commandeS de façon aléatoire
    private Message getCommand() {
        Message msg = new Message();
        if(firstMessage.equals(1)){
            msg.setEtape(listCommands[0]);
            msg.setContent(callback.getinputName());
            this.firstMessage=0;
        }
        return msg;
    }

    //Méthode pour lire les réponses du serveur
//    private String read() throws IOException {
//        String response = "";
//        int stream;<question> <  / question
//                > byte[] b = new byte[4096];
//        stream = in.read(b);
//        response = new String(b, 0, stream);
//        return response;
//    }
}
