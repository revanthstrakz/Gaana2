package android.arch.persistence.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.File;

public interface c {

    public static abstract class a {
        public final int a;

        public void a(b bVar) {
        }

        public abstract void a(b bVar, int i, int i2);

        public abstract void b(b bVar);

        public void c(b bVar) {
        }

        public a(int i) {
            this.a = i;
        }

        public void b(b bVar, int i, int i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Can't downgrade database from version ");
            stringBuilder.append(i);
            stringBuilder.append(" to ");
            stringBuilder.append(i2);
            throw new SQLiteException(stringBuilder.toString());
        }

        /* JADX WARNING: Removed duplicated region for block: B:24:0x0071  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0059  */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0031 */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x002f A:{Splitter:B:5:0x0029, ExcHandler: all (th java.lang.Throwable), PHI: r0 } */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing block: B:11:0x0035, code skipped:
            if (r0 != null) goto L_0x0037;
     */
        /* JADX WARNING: Missing block: B:12:0x0037, code skipped:
            r4 = r0.iterator();
     */
        /* JADX WARNING: Missing block: B:14:0x003f, code skipped:
            if (r4.hasNext() != false) goto L_0x0041;
     */
        /* JADX WARNING: Missing block: B:15:0x0041, code skipped:
            a((java.lang.String) ((android.util.Pair) r4.next()).second);
     */
        /* JADX WARNING: Missing block: B:16:0x004f, code skipped:
            a(r4.f());
     */
        public void d(android.arch.persistence.a.b r4) {
            /*
            r3 = this;
            r0 = "SupportSQLite";
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "Corruption reported by sqlite on database: ";
            r1.append(r2);
            r2 = r4.f();
            r1.append(r2);
            r1 = r1.toString();
            android.util.Log.e(r0, r1);
            r0 = r4.e();
            if (r0 != 0) goto L_0x0028;
        L_0x0020:
            r4 = r4.f();
            r3.a(r4);
            return;
        L_0x0028:
            r0 = 0;
            r1 = r4.g();	 Catch:{ SQLiteException -> 0x0031, all -> 0x002f }
            r0 = r1;
            goto L_0x0031;
        L_0x002f:
            r1 = move-exception;
            goto L_0x0035;
        L_0x0031:
            r4.close();	 Catch:{ IOException -> 0x0057, all -> 0x002f }
            goto L_0x0057;
        L_0x0035:
            if (r0 == 0) goto L_0x004f;
        L_0x0037:
            r4 = r0.iterator();
        L_0x003b:
            r0 = r4.hasNext();
            if (r0 == 0) goto L_0x0056;
        L_0x0041:
            r0 = r4.next();
            r0 = (android.util.Pair) r0;
            r0 = r0.second;
            r0 = (java.lang.String) r0;
            r3.a(r0);
            goto L_0x003b;
        L_0x004f:
            r4 = r4.f();
            r3.a(r4);
        L_0x0056:
            throw r1;
        L_0x0057:
            if (r0 == 0) goto L_0x0071;
        L_0x0059:
            r4 = r0.iterator();
        L_0x005d:
            r0 = r4.hasNext();
            if (r0 == 0) goto L_0x0078;
        L_0x0063:
            r0 = r4.next();
            r0 = (android.util.Pair) r0;
            r0 = r0.second;
            r0 = (java.lang.String) r0;
            r3.a(r0);
            goto L_0x005d;
        L_0x0071:
            r4 = r4.f();
            r3.a(r4);
        L_0x0078:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.a.c$a.d(android.arch.persistence.a.b):void");
        }

        private void a(String str) {
            if (!str.equalsIgnoreCase(":memory:") && str.trim().length() != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("deleting the database file: ");
                stringBuilder.append(str);
                Log.w("SupportSQLite", stringBuilder.toString());
                try {
                    if (VERSION.SDK_INT >= 16) {
                        SQLiteDatabase.deleteDatabase(new File(str));
                    } else {
                        try {
                            if (!new File(str).delete()) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Could not delete the database file ");
                                stringBuilder.append(str);
                                Log.e("SupportSQLite", stringBuilder.toString());
                            }
                        } catch (Exception e) {
                            Log.e("SupportSQLite", "error while deleting corrupted database file", e);
                        }
                    }
                } catch (Exception e2) {
                    Log.w("SupportSQLite", "delete failed: ", e2);
                }
            }
        }
    }

    public static class b {
        @NonNull
        public final Context a;
        @Nullable
        public final String b;
        @NonNull
        public final a c;

        public static class a {
            Context a;
            String b;
            a c;

            public b a() {
                if (this.c == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                } else if (this.a != null) {
                    return new b(this.a, this.b, this.c);
                } else {
                    throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
                }
            }

            a(@NonNull Context context) {
                this.a = context;
            }

            public a a(@Nullable String str) {
                this.b = str;
                return this;
            }

            public a a(@NonNull a aVar) {
                this.c = aVar;
                return this;
            }
        }

        b(@NonNull Context context, @Nullable String str, @NonNull a aVar) {
            this.a = context;
            this.b = str;
            this.c = aVar;
        }

        public static a a(Context context) {
            return new a(context);
        }
    }

    public interface c {
        c a(b bVar);
    }

    b a();

    @RequiresApi(api = 16)
    void a(boolean z);
}
