package com.facebook.ads.internal.g;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.s.a.p;
import com.facebook.ads.internal.s.d.b;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class d {
    private static final String a;
    private static final ReentrantReadWriteLock b = new ReentrantReadWriteLock();
    private static final Lock c = b.readLock();
    private static final Lock d = b.writeLock();
    private final Context e;
    private final h f = new h(this);
    private final c g = new c(this);
    private SQLiteOpenHelper h;

    private static class a<T> extends AsyncTask<Void, Void, T> {
        private final f<T> a;
        private final a<T> b;
        private final Context c;
        private com.facebook.ads.internal.g.f.a d;

        a(Context context, f<T> fVar, a<T> aVar) {
            this.a = fVar;
            this.b = aVar;
            this.c = context;
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public T doInBackground(Void... voidArr) {
            Exception e;
            T b;
            try {
                b = this.a.b();
                try {
                    this.d = this.a.c();
                    return b;
                } catch (Exception e2) {
                    e = e2;
                    com.facebook.ads.internal.s.d.a.a(this.c, "database", b.o, e);
                    this.d = com.facebook.ads.internal.g.f.a.UNKNOWN;
                    return b;
                }
            } catch (Exception e3) {
                Exception exception = e3;
                b = null;
                e = exception;
                com.facebook.ads.internal.s.d.a.a(this.c, "database", b.o, e);
                this.d = com.facebook.ads.internal.g.f.a.UNKNOWN;
                return b;
            }
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(T t) {
            if (this.d == null) {
                this.b.a(t);
            } else {
                this.b.a(this.d.a(), this.d.b());
            }
            this.b.a();
        }
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT tokens.");
        stringBuilder.append(h.a.b);
        stringBuilder.append(", ");
        stringBuilder.append("tokens");
        stringBuilder.append(".");
        stringBuilder.append(h.b.b);
        stringBuilder.append(", ");
        stringBuilder.append("events");
        stringBuilder.append(".");
        stringBuilder.append(c.a.b);
        stringBuilder.append(", ");
        stringBuilder.append("events");
        stringBuilder.append(".");
        stringBuilder.append(c.c.b);
        stringBuilder.append(", ");
        stringBuilder.append("events");
        stringBuilder.append(".");
        stringBuilder.append(c.d.b);
        stringBuilder.append(", ");
        stringBuilder.append("events");
        stringBuilder.append(".");
        stringBuilder.append(c.e.b);
        stringBuilder.append(", ");
        stringBuilder.append("events");
        stringBuilder.append(".");
        stringBuilder.append(c.f.b);
        stringBuilder.append(", ");
        stringBuilder.append("events");
        stringBuilder.append(".");
        stringBuilder.append(c.g.b);
        stringBuilder.append(", ");
        stringBuilder.append("events");
        stringBuilder.append(".");
        stringBuilder.append(c.h.b);
        stringBuilder.append(", ");
        stringBuilder.append("events");
        stringBuilder.append(".");
        stringBuilder.append(c.i.b);
        stringBuilder.append(" FROM ");
        stringBuilder.append("events");
        stringBuilder.append(" JOIN ");
        stringBuilder.append("tokens");
        stringBuilder.append(" ON ");
        stringBuilder.append("events");
        stringBuilder.append(".");
        stringBuilder.append(c.b.b);
        stringBuilder.append(" = ");
        stringBuilder.append("tokens");
        stringBuilder.append(".");
        stringBuilder.append(h.a.b);
        stringBuilder.append(" ORDER BY ");
        stringBuilder.append("events");
        stringBuilder.append(".");
        stringBuilder.append(c.e.b);
        stringBuilder.append(" ASC");
        a = stringBuilder.toString();
    }

    public d(Context context) {
        this.e = context;
    }

    private synchronized SQLiteDatabase j() {
        if (this.h == null) {
            this.h = new e(this.e, this);
        }
        return this.h.getWritableDatabase();
    }

    @WorkerThread
    public Cursor a(int i) {
        c.lock();
        try {
            SQLiteDatabase a = a();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a);
            stringBuilder.append(" LIMIT ");
            stringBuilder.append(String.valueOf(i));
            Cursor rawQuery = a.rawQuery(stringBuilder.toString(), null);
            return rawQuery;
        } finally {
            c.unlock();
        }
    }

    public SQLiteDatabase a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return j();
        }
        throw new IllegalStateException("Cannot call getDatabase from the UI thread!");
    }

    public <T> AsyncTask a(f<T> fVar, a<T> aVar) {
        return com.facebook.ads.internal.s.a.d.a(p.b, new a(this.e.getApplicationContext(), fVar, aVar), new Void[0]);
    }

    public AsyncTask a(String str, int i, String str2, double d, double d2, String str3, Map<String, String> map, a<String> aVar) {
        final String str4 = str;
        final int i2 = i;
        final String str5 = str2;
        final double d3 = d;
        final double d4 = d2;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        return a(new i<String>() {
            /* JADX WARNING: Removed duplicated region for block: B:44:0x00b9 A:{Catch:{ Exception -> 0x00bd }} */
            /* JADX WARNING: Removed duplicated region for block: B:32:0x0090 A:{Catch:{ Exception -> 0x0094 }} */
            @android.support.annotation.Nullable
            /* renamed from: a */
            public java.lang.String b() {
                /*
                r14 = this;
                r0 = r2;
                r0 = android.text.TextUtils.isEmpty(r0);
                r1 = 0;
                if (r0 == 0) goto L_0x000a;
            L_0x0009:
                return r1;
            L_0x000a:
                r0 = com.facebook.ads.internal.g.d.d;
                r0.lock();
                r0 = com.facebook.ads.internal.g.d.this;	 Catch:{ Exception -> 0x006e, all -> 0x0069 }
                r0 = r0.a();	 Catch:{ Exception -> 0x006e, all -> 0x0069 }
                r0.beginTransaction();	 Catch:{ Exception -> 0x0067 }
                r2 = com.facebook.ads.internal.g.d.this;	 Catch:{ Exception -> 0x0067 }
                r3 = r2.g;	 Catch:{ Exception -> 0x0067 }
                r2 = com.facebook.ads.internal.g.d.this;	 Catch:{ Exception -> 0x0067 }
                r2 = r2.f;	 Catch:{ Exception -> 0x0067 }
                r4 = r2;	 Catch:{ Exception -> 0x0067 }
                r4 = r2.a(r4);	 Catch:{ Exception -> 0x0067 }
                r5 = r3;	 Catch:{ Exception -> 0x0067 }
                r6 = r4;	 Catch:{ Exception -> 0x0067 }
                r7 = r5;	 Catch:{ Exception -> 0x0067 }
                r9 = r7;	 Catch:{ Exception -> 0x0067 }
                r11 = r9;	 Catch:{ Exception -> 0x0067 }
                r12 = r10;	 Catch:{ Exception -> 0x0067 }
                r2 = r3.a(r4, r5, r6, r7, r9, r11, r12);	 Catch:{ Exception -> 0x0067 }
                r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x0067 }
                if (r0 == 0) goto L_0x005f;
            L_0x0041:
                r1 = r0.isOpen();
                if (r1 == 0) goto L_0x005f;
            L_0x0047:
                r1 = r0.inTransaction();	 Catch:{ Exception -> 0x0051 }
                if (r1 == 0) goto L_0x005f;
            L_0x004d:
                r0.endTransaction();	 Catch:{ Exception -> 0x0051 }
                goto L_0x005f;
            L_0x0051:
                r0 = move-exception;
                r1 = com.facebook.ads.internal.g.d.this;
                r1 = r1.e;
                r3 = "database";
                r4 = com.facebook.ads.internal.s.d.b.n;
                com.facebook.ads.internal.s.d.a.a(r1, r3, r4, r0);
            L_0x005f:
                r0 = com.facebook.ads.internal.g.d.d;
                r0.unlock();
                return r2;
            L_0x0067:
                r2 = move-exception;
                goto L_0x0070;
            L_0x0069:
                r0 = move-exception;
                r13 = r1;
                r1 = r0;
                r0 = r13;
                goto L_0x00ab;
            L_0x006e:
                r2 = move-exception;
                r0 = r1;
            L_0x0070:
                r3 = com.facebook.ads.internal.g.f.a.DATABASE_INSERT;	 Catch:{ all -> 0x00aa }
                r14.a(r3);	 Catch:{ all -> 0x00aa }
                r3 = com.facebook.ads.internal.g.d.this;	 Catch:{ all -> 0x00aa }
                r3 = r3.e;	 Catch:{ all -> 0x00aa }
                r4 = "database";
                r5 = com.facebook.ads.internal.s.d.b.l;	 Catch:{ all -> 0x00aa }
                com.facebook.ads.internal.s.d.a.a(r3, r4, r5, r2);	 Catch:{ all -> 0x00aa }
                if (r0 == 0) goto L_0x00a2;
            L_0x0084:
                r2 = r0.isOpen();
                if (r2 == 0) goto L_0x00a2;
            L_0x008a:
                r2 = r0.inTransaction();	 Catch:{ Exception -> 0x0094 }
                if (r2 == 0) goto L_0x00a2;
            L_0x0090:
                r0.endTransaction();	 Catch:{ Exception -> 0x0094 }
                goto L_0x00a2;
            L_0x0094:
                r0 = move-exception;
                r2 = com.facebook.ads.internal.g.d.this;
                r2 = r2.e;
                r3 = "database";
                r4 = com.facebook.ads.internal.s.d.b.n;
                com.facebook.ads.internal.s.d.a.a(r2, r3, r4, r0);
            L_0x00a2:
                r0 = com.facebook.ads.internal.g.d.d;
                r0.unlock();
                return r1;
            L_0x00aa:
                r1 = move-exception;
            L_0x00ab:
                if (r0 == 0) goto L_0x00cb;
            L_0x00ad:
                r2 = r0.isOpen();
                if (r2 == 0) goto L_0x00cb;
            L_0x00b3:
                r2 = r0.inTransaction();	 Catch:{ Exception -> 0x00bd }
                if (r2 == 0) goto L_0x00cb;
            L_0x00b9:
                r0.endTransaction();	 Catch:{ Exception -> 0x00bd }
                goto L_0x00cb;
            L_0x00bd:
                r0 = move-exception;
                r2 = com.facebook.ads.internal.g.d.this;
                r2 = r2.e;
                r3 = "database";
                r4 = com.facebook.ads.internal.s.d.b.n;
                com.facebook.ads.internal.s.d.a.a(r2, r3, r4, r0);
            L_0x00cb:
                r0 = com.facebook.ads.internal.g.d.d;
                r0.unlock();
                throw r1;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.g.d$AnonymousClass1.b():java.lang.String");
            }
        }, aVar);
    }

    @WorkerThread
    public boolean a(String str) {
        d.lock();
        boolean z = false;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE ");
            stringBuilder.append("events");
            stringBuilder.append(" SET ");
            stringBuilder.append(c.i.b);
            stringBuilder.append("=");
            stringBuilder.append(c.i.b);
            stringBuilder.append("+1");
            stringBuilder.append(" WHERE ");
            stringBuilder.append(c.a.b);
            stringBuilder.append("=?");
            a().execSQL(stringBuilder.toString(), new String[]{str});
            z = true;
        } catch (SQLiteException unused) {
        }
        d.unlock();
        return z;
    }

    public synchronized void b() {
        for (g e : c()) {
            e.e();
        }
        if (this.h != null) {
            this.h.close();
            this.h = null;
        }
    }

    @WorkerThread
    public boolean b(String str) {
        d.lock();
        try {
            boolean a = this.g.a(str);
            return a;
        } finally {
            d.unlock();
        }
    }

    public g[] c() {
        return new g[]{this.f, this.g};
    }

    public Cursor d() {
        c.lock();
        try {
            Cursor c = this.g.c();
            return c;
        } finally {
            c.unlock();
        }
    }

    @WorkerThread
    public Cursor e() {
        c.lock();
        try {
            Cursor d = this.g.d();
            return d;
        } finally {
            c.unlock();
        }
    }

    @WorkerThread
    public Cursor f() {
        c.lock();
        try {
            Cursor c = this.f.c();
            return c;
        } finally {
            c.unlock();
        }
    }

    @WorkerThread
    public void g() {
        d.lock();
        try {
            this.f.d();
        } finally {
            d.unlock();
        }
    }

    @WorkerThread
    public void h() {
        d.lock();
        try {
            this.g.g();
            this.f.g();
        } finally {
            d.unlock();
        }
    }
}
