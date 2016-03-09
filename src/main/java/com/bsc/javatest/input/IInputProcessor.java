package com.bsc.javatest.input;

import com.bsc.javatest.Payment;
import com.bsc.javatest.parser.IPaymentParser;
import com.bsc.javatest.parser.ValidationException;
import com.bsc.javatest.tracker.IPaymentTrackerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * IInputProcessor is able to read everything from input stream and process it.
 *
 * Created by dkacetl on 9.3.16.
 */
public interface IInputProcessor {

    /**
     * Read input stream
     * @param inputStream
     * @throws IOException
     */
    void process(InputStream inputStream) throws IOException;
}
