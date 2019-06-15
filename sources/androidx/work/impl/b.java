package androidx.work.impl;

import android.arch.lifecycle.k;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.h;
import androidx.work.h.a;
import androidx.work.h.a.c;

@RestrictTo({Scope.LIBRARY_GROUP})
public class b implements h {
    private final k<a> c = new k();
    private final androidx.work.impl.utils.futures.b<c> d = androidx.work.impl.utils.futures.b.d();

    public b() {
        a(h.b);
    }

    public void a(@NonNull a aVar) {
        this.c.postValue(aVar);
        if (aVar instanceof c) {
            this.d.a((c) aVar);
        } else if (aVar instanceof a.a) {
            this.d.a(((a.a) aVar).a());
        }
    }
}
