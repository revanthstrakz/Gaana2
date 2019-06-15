package com.simpl.android.zeroClickSdk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.simpl.android.zeroClickSdk.internal.g;
import com.simpl.android.zeroClickSdk.internal.i;

public class Simpl implements g {
    public static final String TAG = "Simpl";
    private static Simpl instance;

    private Simpl() {
    }

    public static Simpl getInstance() {
        if (instance != null) {
            return instance;
        }
        Log.e(TAG, "Please call init() before accessing the instance.\n++++++++++++++++++++++\nMake sure you have called Simpl.init(context) or Simpl.init(context, client_id) in your Application class.\n++++++++++++++++++++++\\n\" +");
        return new Simpl();
    }

    public static void init(@NonNull Context context) {
        i.a(context);
        instance = new Simpl();
    }

    public static void init(@NonNull Context context, @NonNull String str) {
        i.a(context, str);
        instance = new Simpl();
    }

    public void addFlags(String... strArr) {
        i.a().addFlags(strArr);
    }

    public void generateZeroClickToken(@NonNull SimplUser simplUser, @NonNull SimplZeroClickTokenListener simplZeroClickTokenListener) {
        i.a().generateZeroClickToken(simplUser, simplZeroClickTokenListener);
    }

    public void generateZeroClickToken(@NonNull SimplZeroClickTokenListener simplZeroClickTokenListener) {
        i.a().generateZeroClickToken(simplZeroClickTokenListener);
    }

    public boolean isSimplApproved() {
        return i.a().isSimplApproved();
    }

    public SimplUserApprovalRequest isUserApproved(@NonNull SimplUser simplUser) {
        return i.a().isUserApproved(simplUser);
    }

    public SimplPaymentUrlRequest openRedirectionURL(@NonNull Context context, @NonNull String str) {
        return i.a().openRedirectionURL(context, str);
    }

    public SimplPaymentUrlRequest openRedirectionURL(@NonNull Context context, @NonNull String str, @NonNull SimplUser simplUser) {
        return i.a().openRedirectionURL(context, str, simplUser);
    }

    public void runInSandboxMode() {
        i.a().runInSandboxMode();
    }

    public void runInStagingMode() {
        i.a().runInStagingMode();
    }

    public void setMerchantId(String str) {
        i.a().setMerchantId(str);
    }
}
