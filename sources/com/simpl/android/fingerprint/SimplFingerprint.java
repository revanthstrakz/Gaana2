package com.simpl.android.fingerprint;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.simpl.android.fingerprint.a.o;
import com.simpl.android.fingerprint.a.p;
import java.util.HashMap;

public class SimplFingerprint implements o {
    private static final String TAG = p.class.getSimpleName();
    private static SimplFingerprint instance;

    private SimplFingerprint() {
    }

    public static SimplFingerprint getInstance() {
        if (instance != null) {
            return instance;
        }
        Log.e(TAG, "Please call init() before accessing the instance.\n++++++++++++++++++++++\nMake sure you have called SimplFingerprint.init(context, phoneNo, emailId)in your Application class.\n++++++++++++++++++++++\\n\" +");
        return new SimplFingerprint();
    }

    public static void init(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        p.a(context, str, str2);
        instance = new SimplFingerprint();
    }

    public void addFlags(String... strArr) {
        p.a().addFlags(strArr);
    }

    public void generateFingerprint(@NonNull SimplFingerprintListener simplFingerprintListener) {
        p.a().generateFingerprint(simplFingerprintListener);
    }

    public void generateFingerprint(@NonNull SimplFingerprintListener simplFingerprintListener, @NonNull HashMap<String, String> hashMap) {
        p.a().generateFingerprint(simplFingerprintListener, hashMap);
    }

    public void generateTransactionFingerprint(@NonNull SimplFingerprintListener simplFingerprintListener) {
        p.a().generateTransactionFingerprint(simplFingerprintListener);
    }
}
