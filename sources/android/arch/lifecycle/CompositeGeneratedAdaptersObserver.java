package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {
    private final c[] a;

    CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.a = cVarArr;
    }

    public void a(e eVar, Event event) {
        j jVar = new j();
        int i = 0;
        for (c a : this.a) {
            a.a(eVar, event, false, jVar);
        }
        c[] cVarArr = this.a;
        int length = cVarArr.length;
        while (i < length) {
            cVarArr[i].a(eVar, event, true, jVar);
            i++;
        }
    }
}
