package com.bsc.javatest.output;

import java.io.PrintStream;

/**
 * Thread for periodical output of OutputGenerator to printStream
 * Thread is daemon as a default.
 *
 * Created by dkacetl on 9.3.16.
 */
public class OutputGeneratorThread extends Thread {

    private static final int SLEEP = 60000;

    private IOutputGenerator outputGenerator;

    private PrintStream printStream;

    /**
     * Create thread for generation output
     *
     * @param outputGenerator output generator
     * @param printStream stream for output (System.out for example)
     */
    public OutputGeneratorThread(IOutputGenerator outputGenerator, PrintStream printStream) {
        this.outputGenerator = outputGenerator;
        this.printStream = printStream;
        this.setDaemon(true); // set daemon for auto-exit
    }

    @Override
    public void run() {
        while (true) {
            outputGenerator.generate(printStream);
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread was interrupted. Generating output has been stopped.",e);
            }
        }
    }
}
