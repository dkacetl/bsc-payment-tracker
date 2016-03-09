package com.bsc.javatest.tracker;

import com.bsc.javatest.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Thread-safe service for collecting all payments
 *
 * Created by dkacetl on 9.3.16.
 */
public class PaymentTrackerService implements IPaymentTrackerService {

    private HashMap<String, Integer> trackedMap = new HashMap<String, Integer>();

    /**
     * @param payment
     */
    public synchronized void pay(Payment payment) {
        if (!trackedMap.containsKey(payment.getCurrency())) {
            trackedMap.put(payment.getCurrency(), payment.getAmount());
        } else {
            Integer sum = trackedMap.get(payment.getCurrency()) + payment.getAmount();
            if (sum==0) {
                trackedMap.remove(payment.getCurrency());
            } else {
                trackedMap.put(payment.getCurrency(), sum);
            }
        }
    }

    /**
     * Return current status of payments.
     *
     * @return
     */
    public synchronized List<Payment> getPayments() {
        List<Payment> result = new ArrayList<Payment>(trackedMap.size());
        for (Entry<String, Integer> entry : trackedMap.entrySet()) {
            Payment item = new Payment(entry.getKey(), entry.getValue());
            result.add(item);
        }
        return result;
    }
}
