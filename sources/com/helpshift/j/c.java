package com.helpshift.j;

import android.content.Context;
import android.util.Log;
import com.helpshift.j.b.a;
import com.helpshift.j.b.b;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class c implements a {
    private static final String a = "c";
    private boolean b;
    private boolean c;
    private long d;
    private b e;
    private ThreadPoolExecutor f;

    c(Context context, String str) {
        this.e = new a(context, str);
    }

    public void a(boolean z, boolean z2) {
        this.b = z;
        if (this.c != z2) {
            this.c = z2;
            if (this.c) {
                this.f = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
                    public Thread newThread(Runnable runnable) {
                        return new Thread(runnable, "HS-Logger");
                    }
                });
            } else if (this.f != null) {
                this.f.shutdown();
            }
        }
    }

    public void a(long j) {
        this.d = j;
    }

    private boolean c() {
        return this.b;
    }

    private boolean d() {
        return this.c;
    }

    private Future a(String str, String str2, String str3, com.helpshift.j.c.a[] aVarArr) {
        b bVar = new b();
        bVar.d = str;
        bVar.e = aVarArr;
        bVar.b = str2;
        bVar.a = System.currentTimeMillis() + this.d;
        bVar.c = str3;
        try {
            return this.f.submit(new e(bVar, this.e));
        } catch (RejectedExecutionException e) {
            str2 = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Rejected execution of log message : ");
            stringBuilder.append(bVar.b);
            Log.e(str2, stringBuilder.toString(), e);
            return null;
        }
    }

    public void a(String str, String str2, Throwable[] thArr, com.helpshift.j.c.a... aVarArr) {
        if (c()) {
            String b = b(thArr);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(a(aVarArr));
            stringBuilder.append(b);
            Log.d(str, stringBuilder.toString());
        }
    }

    public void b(String str, String str2, Throwable[] thArr, com.helpshift.j.c.a... aVarArr) {
        String b;
        if (c()) {
            b = b(thArr);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(a(aVarArr));
            stringBuilder.append(b);
            Log.w(str, stringBuilder.toString());
        } else {
            b = null;
        }
        if (d()) {
            if (b == null) {
                b = b(thArr);
            }
            a("WARN", str2, b, aVarArr);
        }
    }

    public void c(String str, String str2, Throwable[] thArr, com.helpshift.j.c.a... aVarArr) {
        String b;
        if (c()) {
            b = b(thArr);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(a(aVarArr));
            stringBuilder.append(b);
            Log.e(str, stringBuilder.toString());
        } else {
            b = null;
        }
        if (d() && !a(thArr)) {
            if (b == null) {
                b = b(thArr);
            }
            a("ERROR", str2, b, aVarArr);
        }
    }

    public void d(String str, String str2, Throwable[] thArr, com.helpshift.j.c.a... aVarArr) {
        String b;
        if (c()) {
            b = b(thArr);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(a(aVarArr));
            stringBuilder.append(b);
            Log.e(str, stringBuilder.toString());
        } else {
            b = null;
        }
        if (d()) {
            if (b == null) {
                b = b(thArr);
            }
            Future a = a("FATAL", str2, b, aVarArr);
            if (a != null) {
                try {
                    a.get();
                } catch (Exception e) {
                    str2 = a;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Error logging fatal log : ");
                    stringBuilder2.append(e.getMessage());
                    Log.e(str2, stringBuilder2.toString());
                }
            }
        }
    }

    private boolean a(Throwable[] thArr) {
        boolean z = false;
        if (thArr == null) {
            return false;
        }
        for (Throwable th : thArr) {
            if (th instanceof UnknownHostException) {
                z = true;
                break;
            }
        }
        return z;
    }

    private String a(com.helpshift.j.c.a[] aVarArr) {
        String str = " ";
        if (aVarArr == null || aVarArr.length <= 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (com.helpshift.j.c.a aVar : aVarArr) {
            if (aVar != null) {
                stringBuilder.append(aVar.a());
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private String b(Throwable[] thArr) {
        String str = "";
        if (thArr == null) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (a(thArr)) {
            return "UnknownHostException";
        }
        for (Throwable stackTraceString : thArr) {
            stringBuilder.append(Log.getStackTraceString(stackTraceString));
        }
        return stringBuilder.toString();
    }

    public List<com.helpshift.j.d.a> a() {
        return this.e.a();
    }

    public void b() {
        this.e.b();
    }

    public int a(int i) {
        return this.e.a(b(i));
    }

    private List<String> b(int i) {
        if (i == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if ((i & 8) != 0) {
            arrayList.add("ERROR");
        }
        if ((i & 4) != 0) {
            arrayList.add("WARN");
        }
        if ((i & 16) != 0) {
            arrayList.add("FATAL");
        }
        return arrayList;
    }
}
