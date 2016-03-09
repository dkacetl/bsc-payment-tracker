package com.bsc.javatest.formatter;

import com.bsc.javatest.Payment;
import com.bsc.javatest.parser.IPaymentParser;
import com.bsc.javatest.parser.PaymentParser;
import com.bsc.javatest.parser.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dkacetl on 9.3.16.
 */
public class PaymentFormatterTest {

    private IPaymentFormatter testedPaymentFormatter;

    @Before
    public void init() {
        this.testedPaymentFormatter = new PaymentFormatter();
    }

    @Test
    public void paymentFormatter() {
        Payment payment1 = new Payment("ABC",1234);
        Payment payment2 = new Payment("ERT",-1234);
        Payment payment3 = new Payment("EET",0);

        Assert.assertEquals("Error in formatting.", "ABC 1234", this.testedPaymentFormatter.format(payment1));
        Assert.assertEquals("Error in formatting.", "ERT -1234", this.testedPaymentFormatter.format(payment2));
        Assert.assertEquals("Error in formatting.", "EET 0", this.testedPaymentFormatter.format(payment3));
    }
}
