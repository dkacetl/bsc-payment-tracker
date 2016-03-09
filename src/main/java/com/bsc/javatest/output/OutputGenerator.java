package com.bsc.javatest.output;

import com.bsc.javatest.Payment;
import com.bsc.javatest.formatter.IPaymentFormatter;
import com.bsc.javatest.tracker.IPaymentTrackerService;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by dkacetl on 9.3.16.
 */
public class OutputGenerator implements IOutputGenerator {

    private IPaymentFormatter paymentFormatter;

    private IPaymentTrackerService paymentTrackerService;

    /**
     * Constructor injection
     *
     * @param paymentFormatter
     * @param paymentTrackerService
     */
    public OutputGenerator(IPaymentFormatter paymentFormatter, IPaymentTrackerService paymentTrackerService) {
        this.paymentFormatter = paymentFormatter;
        this.paymentTrackerService = paymentTrackerService;
    }

    /**
     * Generate status payments in paymentTrackerService to output
     *
     * @return
     */
    public void generate(PrintStream output) {
        List<Payment> payments = this.paymentTrackerService.getPayments();
        for (Payment payment : payments) {
            output.append(paymentFormatter.format(payment));
            output.append('\n');
        }
    }
}
