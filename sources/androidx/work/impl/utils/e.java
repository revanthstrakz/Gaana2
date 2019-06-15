package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class e {
    private Context a;
    private SharedPreferences b;

    public e(@NonNull Context context) {
        this.a = context;
    }

    public boolean a() {
        return b().getBoolean("reschedule_needed", false);
    }

    public void a(boolean z) {
        b().edit().putBoolean("reschedule_needed", z).apply();
    }

    private SharedPreferences b() {
        SharedPreferences sharedPreferences;
        synchronized (e.class) {
            if (this.b == null) {
                this.b = this.a.getSharedPreferences("androidx.work.util.preferences", 0);
            }
            sharedPreferences = this.b;
        }
        return sharedPreferences;
    }
}
