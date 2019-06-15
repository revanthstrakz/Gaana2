package com.payu.custombrowser.upiintent;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;

public class b {

    /* renamed from: com.payu.custombrowser.upiintent.b$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Payment.values().length];

        static {
            try {
                a[Payment.TEZ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public boolean a(Context context, Payment payment) {
        if (AnonymousClass1.a[payment.ordinal()] != 1 || VERSION.SDK_INT < payment.getMinSdk()) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(payment.getPackageName(), 0);
            return true;
        } catch (NameNotFoundException unused) {
            return false;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String a(String str, String str2) {
        for (String split : str.split("&")) {
            String[] split2 = split.split("=");
            if (split2.length >= 2 && str2.equalsIgnoreCase(split2[0])) {
                return split2[1];
            }
        }
        return "";
    }

    public Payment a(String str) {
        if (str != null) {
            str = a(str, "bankcode");
            for (Payment payment : Payment.values()) {
                if (payment.getPaymentName().equalsIgnoreCase(str)) {
                    return payment;
                }
            }
        }
        return null;
    }

    public String a(String str, String str2, String str3, String str4, String str5) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("upi://pay?");
        stringBuilder.append("pa");
        stringBuilder.append("=");
        stringBuilder.append(str);
        stringBuilder.append("&");
        stringBuilder.append("pn");
        stringBuilder.append("=");
        stringBuilder.append(str2);
        stringBuilder.append("&");
        stringBuilder.append("am");
        stringBuilder.append("=");
        stringBuilder.append(str3);
        stringBuilder.append("&");
        stringBuilder.append("tr");
        stringBuilder.append("=");
        stringBuilder.append(str5);
        stringBuilder.append("&");
        stringBuilder.append("tid");
        stringBuilder.append("=");
        stringBuilder.append(str4);
        return stringBuilder.toString();
    }
}
