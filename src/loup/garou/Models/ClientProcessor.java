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
import java.util.logging.Level;
import java.util.logging.Logger;
import loup.garou.Trucable;

public class ClientProcessor implements Runnable{

   private Socket sock;
   private ObjectOutputStream out = null;
   private ObjectInputStream in = null;
   private Trucable callback;
   
   public ClientProcessor(Socket pSock, Trucable callback){
      sock = pSock;
      this.callback=callback;
   }
   
   //Le traitement lancé dans un thread séparé
   public void run(){
      System.err.println("Lancement du traitement de la connexion cliente");

      boolean closeConnexion = false;
      //tant que la connexion est active, on traite les demandes
      while(!sock.isClosed()){
         
         try {
            
            //Ici, nous n'utilisons pas les mêmes objets que précédemment
            //Je vous expliquerai pourquoi ensuite
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            
            //On attend la demande du client            
            Message response = (Message)in.readObject();
            //On traite la demande du client en fonction de la commande envoyée
            String toSend = "";
            
            switch(response.getEtape()){
               case "NAME":
                  callback.etat(response.getContent());
                  toSend = "OK";
                  break;
               case "DATE":
                  toSend = "";
                  break;
               case "HOUR":
                  toSend = "";
                  break;
               case "CLOSE":
                  toSend = "Communication terminée"; 
                  closeConnexion = true;
                  break;
               case "DEFAULT" : 
                  toSend = "Commande inconnu !";                     
                  break;
            }
            
            //On envoie la réponse au client
            out.writeObject(toSend);
            //Il FAUT IMPERATIVEMENT UTILISER flush()
            //Sinon les données ne seront pas transmises au client
            //et il attendra indéfiniment
            out.flush();
            
            if(closeConnexion){
               System.err.println("COMMANDE CLOSE DETECTEE ! ");
               out.close();
               in.close();
               sock.close();
               break;
            }
         }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            break;
         } catch (IOException e) {
            e.printStackTrace();
         } catch (ClassNotFoundException ex) {         
              Logger.getLogger(ClientProcessor.class.getName()).log(Level.SEVERE, null, ex);
          }         
      }
   }
   
   //La méthode que nous utilisons pour lire les réponses
   private String read() throws IOException{      
      String response = "";
      int stream;
      byte[] b = new byte[4096];
      stream = in.read(b);
      response = new String(b, 0, stream);
      return response;
   }
   
}
