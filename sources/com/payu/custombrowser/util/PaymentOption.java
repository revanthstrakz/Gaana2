package com.payu.custombrowser.util;

public enum PaymentOption {
    SAMSUNGPAY("SAMPAY"),
    NB("NB"),
    CC("CC"),
    DC("DC");
    
    private String paymentName;

    private PaymentOption(String str) {
        this.paymentName = str;
    }

    public String getPaymentName() {
        return this.paymentName;
    }
}
