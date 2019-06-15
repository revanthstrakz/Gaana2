package com.payu.custombrowser.bean;

import com.payu.custombrowser.PayUCustomBrowserCallback;
import com.payu.custombrowser.e;

public enum b {
    SINGLETON;
    
    private PayUCustomBrowserCallback payuCustomBrowserCallback;
    private e samsungPayWrapper;

    public PayUCustomBrowserCallback getPayuCustomBrowserCallback() {
        return this.payuCustomBrowserCallback;
    }

    public void setPayuCustomBrowserCallback(PayUCustomBrowserCallback payUCustomBrowserCallback) {
        this.payuCustomBrowserCallback = payUCustomBrowserCallback;
    }

    public e getSamsungPayWrapper() {
        return this.samsungPayWrapper;
    }

    public void setSamsungPayWrapper(e eVar) {
        this.samsungPayWrapper = eVar;
    }
}
