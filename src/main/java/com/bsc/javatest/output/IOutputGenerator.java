package com.bsc.javatest.output;

import java.io.PrintStream;

/**
 * IOutputGenerator is able to print "some staff" to printstream
 *
 * Created by dkacetl on 9.3.16.
 */
public interface IOutputGenerator {

    /**
     * Generate output to print stream
     * @param output destination
     */
    void generate(PrintStream output);
}
