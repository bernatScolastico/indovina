package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(3000);
            while (true) {
                Socket client = serverSocket.accept();
                int numrand = (int) (Math.random() * 9 + 1);
                ThreadGioco th = new ThreadGioco(client, numrand);
                th.start();
            }
        } catch (Exception e) {
            System.out.println("\nErrore");
        }
    }
}
