package com.rocketpartners.onboarding.posvirtualjournal;

import lombok.NonNull;

/**
 * Prints messages to the journal.
 */
public class JournalPrinter {

    /**
     * Prints a message to the journal.
     *
     * @param message the message to print
     */
    public static void print(@NonNull String message) {
        System.out.println(message);
    }
}
