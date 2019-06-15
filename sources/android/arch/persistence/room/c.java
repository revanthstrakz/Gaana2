package android.arch.persistence.room;

import android.arch.persistence.a.f;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.google.api.client.http.HttpMethods;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

public class c {
    private static final String[] f = new String[]{MoEHelperConstants.EVENT_APP_UPD, HttpMethods.DELETE, "INSERT"};
    @VisibleForTesting
    @NonNull
    ArrayMap<String, Integer> a;
    @VisibleForTesting
    @NonNull
    long[] b;
    AtomicBoolean c;
    @VisibleForTesting
    final android.arch.a.b.b<b, c> d;
    @VisibleForTesting
    Runnable e;
    private String[] g;
    private Object[] h = new Object[1];
    private long i = 0;
    private final RoomDatabase j;
    private volatile boolean k;
    private volatile f l;
    private a m;

    static class a {
        final long[] a;
        final boolean[] b;
        final int[] c;
        boolean d;
        boolean e;

        a(int i) {
            this.a = new long[i];
            this.b = new boolean[i];
            this.c = new int[i];
            Arrays.fill(this.a, 0);
            Arrays.fill(this.b, false);
        }

        /* Access modifiers changed, original: 0000 */
        @Nullable
        public int[] a() {
            synchronized (this) {
                if (this.d) {
                    if (!this.e) {
                        int length = this.a.length;
                        int i = 0;
                        while (true) {
                            boolean z = true;
                            if (i < length) {
                                boolean z2 = this.a[i] > 0;
                                if (z2 != this.b[i]) {
                                    int[] iArr = this.c;
                                    if (!z2) {
                                        z = true;
                                    }
                                    iArr[i] = z;
                                } else {
                                    this.c[i] = 0;
                                }
                                this.b[i] = z2;
                                i++;
                            } else {
                                this.e = true;
                                this.d = false;
                                int[] iArr2 = this.c;
                                return iArr2;
                            }
                        }
                    }
                }
                return null;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
            synchronized (this) {
                this.e = false;
            }
        }
    }

    public static abstract class b {
        public abstract void a(@NonNull Set<String> set);
    }

    static class c {
        final int[] a;
        final b b;
        private final String[] c;
        private final long[] d;
        private final Set<String> e;

        /* Access modifiers changed, original: 0000 */
        public void a(long[] jArr) {
            Set set = null;
            int length = this.a.length;
            for (int i = 0; i < length; i++) {
                long j = jArr[this.a[i]];
                if (this.d[i] < j) {
                    this.d[i] = j;
                    if (length == 1) {
                        set = this.e;
                    } else {
                        if (set == null) {
                            set = new ArraySet(length);
                        }
                        set.add(this.c[i]);
                    }
                }
            }
            if (set != null) {
                this.b.a(set);
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public c(RoomDatabase roomDatabase, String... strArr) {
        int i = 0;
        this.c = new AtomicBoolean(false);
        this.k = false;
        this.d = new android.arch.a.b.b();
        this.e = new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:42:0x009c  */
            public void run() {
                /*
                r6 = this;
                r0 = android.arch.persistence.room.c.this;
                r0 = r0.j;
                r0 = r0.a();
                r1 = 0;
                r0.lock();	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = android.arch.persistence.room.c.this;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.b();	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                if (r2 != 0) goto L_0x001a;
            L_0x0016:
                r0.unlock();
                return;
            L_0x001a:
                r2 = android.arch.persistence.room.c.this;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.c;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r3 = 1;
                r2 = r2.compareAndSet(r3, r1);	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                if (r2 != 0) goto L_0x0029;
            L_0x0025:
                r0.unlock();
                return;
            L_0x0029:
                r2 = android.arch.persistence.room.c.this;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.j;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.i();	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                if (r2 == 0) goto L_0x0039;
            L_0x0035:
                r0.unlock();
                return;
            L_0x0039:
                r2 = android.arch.persistence.room.c.this;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.l;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2.a();	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = android.arch.persistence.room.c.this;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.h;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r3 = android.arch.persistence.room.c.this;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r3 = r3.i;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r3 = java.lang.Long.valueOf(r3);	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2[r1] = r3;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = android.arch.persistence.room.c.this;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.j;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.b;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                if (r2 == 0) goto L_0x0087;
            L_0x005e:
                r2 = android.arch.persistence.room.c.this;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.j;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.b();	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2 = r2.a();	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                r2.a();	 Catch:{ all -> 0x0082 }
                r3 = r6.a();	 Catch:{ all -> 0x0082 }
                r2.c();	 Catch:{ all -> 0x007d }
                r2.b();	 Catch:{ SQLiteException | IllegalStateException -> 0x007a, SQLiteException | IllegalStateException -> 0x007a }
                goto L_0x0097;
            L_0x007a:
                r1 = move-exception;
                r2 = r1;
                goto L_0x0090;
            L_0x007d:
                r1 = move-exception;
                r5 = r3;
                r3 = r1;
                r1 = r5;
                goto L_0x0083;
            L_0x0082:
                r3 = move-exception;
            L_0x0083:
                r2.b();	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                throw r3;	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
            L_0x0087:
                r3 = r6.a();	 Catch:{ SQLiteException | IllegalStateException -> 0x008e, SQLiteException | IllegalStateException -> 0x008e }
                goto L_0x0097;
            L_0x008c:
                r1 = move-exception;
                goto L_0x00c9;
            L_0x008e:
                r2 = move-exception;
                r3 = r1;
            L_0x0090:
                r1 = "ROOM";
                r4 = "Cannot run invalidation tracker. Is the db closed?";
                android.util.Log.e(r1, r4, r2);	 Catch:{ all -> 0x008c }
            L_0x0097:
                r0.unlock();
                if (r3 == 0) goto L_0x00c8;
            L_0x009c:
                r0 = android.arch.persistence.room.c.this;
                r0 = r0.d;
                monitor-enter(r0);
                r1 = android.arch.persistence.room.c.this;	 Catch:{ all -> 0x00c5 }
                r1 = r1.d;	 Catch:{ all -> 0x00c5 }
                r1 = r1.iterator();	 Catch:{ all -> 0x00c5 }
            L_0x00a9:
                r2 = r1.hasNext();	 Catch:{ all -> 0x00c5 }
                if (r2 == 0) goto L_0x00c3;
            L_0x00af:
                r2 = r1.next();	 Catch:{ all -> 0x00c5 }
                r2 = (java.util.Map.Entry) r2;	 Catch:{ all -> 0x00c5 }
                r2 = r2.getValue();	 Catch:{ all -> 0x00c5 }
                r2 = (android.arch.persistence.room.c.c) r2;	 Catch:{ all -> 0x00c5 }
                r3 = android.arch.persistence.room.c.this;	 Catch:{ all -> 0x00c5 }
                r3 = r3.b;	 Catch:{ all -> 0x00c5 }
                r2.a(r3);	 Catch:{ all -> 0x00c5 }
                goto L_0x00a9;
            L_0x00c3:
                monitor-exit(r0);	 Catch:{ all -> 0x00c5 }
                goto L_0x00c8;
            L_0x00c5:
                r1 = move-exception;
                monitor-exit(r0);	 Catch:{ all -> 0x00c5 }
                throw r1;
            L_0x00c8:
                return;
            L_0x00c9:
                r0.unlock();
                throw r1;
                */
                throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.room.c$AnonymousClass1.run():void");
            }

            private boolean a() {
                Cursor a = c.this.j.a("SELECT * FROM room_table_modification_log WHERE version  > ? ORDER BY version ASC;", c.this.h);
                boolean z = false;
                while (a.moveToNext()) {
                    try {
                        long j = a.getLong(0);
                        c.this.b[a.getInt(1)] = j;
                        c.this.i = j;
                        z = true;
                    } finally {
                        a.close();
                    }
                }
                return z;
            }
        };
        this.j = roomDatabase;
        this.m = new a(strArr.length);
        this.a = new ArrayMap();
        int length = strArr.length;
        this.g = new String[length];
        while (i < length) {
            String toLowerCase = strArr[i].toLowerCase(Locale.US);
            this.a.put(toLowerCase, Integer.valueOf(i));
            this.g[i] = toLowerCase;
            i++;
        }
        this.b = new long[strArr.length];
        Arrays.fill(this.b, 0);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(android.arch.persistence.a.b bVar) {
        synchronized (this) {
            if (this.k) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            bVar.a();
            try {
                bVar.c("PRAGMA temp_store = MEMORY;");
                bVar.c("PRAGMA recursive_triggers='ON';");
                bVar.c("CREATE TEMP TABLE room_table_modification_log(version INTEGER PRIMARY KEY AUTOINCREMENT, table_id INTEGER)");
                bVar.c();
                b(bVar);
                this.l = bVar.a("DELETE FROM room_table_modification_log WHERE version NOT IN( SELECT MAX(version) FROM room_table_modification_log GROUP BY table_id)");
                this.k = true;
            } finally {
                bVar.b();
            }
        }
    }

    private static void a(StringBuilder stringBuilder, String str, String str2) {
        stringBuilder.append("`");
        stringBuilder.append("room_table_modification_trigger_");
        stringBuilder.append(str);
        stringBuilder.append("_");
        stringBuilder.append(str2);
        stringBuilder.append("`");
    }

    private void a(android.arch.persistence.a.b bVar, int i) {
        String str = this.g[i];
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : f) {
            stringBuilder.setLength(0);
            stringBuilder.append("DROP TRIGGER IF EXISTS ");
            a(stringBuilder, str, str2);
            bVar.c(stringBuilder.toString());
        }
    }

    private void b(android.arch.persistence.a.b bVar, int i) {
        String str = this.g[i];
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : f) {
            stringBuilder.setLength(0);
            stringBuilder.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            a(stringBuilder, str, str2);
            stringBuilder.append(" AFTER ");
            stringBuilder.append(str2);
            stringBuilder.append(" ON `");
            stringBuilder.append(str);
            stringBuilder.append("` BEGIN INSERT OR REPLACE INTO ");
            stringBuilder.append("room_table_modification_log");
            stringBuilder.append(" VALUES(null, ");
            stringBuilder.append(i);
            stringBuilder.append("); END");
            bVar.c(stringBuilder.toString());
        }
    }

    private boolean b() {
        if (!this.j.d()) {
            return false;
        }
        if (!this.k) {
            this.j.b().a();
        }
        if (this.k) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public void a() {
        if (this.c.compareAndSet(false, true)) {
            android.arch.a.a.a.a().a(this.e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b(android.arch.persistence.a.b bVar) {
        if (!bVar.d()) {
            while (true) {
                try {
                    Lock a = this.j.a();
                    a.lock();
                    try {
                        int[] a2 = this.m.a();
                        if (a2 == null) {
                            a.unlock();
                            return;
                        }
                        int length = a2.length;
                        bVar.a();
                        for (int i = 0; i < length; i++) {
                            switch (a2[i]) {
                                case 1:
                                    b(bVar, i);
                                    break;
                                case 2:
                                    a(bVar, i);
                                    break;
                                default:
                                    break;
                            }
                        }
                        bVar.c();
                        bVar.b();
                        this.m.b();
                        a.unlock();
                    } catch (Throwable th) {
                        a.unlock();
                    }
                } catch (SQLiteException | IllegalStateException e) {
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
                    return;
                }
            }
        }
    }
}
