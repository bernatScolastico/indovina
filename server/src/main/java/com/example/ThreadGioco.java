package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadGioco extends Thread {
    Socket client;
    int numrand;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ThreadGioco(Socket client, int numrand) {
        this.client = client;
        this.numrand = numrand;
    }

    public void run() {
        int numrand = this.numrand;
        int num;
        int cont = 0;
        try {
            System.out.println("sono collegato al client: " + client.getInetAddress() + " suo numero è " + numrand);
            this.inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.outVersoClient = new DataOutputStream(client.getOutputStream());
            do {
                num = Integer.parseInt(inDalClient.readLine());
                String daRitornare = "";
                if (num == numrand) {
                    daRitornare = "3";
                }
                if (num > numrand) {
                    daRitornare = "2";
                }
                if (num < numrand) {
                    daRitornare = "1";
                }
                outVersoClient.writeBytes(daRitornare + "\n");
                daRitornare = client.getInetAddress() + " da indovinare : " + numrand+ " utente ha messo: " + num + " ritorno: " + daRitornare;
                System.out.println(daRitornare);
                cont++;
            } while (numrand != num);
            outVersoClient.writeBytes(cont + "\n");
            System.out.println("\nclient " + client.getInetAddress() + " ha finito con " + cont + " tentaivi");
        } catch (Exception e) {
            System.out.println("\t\terrore nella comunicazione: " + e.getMessage());
        }
    }
}
