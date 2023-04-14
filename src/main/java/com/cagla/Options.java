package com.cagla;

import org.kohsuke.args4j.Option;

public class Options {
    @Option(name = "-numThread", usage = "Input number of threads")
    public int numOfThread;

    @Option(name = "-numBins", usage = "Input number of bins")
    public int numOfBins;
}
