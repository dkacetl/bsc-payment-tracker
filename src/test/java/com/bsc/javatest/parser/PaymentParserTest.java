package com.bsc.javatest.parser;

import com.bsc.javatest.Payment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dkacetl on 9.3.16.
 */
public class PaymentParserTest {

    private IPaymentParser testedPaymentParser;

    @Before
    public void init() {
        this.testedPaymentParser = new PaymentParser();
    }

    @Test
    public void parserTestSuccess() {
        Payment result1 = testedPaymentParser.parse("ABC 123");
        Assert.assertEquals("Error in currency parsing.", "ABC", result1.getCurrency());
        Assert.assertEquals("Error in amount parsing.", 123, result1.getAmount());

        Payment result2 = testedPaymentParser.parse("DEF -123");
        Assert.assertEquals("Error in currency parsing.", "DEF",result2.getCurrency());
        Assert.assertEquals("Error in amount parsing.", -123, result2.getAmount());

        Payment result3 = testedPaymentParser.parse("IJK 0");
        Assert.assertEquals("Error in currency parsing.", "IJK", result3.getCurrency());
        Assert.assertEquals("Error in amount parsing.", 0,result3.getAmount());
    }

    @Test(expected = ValidationException.class)
    public void parserFailsTest1() {
        testedPaymentParser.parse("ABC 123 456");
    }

    @Test(expected = ValidationException.class)
    public void parserFailsTest2() {
        testedPaymentParser.parse("ABC");
    }

    @Test(expected = ValidationException.class)
    public void parserFailsTest3() {
        testedPaymentParser.parse(null);
    }

    @Test(expected = ValidationException.class)
    public void parserFailsTest4() {
        testedPaymentParser.parse("ABC 1.45");
    }

    @Test(expected = ValidationException.class)
    public void parserFailsTest5() {
        testedPaymentParser.parse("ABC AB");
    }
}
