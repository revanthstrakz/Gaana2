package com.simpl.android.fingerprint.a;

import android.support.annotation.NonNull;
import com.simpl.android.fingerprint.SimplFingerprintListener;
import java.util.HashMap;

public interface o {
    void addFlags(String... strArr);

    void generateFingerprint(@NonNull SimplFingerprintListener simplFingerprintListener);

    void generateFingerprint(@NonNull SimplFingerprintListener simplFingerprintListener, @NonNull HashMap<String, String> hashMap);

    void generateTransactionFingerprint(@NonNull SimplFingerprintListener simplFingerprintListener);
}
