package com.rocketpartners.onboarding.posvirtualjournal;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * A virtual journal for a point of sale system.
 */
public class POSVirtualJournal implements Runnable {

    private final List<ClientHandler> clients;
    private ServerSocket serverSocket;
    private boolean running;

    /**
     * Creates a new virtual journal.
     *
     * @param port the port to listen on
     */
    public POSVirtualJournal(int port) {
        clients = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("POS virtual journal started on port " + port);
            running = true;
        } catch (Exception e) {
            System.err.println("Error starting POS virtual journal on port " + port + ": " + e.getMessage());
        }
    }

    /**
     * Runs the virtual journal.
     */
    @Override
    public void run() {
        while (running) {
            try {
                System.out.println("Waiting for connection...");

                Socket clientSocket = serverSocket.accept();

                ClientHandler client = new ClientHandler(clientSocket);
                clients.add(client);
                client.start();

                System.out.println("Connection accepted to socket: " + clientSocket);
            } catch (Exception e) {
                System.err.println("Error accepting connection: " + e.getMessage());
            }
        }

        System.out.println("POS virtual journal stopped");
    }

    public void stop() {
        running = false;
        try {
            serverSocket.close();
        } catch (Exception e) {
            System.err.println("Error stopping POS virtual journal: " + e.getMessage());
        }
        clients.forEach(ClientHandler::shutDown);
    }
}