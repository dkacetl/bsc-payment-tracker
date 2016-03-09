package com.bsc.javatest.parser;

import com.bsc.javatest.Payment;
import com.bsc.javatest.parser.ValidationException;

/**
 * Responsible for parsing input (string line)
 *
 * Created by dkacetl on 9.3.16.
 */
public class PaymentParser implements IPaymentParser {


    public Payment parse(String input) throws ValidationException{
        if (input==null) {
            throw new ValidationException("input parameter cannot be null");
        }
        String[] parts = input.trim().split(" ");
        if (parts.length!=2) {
            throw new ValidationException("input parameter is in invalid format.Found '"+input+"'. Expected '<CURRENCY> <AMOUNT>'.",input);
        }

        String currency=parts[0];
        String amountStr=parts[1];

        try {
            int amount = Integer.valueOf(amountStr);
            return new Payment(currency,amount);
        } catch (NumberFormatException e) {
            throw new ValidationException("input parameter is in invalid format.Found '"+input+"'. Expected '<CURRENCY> <AMOUNT>'.",e,input);
        }
    }

}
