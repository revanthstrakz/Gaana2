package com.payu.custombrowser.upiintent;

public enum Payment {
    TEZ("Tez", "com.google.android.apps.nbu.paisa.user", true, 19);
    
    private boolean isWebFlowSupported;
    private int minSdk;
    private String packageName;
    private String paymentName;

    private Payment(String str, String str2, boolean z, int i) {
        this.paymentName = str;
        this.packageName = str2;
        this.isWebFlowSupported = z;
        this.minSdk = i;
    }

    public String getPaymentName() {
        return this.paymentName;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public boolean isWebFlowSupported() {
        return this.isWebFlowSupported;
    }

    public void setWebFlowSupported(boolean z) {
        this.isWebFlowSupported = z;
    }

    public int getMinSdk() {
        return this.minSdk;
    }
}
