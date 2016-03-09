package com.bsc.javatest.formatter;

import com.bsc.javatest.Payment;

/**
 * Created by dkacetl on 9.3.16.
 */
public interface IPaymentFormatter {

    /**
     * Format payment for output
     *
     * @param payment
     *
     * @return formatted payment
     */
    String format(Payment payment);

}
