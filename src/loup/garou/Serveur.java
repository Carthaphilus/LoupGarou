/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loup.garou;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author FullCodex
 */
public class Serveur implements Runnable {

    private ServerSocket serveurSocket;
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Trucable callback;

    public Serveur(Trucable machin) {
        this.callback = machin;
        try {
            serveurSocket = new ServerSocket(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Serveur(String ip, Trucable machin) {
        this.callback = machin;
        try {
            clientSocket = new Socket(ip, 5000);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Envoie(Object obj) {
        Thread envoie = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        out.writeObject(obj);
                        out.flush();
                    } catch (IOException e) {

                    }

                    //msg = sc.nextLine();
                    //out.println(msg);
                    //out.flush();
                }
            }
        });
        envoie.start();
    }

    public static InetAddress getIp() {
        try {
            InetAddress IP = InetAddress.getLocalHost();
            return IP;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Recevoir() {
        Thread recevoir = new Thread(new Runnable() {
            String msg;
            Object tab;
            ObjectInputStream e;

            @Override
            public void run() {
                try {
                    //tant que le client est connecté
                    Object obj = in.readObject();
                    out.close();
                    callback.etat(obj);
//                    clientSocket.close();
//                    serveurSocket.close();
                    //return obj;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {

                }
            }
        });
        recevoir.start();

    }

    @Override
    public void run() {
        try {
            String message = "Bienvenue sur le serveur";
            clientSocket = serveurSocket.accept();
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            Envoie(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
