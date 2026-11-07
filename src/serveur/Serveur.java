
package serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import operation.Operation;

public class Serveur {

    public static void main(String[] args) {

        try {
            // Etape 1 : Creation du serveur
            ServerSocket serverSocket = new ServerSocket(1234);

            System.out.println("Serveur en attente de connexion...");

            // Etape 2 : Attente de connexion du client
            Socket socket = serverSocket.accept();

            System.out.println("Un client est connecte");

            // Etape 3 : Creation des flux d'objets
            ObjectInputStream in = new ObjectInputStream(
                    socket.getInputStream()
            );

            ObjectOutputStream out = new ObjectOutputStream(
                    socket.getOutputStream()
            );
            out.flush();

            // Etape 4 : Reception de l'objet Operation
            Operation operation = (Operation) in.readObject();

            System.out.println("Objet Operation reçu");

            // Etape 5 : Calcul
            int resultat = operation.calculer();

            System.out.println("Calcul effectue : " + resultat);

            // Etape 6 : Envoi du resultat au client
            out.writeObject(resultat);
            out.flush();

            System.out.println("Resultat envoye au client");

            // Etape 7 : Fermeture
            in.close();
            out.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }
    }
}
