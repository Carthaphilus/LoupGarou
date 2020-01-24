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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientConnexion implements Runnable {

    private Thread t;
    private Socket connexion = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private Trucable callback = null;

    //Notre liste de commandes. Le serveur nous répondra différemment selon la commande utilisée.
    private String[] listCommands = {"NAME", "DEFAULT"};
    private static int count = 0;
    private Integer firstMessage;
    private String name = "Client-";

    public ClientConnexion(String host, int port, Trucable callback) {
        count = count +1;
        this.name = this.name+count;
        this.firstMessage = 1;
        this.callback = callback;
        try {
            connexion = new Socket(host, port);
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
                if (commande != null) {
                    out.writeObject(commande);
                    //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
                    out.flush();
                    System.out.println(this.name + ": commande " + commande + " envoyée au serveur");
                }

                //On attend la réponse
                String response = (String) in.readObject();
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
        if (this.firstMessage.equals(1)) {
            msg.setEtape(listCommands[0]);
            msg.setContent(callback.getinputName());
            this.firstMessage = 0;
            return msg;
        }
        msg = null;
        return msg;
    }

    public static void newClientConnexion(String host, int port, Trucable callback) {
        ClientConnexion connexion = new ClientConnexion(host, port, callback);
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
