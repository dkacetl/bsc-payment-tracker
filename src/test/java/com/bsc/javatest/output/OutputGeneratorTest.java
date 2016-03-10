package com.bsc.javatest.output;

import com.bsc.javatest.Payment;
import com.bsc.javatest.formatter.IPaymentFormatter;
import com.bsc.javatest.input.IInputProcessor;
import com.bsc.javatest.input.InputProcessor;
import com.bsc.javatest.parser.IPaymentParser;
import com.bsc.javatest.tracker.IPaymentTrackerService;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test if payments are displayed right way
 *
 * Created by dkacetl on 10.3.16.
 */
public class OutputGeneratorTest {

    /**
     * Test if OutputProcessor process input correctly
     *
     * @throws IOException
     */
    @Test
    public void generate() throws IOException {

        // prepare mock of paymentTrackerService
        List<Payment> paymentList = new ArrayList<Payment>();
        paymentList.add(new Payment("ABC", 200));
        paymentList.add(new Payment("ABC", 200));
        paymentList.add(new Payment("ABC", 200));
        paymentList.add(new Payment("XYZ", 100));
        paymentList.add(new Payment("XYZ", -100));

        IPaymentTrackerService paymentTrackerServiceMock = mock(IPaymentTrackerService.class);
        when(paymentTrackerServiceMock.getPayments()).thenReturn(paymentList);

        // formatter mock
        IPaymentFormatter paymentFormatterMock = mock(IPaymentFormatter.class);
        when(paymentFormatterMock.format(new Payment("ABC", 200))).thenReturn("ABC 200");
        when(paymentFormatterMock.format(new Payment("XYZ", -100))).thenReturn("XYZ -100");
        when(paymentFormatterMock.format(new Payment("XYZ", 100))).thenReturn("XYZ 100");

        // output buffer for output generator
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputBuffer);

        // execute generate method
        IOutputGenerator outputGenerator = new OutputGenerator(paymentFormatterMock,paymentTrackerServiceMock);
        outputGenerator.generate(printStream);
        printStream.flush();

        // generate output
        String output =  new String(outputBuffer.toByteArray());
        String expectedOutput = "ABC 200\n" +
                "ABC 200\n" +
                "ABC 200\n" +
                "XYZ 100\n" +
                "XYZ -100\n";

        Assert.assertEquals(expectedOutput,output);
    }
}
