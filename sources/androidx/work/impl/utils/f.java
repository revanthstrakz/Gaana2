package androidx.work.impl.utils;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.WorkerParameters.a;
import androidx.work.impl.h;

@RestrictTo({Scope.LIBRARY_GROUP})
public class f implements Runnable {
    private h a;
    private String b;
    private a c;

    public f(h hVar, String str, a aVar) {
        this.a = hVar;
        this.b = str;
        this.c = aVar;
    }

    public void run() {
        this.a.g().a(this.b, this.c);
    }
}
