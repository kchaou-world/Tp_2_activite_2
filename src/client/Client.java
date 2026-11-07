
package client;

import java.io.*;
import java.net.Socket;
import operation.Operation;

public class Client {

    public static void main(String[] args) {

        try {
            // Etape 1 : Connexion au serveur
            Socket socket = new Socket("localhost", 1234);

            System.out.println("Connecté au serveur");

            // Etape 2 : Lecture au clavier
            BufferedReader clavier = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            System.out.println("Donner le premier operande :");
            int a = Integer.parseInt(clavier.readLine());

            System.out.println("Donner l'operateur (+, -, *, /) :");
            String op = clavier.readLine();

            System.out.println("Donner le deuxieme operande :");
            int b = Integer.parseInt(clavier.readLine());

            // Verification de l'operateur
            if (op.length() != 1 ||
                    "+-*/".indexOf(op.charAt(0)) == -1) {
                System.out.println("Operateur invalide !");
                socket.close();
                return;
            }

            if (op.charAt(0) == '/' && b == 0) {
                System.out.println("Division par zero impossible !");
                socket.close();
                return;
            }

            // Etape 3 : Creation de l'objet Operation
            Operation operation = new Operation(a, b, op.charAt(0));

            // Etape 4 : Creation des flux d'objets
            ObjectOutputStream out = new ObjectOutputStream(
                    socket.getOutputStream()
            );
            out.flush();

            ObjectInputStream in = new ObjectInputStream(
                    socket.getInputStream()
            );

            // Etape 5 : Envoi de l'objet au serveur
            out.writeObject(operation);
            out.flush();

            // Etape 6 : Reception du resultat
            int resultat = (Integer) in.readObject();

            System.out.println("Resultat reçu du serveur : " + resultat);

            // Etape 7 : Fermeture
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
