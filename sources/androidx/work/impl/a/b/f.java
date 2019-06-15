package androidx.work.impl.a.b;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class f extends c<Boolean> {
    private static final String b = androidx.work.f.a("StorageNotLowTracker");

    public f(Context context) {
        super(context);
    }

    /* renamed from: a */
    public Boolean c() {
        Intent registerReceiver = this.a.registerReceiver(null, b());
        if (registerReceiver == null || registerReceiver.getAction() == null) {
            return Boolean.valueOf(true);
        }
        String action = registerReceiver.getAction();
        boolean z = true;
        int hashCode = action.hashCode();
        if (hashCode != -1181163412) {
            if (hashCode == -730838620 && action.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                z = false;
            }
        } else if (action.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
            z = true;
        }
        switch (z) {
            case false:
                return Boolean.valueOf(true);
            case true:
                return Boolean.valueOf(false);
            default:
                return null;
        }
    }

    public IntentFilter b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
        return intentFilter;
    }

    public void a(Context context, @NonNull Intent intent) {
        if (intent.getAction() != null) {
            androidx.work.f.a().b(b, String.format("Received %s", new Object[]{intent.getAction()}), new Throwable[0]);
            String action = intent.getAction();
            int i = -1;
            int hashCode = action.hashCode();
            if (hashCode != -1181163412) {
                if (hashCode == -730838620 && action.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                    i = 0;
                }
            } else if (action.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
                i = 1;
            }
            switch (i) {
                case 0:
                    a((Object) Boolean.valueOf(true));
                    break;
                case 1:
                    a((Object) Boolean.valueOf(false));
                    break;
            }
        }
    }
}
