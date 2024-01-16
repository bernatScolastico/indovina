package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class App {
    public static void main(String[] args) {
        try {
            
            Socket socket = new Socket("localhost", 3000);
            DataOutputStream outVersoServer = new DataOutputStream(socket.getOutputStream());
            BufferedReader inDalServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String rigaRitornata;
            do {

                System.out.println("Inserisci un numero...");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                // converto in int
                Integer num = Integer.parseInt(in.readLine());
                // invio il numero al server
                outVersoServer.writeBytes(num + "\n");
                // aspetto la risposta dal server
                rigaRitornata = inDalServer.readLine();
                switch (rigaRitornata) {
                    case "1":
                        System.out.println("MI DISPIACE! il tuo numero inserito è basso...");
                        break;
                    case "2":
                        System.out.println("MI DISPIACE! il tuo numero inserito è alto...");
                        break;
                    case "3":
                        System.out.println("MI DISPIACE! il tuo numero inserito è giusto");
                        break;
                    default:
                        break;
                }
            } while (!rigaRitornata.equals("3"));
            // aspetto il numero di tentativi dal server
            rigaRitornata = inDalServer.readLine();
            // stampo
            System.out.println("\n\nFine " + rigaRitornata);
            // chiudo il socket (chiuso dal client!)
            socket.close();

        } catch (Exception e) {
            System.out.println("Errore");
        }
    }
}
