package com.bsc.javatest;

/**
 * Created by dkacetl on 9.3.16.
 */
public class Payment {

    private String currency;

    private int amount;

    public Payment(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Payment payment = (Payment) o;

        if (amount != payment.amount) {
            return false;
        }
        return currency.equals(payment.currency);

    }

    @Override
    public int hashCode() {
        int result = currency.hashCode();
        result = 31 * result + amount;
        return result;
    }
}
