package main;

import presentation.ParseInput;

/**
 * Main is the main entity of the application
 * @author Mihali Vlad
 * @since 1.0
 * @version 1.0
 */
public class Main {
    /**
     * this is a public static param which keep track of writing pdf files
     * @since 1.0
     */
    public static int INDEX_FILE=0;

    /**
     * starts the application
     * @param args are arguments of the application
     */
    public static void main (String[] args)
    {
        new ParseInput(args[0]);
    }
}
