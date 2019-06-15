package com.simpl.approvalsdk;

import com.simpl.android.fingerprint.SimplFingerprint;
import com.simpl.android.fingerprint.SimplFingerprintListener;
import com.simpl.android.fingerprint.commons.exception.ExceptionNotifier;
import com.simpl.android.fingerprint.commons.models.Attribute;
import com.simpl.approvalsdk.executor.Executor;
import java.util.HashMap;

public class SimplUserApprovalRequest {
    private static final String TAG = "SimplUserApprovalRequest";
    private String merchantId;
    private HashMap<String, String> merchantParams = new HashMap();
    private SimplUser simplUser;

    public SimplUserApprovalRequest(SimplUser simplUser, String str) {
        this.simplUser = simplUser;
        this.merchantId = str;
    }

    private void checkForApproval(String str, final SimplUserApprovalListenerV2 simplUserApprovalListenerV2) {
        try {
            Executor.get().execute(new com.simpl.approvalsdk.b.AnonymousClass1(str));
        } catch (Throwable th) {
            simplUserApprovalListenerV2.onError(new Throwable("User not Approved"));
            ExceptionNotifier.getSharedInstance().send(th, new Attribute("user", this.simplUser.toString()));
        }
    }

    private void generateFingerprint(SimplFingerprintListener simplFingerprintListener) {
        try {
            SimplApproval.getInstance().setSimplUser(this.simplUser);
            this.merchantParams.put("merchant_id", this.merchantId);
            SimplFingerprint.init(SimplApproval.getInstance().getCurrentApplicationContext(), this.simplUser.getPhoneNumber(), this.simplUser.getEmailAddress());
            SimplFingerprint.getInstance().addFlags(a.a.b);
            SimplFingerprint.getInstance().generateFingerprint(simplFingerprintListener, this.merchantParams);
        } catch (Throwable th) {
            simplFingerprintListener.fingerprintData("");
            ExceptionNotifier.getSharedInstance().send(th, new Attribute("execute user", this.simplUser.toString()));
        }
    }

    private void generateFingerprintAndCheckForApproval(final SimplUserApprovalListenerV2 simplUserApprovalListenerV2) {
        SimplApproval.getInstance().setGlobalSimplUserApprovalListener(simplUserApprovalListenerV2);
        generateFingerprint(new SimplFingerprintListener() {
            public final void fingerprintData(String str) {
                SimplUserApprovalRequest.this.checkForApproval(str, simplUserApprovalListenerV2);
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    public com.simpl.approvalsdk.SimplUserApprovalRequest addParam(@android.support.annotation.NonNull java.lang.String r3, @android.support.annotation.NonNull java.lang.String r4) {
        /*
        r2 = this;
        r0 = r3.hashCode();
        r1 = -44759723; // 0xfffffffffd550555 float:-1.7697078E37 double:NaN;
        if (r0 == r1) goto L_0x0028;
    L_0x0009:
        r1 = 1234304940; // 0x4991ffac float:1196021.5 double:6.098276673E-315;
        if (r0 == r1) goto L_0x001e;
    L_0x000e:
        r1 = 1951594921; // 0x7452f9a9 float:6.686072E31 double:9.64216005E-315;
        if (r0 == r1) goto L_0x0014;
    L_0x0013:
        goto L_0x0032;
    L_0x0014:
        r0 = "user_location";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0032;
    L_0x001c:
        r0 = 0;
        goto L_0x0033;
    L_0x001e:
        r0 = "order_id";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0032;
    L_0x0026:
        r0 = 1;
        goto L_0x0033;
    L_0x0028:
        r0 = "member_since";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0032;
    L_0x0030:
        r0 = 2;
        goto L_0x0033;
    L_0x0032:
        r0 = -1;
    L_0x0033:
        switch(r0) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0041;
            case 2: goto L_0x003c;
            default: goto L_0x0036;
        };
    L_0x0036:
        r0 = r2.merchantParams;
        r0.put(r3, r4);
        return r2;
    L_0x003c:
        r3 = r2.merchantParams;
        r0 = "member-since";
        goto L_0x004a;
    L_0x0041:
        r3 = r2.merchantParams;
        r0 = "order-id";
        goto L_0x004a;
    L_0x0046:
        r3 = r2.merchantParams;
        r0 = "user-location";
    L_0x004a:
        r3.put(r0, r4);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.simpl.approvalsdk.SimplUserApprovalRequest.addParam(java.lang.String, java.lang.String):com.simpl.approvalsdk.SimplUserApprovalRequest");
    }

    public void execute(SimplUserApprovalListenerV2 simplUserApprovalListenerV2) {
        generateFingerprintAndCheckForApproval(simplUserApprovalListenerV2);
    }
}
