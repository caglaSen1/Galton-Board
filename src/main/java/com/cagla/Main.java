package com.cagla;


import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        CmdLineParser parser = new CmdLineParser(options);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            System.exit(1);
        }

        int numOfThread = options.numOfThread;
        int numOfBins = options.numOfBins;

        GaltonBoard galtonBoard = new GaltonBoard(numOfBins, numOfThread);

        galtonBoard.run();

        System.out.println(galtonBoard);
    }
}
