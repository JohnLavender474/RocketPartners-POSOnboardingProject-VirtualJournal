package com.rocketpartners.onboarding.posvirtualjournal;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * A virtual journal for a point of sale system.
 */
public class POSVirtualJournal {

    private ServerSocket serverSocket;

    /**
     * Creates a new virtual journal.
     *
     * @param port the port to listen on
     */
    public POSVirtualJournal(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
        } catch (Exception e) {
            System.err.println("Error starting server on port " + port + ": " + e.getMessage());
        }
    }

    /**
     * Starts the virtual journal.
     */
    public void start() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                System.out.println("Waiting for connection...");
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
                System.out.println("Connection accepted!");
            } catch (Exception e) {
                System.err.println("Error accepting connection: " + e.getMessage());
            }
        }
    }
}