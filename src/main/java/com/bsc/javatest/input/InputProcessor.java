package com.bsc.javatest.input;

import com.bsc.javatest.Payment;
import com.bsc.javatest.output.IOutputGenerator;
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
 * Class read input stream as text (default encoding) by lines, parses the input and send it into
 * IPaymentTrackerService
 *
 * class can be used for files as well for console input "System.in"
 *
 * Created by dkacetl on 9.3.16.
 */
public class InputProcessor implements IInputProcessor {

    private static final Logger LOGGER = Logger.getLogger(InputProcessor.class.getCanonicalName());

    private IPaymentTrackerService paymentTrackerService;
    private IPaymentParser paymentParser;
    private IOutputGenerator outputGenerator;

    /**
     * Constructor injection
     *
     * @param paymentTrackerService - tracker for payments
     * @param paymentParser - user defined payment parser
     * @param outputGenerator - for command 'status', show the tracked payments
     */
    public InputProcessor(IPaymentTrackerService paymentTrackerService, IPaymentParser paymentParser, IOutputGenerator outputGenerator) {
        this.paymentTrackerService = paymentTrackerService;
        this.paymentParser = paymentParser;
        this.outputGenerator = outputGenerator;
    }

    /**
     * Read input stream until data are available or "quit" is on input,
     * parse them and put them into PaymentTrackerService
     *
     * @param inputStream source of raw data
     * @throws IOException something went wrong
     */
    public void process(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String s;
        while ((s = br.readLine()) != null && s.length() != 0) {
            try {
                if (s.trim().toLowerCase().equals("quit")) {
                    return;
                }
                if (s.trim().toLowerCase().equals("status")) {
                    outputGenerator.generate(System.out);
                } else {
                    Payment payment = paymentParser.parse(s);
                    paymentTrackerService.pay(payment);
                }
            } catch (ValidationException e) {
                LOGGER.log(Level.SEVERE,"Error parsing input '"+s+"'. Detail:"+e.getMessage());
            }
        }
    }
}
