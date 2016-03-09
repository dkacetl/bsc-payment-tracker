package com.bsc.javatest;

import com.bsc.javatest.formatter.PaymentFormatter;
import com.bsc.javatest.input.InputProcessor;
import com.bsc.javatest.output.IOutputGenerator;
import com.bsc.javatest.output.OutputGenerator;
import com.bsc.javatest.output.OutputGeneratorThread;
import com.bsc.javatest.parser.PaymentParser;
import com.bsc.javatest.tracker.IPaymentTrackerService;
import com.bsc.javatest.tracker.PaymentTrackerService;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class PaymentTrackerApp
{
    public static void main( String[] args ) throws IOException
    {
        IPaymentTrackerService paymentTrackerService = new PaymentTrackerService();
        IOutputGenerator outputGenerator = new OutputGenerator(new PaymentFormatter(), paymentTrackerService);
        new OutputGeneratorThread(outputGenerator,System.out).start();

        InputProcessor r = new InputProcessor(paymentTrackerService, new PaymentParser());

        String data = "USD 1000\n" +
                        "HKD 100\n" +
                        "USD -100\n" +
                        "RMB 2000\n" +
                        "RMB -2000\n" +
                        "HKD 200";

        ByteArrayInputStream is = new ByteArrayInputStream(data.getBytes()); // used default charset
        r.process(is);

        r.process(System.in);

    }
}
