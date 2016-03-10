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
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class PaymentTrackerApp
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println("==============================================");
        System.out.println(" Payment tracker");
        System.out.println("");
        System.out.println(" '<CURRENCY> <AMOUNT>' - add payment");
        System.out.println(" 'quit'                - for quit");
        System.out.println(" 'status'              - for status");
        System.out.println("==============================================");
        // init core service
        IPaymentTrackerService paymentTrackerService = new PaymentTrackerService();

        // output generator and thread
        IOutputGenerator outputGenerator = new OutputGenerator(new PaymentFormatter(), paymentTrackerService);
        new OutputGeneratorThread(outputGenerator, System.out).start();

        // input processor
        InputProcessor r = new InputProcessor(paymentTrackerService, new PaymentParser(), outputGenerator);

        // handle file
        if (args.length==1) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(args[0]);
                r.process(fis);
                System.out.println("File "+args[0] + " loaded.");
            } finally {
                if (fis!=null) { fis.close(); }
            }

        }

        // handle user input
        r.process(System.in);
    }
}
