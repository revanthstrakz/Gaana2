package androidx.work.impl.utils;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import androidx.work.d;
import androidx.work.f;
import androidx.work.h;
import androidx.work.h.a.a;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.j;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.e;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class b implements Runnable {
    private static final String a = f.a("EnqueueRunnable");
    private final androidx.work.impl.f b;
    private final androidx.work.impl.b c = new androidx.work.impl.b();

    public b(@NonNull androidx.work.impl.f fVar) {
        this.b = fVar;
    }

    public void run() {
        try {
            if (this.b.j()) {
                throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", new Object[]{this.b}));
            }
            if (b()) {
                d.a(this.b.a().c(), RescheduleReceiver.class, true);
                c();
            }
            this.c.a(h.a);
        } catch (Throwable th) {
            this.c.a(new a(th));
        }
    }

    public h a() {
        return this.c;
    }

    @VisibleForTesting
    public boolean b() {
        WorkDatabase d = this.b.a().d();
        d.f();
        try {
            boolean a = a(this.b);
            d.h();
            return a;
        } finally {
            d.g();
        }
    }

    @VisibleForTesting
    public void c() {
        androidx.work.impl.h a = this.b.a();
        e.a(a.e(), a.d(), a.f());
    }

    private static boolean a(@NonNull androidx.work.impl.f fVar) {
        List<androidx.work.impl.f> h = fVar.h();
        int i = 0;
        if (h != null) {
            int i2 = 0;
            for (androidx.work.impl.f fVar2 : h) {
                if (fVar2.f()) {
                    f.a().d(a, String.format("Already enqueued work ids (%s).", new Object[]{TextUtils.join(", ", fVar2.e())}), new Throwable[0]);
                } else {
                    i2 |= a(fVar2);
                }
            }
            i = i2;
        }
        return b(fVar) | i;
    }

    private static boolean b(@NonNull androidx.work.impl.f fVar) {
        boolean a = a(fVar.a(), fVar.d(), (String[]) androidx.work.impl.f.a(fVar).toArray(new String[0]), fVar.b(), fVar.c());
        fVar.g();
        return a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:95:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x019c A:{LOOP_END, LOOP:6: B:102:0x0196->B:104:0x019c} */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x01c5 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x011e  */
    private static boolean a(androidx.work.impl.h r18, @android.support.annotation.NonNull java.util.List<? extends androidx.work.l> r19, java.lang.String[] r20, java.lang.String r21, androidx.work.ExistingWorkPolicy r22) {
        /*
        r0 = r20;
        r1 = r21;
        r2 = r22;
        r3 = java.lang.System.currentTimeMillis();
        r5 = r18.d();
        r6 = 0;
        r7 = 1;
        if (r0 == 0) goto L_0x0017;
    L_0x0012:
        r8 = r0.length;
        if (r8 <= 0) goto L_0x0017;
    L_0x0015:
        r8 = r7;
        goto L_0x0018;
    L_0x0017:
        r8 = r6;
    L_0x0018:
        if (r8 == 0) goto L_0x005b;
    L_0x001a:
        r9 = r0.length;
        r10 = r6;
        r12 = r10;
        r13 = r12;
        r11 = r7;
    L_0x001f:
        if (r10 >= r9) goto L_0x005e;
    L_0x0021:
        r14 = r0[r10];
        r15 = r5.m();
        r15 = r15.b(r14);
        if (r15 != 0) goto L_0x0043;
    L_0x002d:
        r0 = androidx.work.f.a();
        r1 = a;
        r2 = "Prerequisite %s doesn't exist; not enqueuing";
        r3 = new java.lang.Object[r7];
        r3[r6] = r14;
        r2 = java.lang.String.format(r2, r3);
        r3 = new java.lang.Throwable[r6];
        r0.e(r1, r2, r3);
        return r6;
    L_0x0043:
        r14 = r15.b;
        r15 = androidx.work.WorkInfo.State.SUCCEEDED;
        if (r14 != r15) goto L_0x004b;
    L_0x0049:
        r15 = r7;
        goto L_0x004c;
    L_0x004b:
        r15 = r6;
    L_0x004c:
        r11 = r11 & r15;
        r15 = androidx.work.WorkInfo.State.FAILED;
        if (r14 != r15) goto L_0x0053;
    L_0x0051:
        r12 = r7;
        goto L_0x0058;
    L_0x0053:
        r15 = androidx.work.WorkInfo.State.CANCELLED;
        if (r14 != r15) goto L_0x0058;
    L_0x0057:
        r13 = r7;
    L_0x0058:
        r10 = r10 + 1;
        goto L_0x001f;
    L_0x005b:
        r12 = r6;
        r13 = r12;
        r11 = r7;
    L_0x005e:
        r9 = android.text.TextUtils.isEmpty(r21);
        r9 = r9 ^ r7;
        if (r9 == 0) goto L_0x0069;
    L_0x0065:
        if (r8 != 0) goto L_0x0069;
    L_0x0067:
        r10 = r7;
        goto L_0x006a;
    L_0x0069:
        r10 = r6;
    L_0x006a:
        if (r10 == 0) goto L_0x0113;
    L_0x006c:
        r10 = r5.m();
        r10 = r10.c(r1);
        r14 = r10.isEmpty();
        if (r14 != 0) goto L_0x0113;
    L_0x007a:
        r14 = androidx.work.ExistingWorkPolicy.APPEND;
        if (r2 != r14) goto L_0x00cd;
    L_0x007e:
        r2 = r5.n();
        r8 = new java.util.ArrayList;
        r8.<init>();
        r10 = r10.iterator();
    L_0x008b:
        r14 = r10.hasNext();
        if (r14 == 0) goto L_0x00c0;
    L_0x0091:
        r14 = r10.next();
        r14 = (androidx.work.impl.b.j.a) r14;
        r15 = r14.a;
        r15 = r2.c(r15);
        if (r15 != 0) goto L_0x00be;
    L_0x009f:
        r15 = r14.b;
        r7 = androidx.work.WorkInfo.State.SUCCEEDED;
        if (r15 != r7) goto L_0x00a7;
    L_0x00a5:
        r7 = 1;
        goto L_0x00a8;
    L_0x00a7:
        r7 = r6;
    L_0x00a8:
        r7 = r7 & r11;
        r11 = r14.b;
        r15 = androidx.work.WorkInfo.State.FAILED;
        if (r11 != r15) goto L_0x00b1;
    L_0x00af:
        r12 = 1;
        goto L_0x00b8;
    L_0x00b1:
        r11 = r14.b;
        r15 = androidx.work.WorkInfo.State.CANCELLED;
        if (r11 != r15) goto L_0x00b8;
    L_0x00b7:
        r13 = 1;
    L_0x00b8:
        r11 = r14.a;
        r8.add(r11);
        r11 = r7;
    L_0x00be:
        r7 = 1;
        goto L_0x008b;
    L_0x00c0:
        r0 = r8.toArray(r0);
        r0 = (java.lang.String[]) r0;
        r2 = r0.length;
        if (r2 <= 0) goto L_0x00cb;
    L_0x00c9:
        r8 = 1;
        goto L_0x0113;
    L_0x00cb:
        r8 = r6;
        goto L_0x0113;
    L_0x00cd:
        r7 = androidx.work.ExistingWorkPolicy.KEEP;
        if (r2 != r7) goto L_0x00ee;
    L_0x00d1:
        r2 = r10.iterator();
    L_0x00d5:
        r7 = r2.hasNext();
        if (r7 == 0) goto L_0x00ee;
    L_0x00db:
        r7 = r2.next();
        r7 = (androidx.work.impl.b.j.a) r7;
        r14 = r7.b;
        r15 = androidx.work.WorkInfo.State.ENQUEUED;
        if (r14 == r15) goto L_0x00ed;
    L_0x00e7:
        r7 = r7.b;
        r14 = androidx.work.WorkInfo.State.RUNNING;
        if (r7 != r14) goto L_0x00d5;
    L_0x00ed:
        return r6;
    L_0x00ee:
        r2 = r18;
        r2 = androidx.work.impl.utils.a.a(r1, r2, r6);
        r2.run();
        r2 = r5.m();
        r7 = r10.iterator();
    L_0x00ff:
        r10 = r7.hasNext();
        if (r10 == 0) goto L_0x0111;
    L_0x0105:
        r10 = r7.next();
        r10 = (androidx.work.impl.b.j.a) r10;
        r10 = r10.a;
        r2.a(r10);
        goto L_0x00ff;
    L_0x0111:
        r2 = 1;
        goto L_0x0114;
    L_0x0113:
        r2 = r6;
    L_0x0114:
        r7 = r19.iterator();
    L_0x0118:
        r10 = r7.hasNext();
        if (r10 == 0) goto L_0x01cc;
    L_0x011e:
        r10 = r7.next();
        r10 = (androidx.work.l) r10;
        r14 = r10.b();
        if (r8 == 0) goto L_0x013f;
    L_0x012a:
        if (r11 != 0) goto L_0x013f;
    L_0x012c:
        if (r12 == 0) goto L_0x0133;
    L_0x012e:
        r15 = androidx.work.WorkInfo.State.FAILED;
        r14.b = r15;
        goto L_0x0147;
    L_0x0133:
        if (r13 == 0) goto L_0x013a;
    L_0x0135:
        r15 = androidx.work.WorkInfo.State.CANCELLED;
        r14.b = r15;
        goto L_0x0147;
    L_0x013a:
        r15 = androidx.work.WorkInfo.State.BLOCKED;
        r14.b = r15;
        goto L_0x0147;
    L_0x013f:
        r15 = r14.a();
        if (r15 != 0) goto L_0x014a;
    L_0x0145:
        r14.n = r3;
    L_0x0147:
        r16 = r7;
        goto L_0x0150;
    L_0x014a:
        r16 = r7;
        r6 = 0;
        r14.n = r6;
    L_0x0150:
        r6 = android.os.Build.VERSION.SDK_INT;
        r7 = 23;
        if (r6 < r7) goto L_0x015f;
    L_0x0156:
        r6 = android.os.Build.VERSION.SDK_INT;
        r7 = 25;
        if (r6 > r7) goto L_0x015f;
    L_0x015c:
        a(r14);
    L_0x015f:
        r6 = r14.b;
        r7 = androidx.work.WorkInfo.State.ENQUEUED;
        if (r6 != r7) goto L_0x0166;
    L_0x0165:
        r2 = 1;
    L_0x0166:
        r6 = r5.m();
        r6.a(r14);
        if (r8 == 0) goto L_0x018c;
    L_0x016f:
        r6 = r0.length;
        r7 = 0;
    L_0x0171:
        if (r7 >= r6) goto L_0x018c;
    L_0x0173:
        r14 = r0[r7];
        r15 = new androidx.work.impl.b.a;
        r17 = r0;
        r0 = r10.a();
        r15.<init>(r0, r14);
        r0 = r5.n();
        r0.a(r15);
        r7 = r7 + 1;
        r0 = r17;
        goto L_0x0171;
    L_0x018c:
        r17 = r0;
        r0 = r10.c();
        r0 = r0.iterator();
    L_0x0196:
        r6 = r0.hasNext();
        if (r6 == 0) goto L_0x01b3;
    L_0x019c:
        r6 = r0.next();
        r6 = (java.lang.String) r6;
        r7 = r5.o();
        r14 = new androidx.work.impl.b.m;
        r15 = r10.a();
        r14.<init>(r6, r15);
        r7.a(r14);
        goto L_0x0196;
    L_0x01b3:
        if (r9 == 0) goto L_0x01c5;
    L_0x01b5:
        r0 = r5.q();
        r6 = new androidx.work.impl.b.g;
        r7 = r10.a();
        r6.<init>(r1, r7);
        r0.a(r6);
    L_0x01c5:
        r7 = r16;
        r0 = r17;
        r6 = 0;
        goto L_0x0118;
    L_0x01cc:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.b.a(androidx.work.impl.h, java.util.List, java.lang.String[], java.lang.String, androidx.work.ExistingWorkPolicy):boolean");
    }

    private static void a(j jVar) {
        androidx.work.b bVar = jVar.j;
        if (bVar.d() || bVar.e()) {
            String str = jVar.c;
            d.a aVar = new d.a();
            aVar.a(jVar.e).a("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME", str);
            jVar.c = ConstraintTrackingWorker.class.getName();
            jVar.e = aVar.a();
        }
    }
}
