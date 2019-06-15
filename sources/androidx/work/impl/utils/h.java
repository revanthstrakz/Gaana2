package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.f;
import java.util.HashMap;
import java.util.WeakHashMap;

@RestrictTo({Scope.LIBRARY_GROUP})
public class h {
    private static final String a = f.a("WakeLocks");
    private static final WeakHashMap<WakeLock, String> b = new WeakHashMap();

    public static WakeLock a(@NonNull Context context, @NonNull String str) {
        PowerManager powerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WorkManager: ");
        stringBuilder.append(str);
        str = stringBuilder.toString();
        WakeLock newWakeLock = powerManager.newWakeLock(1, str);
        synchronized (b) {
            b.put(newWakeLock, str);
        }
        return newWakeLock;
    }

    public static void a() {
        HashMap hashMap = new HashMap();
        synchronized (b) {
            hashMap.putAll(b);
        }
        for (WakeLock wakeLock : hashMap.keySet()) {
            if (wakeLock != null && wakeLock.isHeld()) {
                f.a().d(a, String.format("WakeLock held for %s", new Object[]{hashMap.get(wakeLock)}), new Throwable[0]);
            }
        }
    }
}
