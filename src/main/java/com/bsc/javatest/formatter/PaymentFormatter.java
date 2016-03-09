package com.bsc.javatest.formatter;

import com.bsc.javatest.Payment;

/**
 * Created by dkacetl on 9.3.16.
 */
public class PaymentFormatter implements IPaymentFormatter {

    /**
     * @param payment
     *
     * @return
     */
    public String format(Payment payment) {
        StringBuilder sb = new StringBuilder();
        sb.append(payment.getCurrency());
        sb.append(" ");
        sb.append(payment.getAmount());
        return sb.toString();
    }
}
