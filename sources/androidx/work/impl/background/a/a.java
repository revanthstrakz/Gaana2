package androidx.work.impl.background.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import androidx.work.WorkInfo.State;
import androidx.work.f;
import androidx.work.impl.a.c;
import androidx.work.impl.b.j;
import androidx.work.impl.d;
import androidx.work.impl.h;
import java.util.ArrayList;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class a implements androidx.work.impl.a, c, d {
    private static final String a = f.a("GreedyScheduler");
    private h b;
    private androidx.work.impl.a.d c;
    private List<j> d = new ArrayList();
    private boolean e;
    private final Object f;

    public a(Context context, h hVar) {
        this.b = hVar;
        this.c = new androidx.work.impl.a.d(context, this);
        this.f = new Object();
    }

    public void a(j... jVarArr) {
        a();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (j jVar : jVarArr) {
            if (jVar.b == State.ENQUEUED && !jVar.a() && jVar.g == 0 && !jVar.b()) {
                if (!jVar.d()) {
                    f.a().b(a, String.format("Starting work for %s", new Object[]{jVar.a}), new Throwable[0]);
                    this.b.a(jVar.a);
                } else if (VERSION.SDK_INT < 24 || !jVar.j.i()) {
                    arrayList.add(jVar);
                    arrayList2.add(jVar.a);
                }
            }
        }
        synchronized (this.f) {
            if (!arrayList.isEmpty()) {
                f.a().b(a, String.format("Starting tracking for [%s]", new Object[]{TextUtils.join(",", arrayList2)}), new Throwable[0]);
                this.d.addAll(arrayList);
                this.c.a(this.d);
            }
        }
    }

    public void a(@NonNull String str) {
        a();
        f.a().b(a, String.format("Cancelling work ID %s", new Object[]{str}), new Throwable[0]);
        this.b.b(str);
    }

    public void a(@NonNull List<String> list) {
        for (String str : list) {
            f.a().b(a, String.format("Constraints met: Scheduling work ID %s", new Object[]{str}), new Throwable[0]);
            this.b.a(str);
        }
    }

    public void b(@NonNull List<String> list) {
        for (String str : list) {
            f.a().b(a, String.format("Constraints not met: Cancelling work ID %s", new Object[]{str}), new Throwable[0]);
            this.b.b(str);
        }
    }

    public void a(@NonNull String str, boolean z) {
        b(str);
    }

    private void b(@NonNull String str) {
        synchronized (this.f) {
            int size = this.d.size();
            for (int i = 0; i < size; i++) {
                if (((j) this.d.get(i)).a.equals(str)) {
                    f.a().b(a, String.format("Stopping tracking for %s", new Object[]{str}), new Throwable[0]);
                    this.d.remove(i);
                    this.c.a(this.d);
                    break;
                }
            }
        }
    }

    private void a() {
        if (!this.e) {
            this.b.g().a((androidx.work.impl.a) this);
            this.e = true;
        }
    }
}
