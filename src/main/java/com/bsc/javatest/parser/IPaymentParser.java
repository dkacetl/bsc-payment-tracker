package com.bsc.javatest.parser;

import com.bsc.javatest.Payment;

/**
 * IPaymentParser enables functionality for parsing line of input
 *
 * Created by dkacetl on 9.3.16.
 */
public interface IPaymentParser {

    /**
     * Parse and validate general input to payment structre
     *
     * @param input
     * @return
     * @throws ValidationException
     */
    Payment parse(String input) throws ValidationException;
}
