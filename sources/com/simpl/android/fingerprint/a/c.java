package com.simpl.android.fingerprint.a;

import android.support.annotation.NonNull;
import com.simpl.android.fingerprint.SimplFingerprintListener;
import java.util.HashMap;

public final class c implements o {
    public final void addFlags(String... strArr) {
    }

    public final void generateFingerprint(@NonNull SimplFingerprintListener simplFingerprintListener) {
        if (simplFingerprintListener != null) {
            simplFingerprintListener.fingerprintData("something went wrong");
        }
    }

    public final void generateFingerprint(@NonNull SimplFingerprintListener simplFingerprintListener, @NonNull HashMap<String, String> hashMap) {
        if (simplFingerprintListener != null) {
            simplFingerprintListener.fingerprintData("something went wrong");
        }
    }

    public final void generateTransactionFingerprint(@NonNull SimplFingerprintListener simplFingerprintListener) {
        if (simplFingerprintListener != null) {
            simplFingerprintListener.fingerprintData("something went wrong");
        }
    }
}
