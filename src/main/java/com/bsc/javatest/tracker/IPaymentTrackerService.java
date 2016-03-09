package com.bsc.javatest.tracker;

import com.bsc.javatest.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Thread-safe service for collecting all payments and provides
 *
 * Created by dkacetl on 9.3.16.
 */
public interface IPaymentTrackerService {

    /**
     * @param payment
     */
    void pay(Payment payment);

    /**
     * Return current staus of payments.
     *
     * @return
     */
    List<Payment> getPayments();
}
