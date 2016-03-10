package com.bsc.javatest.tracker;

import com.bsc.javatest.Payment;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test of paymentTrackerService
 */
public class PaymentTrackerTest {

    @Test
    public void paymentTrackerService() {
        // prepare mock of paymentTrackerService
        List<Payment> paymentList = new ArrayList<Payment>();
        paymentList.add(new Payment("ABC", 200));
        paymentList.add(new Payment("ABC", 200));
        paymentList.add(new Payment("ABC", 200));
        paymentList.add(new Payment("XYZ", 100));
        paymentList.add(new Payment("XYZ", -100));

        IPaymentTrackerService paymentTrackerService = new PaymentTrackerService();

        for (Payment p:paymentList) {
            paymentTrackerService.pay(p);
        }
        List<Payment> result = paymentTrackerService.getPayments();

        Assert.assertEquals("PaymentTrackerService should return only one record",1,result.size());
        Assert.assertEquals("PaymentTrackerService should return 'ABC 600'",new Payment("ABC",600), result.get(0));
    }

}
