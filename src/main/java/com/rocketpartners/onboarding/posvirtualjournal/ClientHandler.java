package com.rocketpartners.onboarding.posvirtualjournal;

import lombok.NonNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Handles a single client connection.
 */
public class ClientHandler extends Thread {

    private final Socket clientSocket;
    private BufferedReader in;
    private boolean running;

    /**
     * Creates a new client handler.
     *
     * @param clientSocket the client socket
     */
    public ClientHandler(@NonNull Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Client connection established");
            running = true;
        } catch (Exception e) {
            System.err.println("Error creating input stream: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    JournalPrinter.print(line);
                }
            } catch (Exception e) {
                System.err.println("Error reading from client: " + e.getMessage());
            }
        }
    }

    /**
     * Shuts down the client connection.
     */
    public void shutDown() {
        try {
            in.close();
            clientSocket.close();
            System.out.println("Client connection closed");
        } catch (Exception e) {
            System.err.println("Error closing client connection: " + e.getMessage());
        } finally {
            running = false;
        }
    }
}
