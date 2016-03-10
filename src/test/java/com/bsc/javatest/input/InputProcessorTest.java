package com.bsc.javatest.input;

import com.bsc.javatest.Payment;
import com.bsc.javatest.output.IOutputGenerator;
import com.bsc.javatest.parser.IPaymentParser;
import com.bsc.javatest.tracker.IPaymentTrackerService;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * InputProcessor test - if inserted data will be parsed correctly and putted into tracker
 *
 * Created by dkacetl on 10.3.16.
 */
public class InputProcessorTest {

    /**
     * Test if InputProcessor process input correctly
     *
     * @throws IOException
     */
    @Test
    public void process() throws IOException {
        IPaymentTrackerService paymentTrackerServiceMock = mock(IPaymentTrackerService.class);
        IOutputGenerator outputGeneratorMock = mock(IOutputGenerator.class);

        IPaymentParser paymentParserMock = mock(IPaymentParser.class);
        when(paymentParserMock.parse("ABC 200")).thenReturn(new Payment("ABC", 200));
        when(paymentParserMock.parse("XYZ 100")).thenReturn(new Payment("XYZ", 100));
        when(paymentParserMock.parse("XYZ -100")).thenReturn(new Payment("XYZ", -100));

        IInputProcessor inputProcessor = new InputProcessor(paymentTrackerServiceMock, paymentParserMock, outputGeneratorMock);

        // input is 6 x ABC 200, XYZ 100 and XYZ -100
        byte[] input = ("ABC 200\n" +
                "ABC 200\n" +
                "ABC 200\n" +
                "ABC 200\n" +
                "ABC 200\n" +
                "ABC 200\n" +
                "XYZ 100\n" +
                "XYZ -100\n").getBytes();

        inputProcessor.process(new ByteArrayInputStream(input));

        // check if 6 x ABC 200, XYZ 100 and XYZ -100 was processed
        verify(paymentTrackerServiceMock, times(6)).pay(new Payment("ABC", 200));
        verify(paymentTrackerServiceMock, times(1)).pay(new Payment("XYZ", 100));
        verify(paymentTrackerServiceMock, times(1)).pay(new Payment("XYZ", -100));
    }
}
