package com.rocketpartners.onboarding.posvirtualjournal;

/**
 * The main entry point for the point of sale virtual journal.
 */
public class Driver {

    /**
     * The default port to listen on if none is supplied in the command line args.
     */
    public static final int DEFAULT_PORT = 12345;

    /**
     * Starts the point of sale virtual journal.
     *
     * @param args the command line arguments; if present, the first argument is the port to listen on
     */
    public static void main(String[] args) {
        int port;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        } else {
            port = DEFAULT_PORT;
        }

        POSVirtualJournal journal = new POSVirtualJournal(port);
        journal.run();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down POS virtual journal");
            journal.stop();
        }));
    }
}
