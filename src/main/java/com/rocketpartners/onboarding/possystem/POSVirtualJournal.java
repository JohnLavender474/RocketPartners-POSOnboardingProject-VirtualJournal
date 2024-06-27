package com.rocketpartners.onboarding.possystem;

import java.net.ServerSocket;

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
                serverSocket.accept();
                System.out.println("Connection accepted!");
            } catch (Exception e) {
                System.err.println("Error accepting connection: " + e.getMessage());
            }
        }
    }
}