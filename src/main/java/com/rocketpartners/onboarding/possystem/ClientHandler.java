package com.rocketpartners.onboarding.possystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Handles a single client connection.
 */
public class ClientHandler extends Thread {

    private Socket clientSocket;
    private BufferedReader in;

    /**
     * Creates a new client handler.
     *
     * @param clientSocket the client socket
     */
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (Exception e) {
            System.err.println("Error creating input stream: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Received: " + line);
            }
        } catch (Exception e) {
            System.err.println("Error reading from client: " + e.getMessage());
        } finally {
            try {
                in.close();
                clientSocket.close();
            } catch (Exception e) {
                System.err.println("Error closing client connection: " + e.getMessage());
            }
        }
    }
}
