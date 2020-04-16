/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou.Models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import loup.garou.Trucable;

/**
 *
 * @author FullCodex
 */
public class Serveur {

    private ServerSocket server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Trucable callback;
    private static List<ClientProcessor> ListClient;
    private boolean isRunning = true;

    public Serveur(Trucable machin) {
        ListClient = new ArrayList();
        this.callback = machin;
        try {
            server = new ServerSocket(6000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public Serveur(String ip, Trucable machin) {
//        this.callback = machin;
//        try {
//            clientSocket = new Socket(ip, 5000);
//            out = new ObjectOutputStream(clientSocket.getOutputStream());
//            in = new ObjectInputStream(clientSocket.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    //On lance notre serveur
    public void open() {

        //Toujours dans un thread à part vu qu'il est dans une boucle infinie
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (isRunning == true) {

                    try {
                        //On attend une connexion d'un client
                        Socket client = server.accept();

                        //Une fois reçue, on la traite dans un thread séparé
                        System.out.println("Connexion cliente reçue.");
                        Thread t2 = new Thread(new ClientProcessor(client, callback));
                        t2.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });

        t.start();
    }

    public void close() {
        isRunning = false;
    }
    
    public static void setClientInList(ClientProcessor unClient){
        ListClient.add(unClient);
    }
    
    public static void sendMessageToAllClient(String msg){
        
        ListClient.forEach((unClient) -> {
            unClient.write(msg);
        });

    }
    
    public static void sendRoleToAllClient(List<Joueur> lesJoueurs){
        for(Joueur unJoueur:lesJoueurs){
            for(ClientProcessor unClient:ListClient){
                if(unJoueur.getNom().equals(unClient.getName())){
                    Message unMsg = new Message();
                    unMsg.setEtape("Joueur");
                    unMsg.setContent(unJoueur);
                    unClient.write(unMsg);
                }
            }
        }
    }
}



//
//public void Envoie(Object obj) {
//        Thread envoie = new Thread(new Runnable() {
//            public void run() {
//                    
//                    try {
//                        out.writeObject(obj);
//                        out.flush();
//                    } catch (IOException e) {
//                    }
//                    //msg = sc.nextLine();
//                    //out.println(msg);
//                    //out.flush();
//            }
//        });
//        envoie.start();
//    }
//
//    public static InetAddress getIp() {
//        try {
//            InetAddress IP = InetAddress.getLocalHost();
//            return IP;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void Recevoir() {
//        Thread recevoir = new Thread(new Runnable() {
//            String msg;
//            Object tab;
//            ObjectInputStream e;
//
//            @Override
//        public void run() {
//                try {
//                    //tant que le client est connecté
//                    Object obj = in.readObject();
//                    
//                    callback.etat(obj);
//                    //in.close();
//                    //clientSocket.close();
//                    //serveurSocket.close();
//                    //return obj;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException ex) {
//
//                }
//            }
//        });
//        recevoir.start();
//
//    }
//
//    @Override
//        public void run() {
//        try {
//            String message = "Bienvenue sur le serveur";
//            clientSocket = serveurSocket.accept();
//            out = new ObjectOutputStream(clientSocket.getOutputStream());
//            in = new ObjectInputStream(clientSocket.getInputStream());
//            Recevoir();
//            Envoie(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
