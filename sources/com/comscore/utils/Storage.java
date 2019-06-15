package com.comscore.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

public class Storage {
    public static final String APP_NAME_KEY = "appName";
    private static final String h = "cSPrefs";
    private static final long i = 300;
    protected SharedPreferences a;
    protected final HashMap<String, String> b;
    protected final HashSet<String> c;
    protected final Object d;
    protected final Object e;
    protected long f;
    protected boolean g;
    private final Runnable j;

    public Storage(Context context) {
        this(context, h);
    }

    public Storage(Context context, String str) {
        this.b = new HashMap();
        this.c = new HashSet();
        this.d = new Object();
        this.e = new Object();
        this.f = -1;
        this.g = false;
        this.j = new d(this);
        a(context, str);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        synchronized (this.d) {
            if (this.g) {
                if (this.f < 0) {
                    this.f = System.currentTimeMillis() + 300;
                    new Thread(this.j).start();
                } else {
                    this.f = System.currentTimeMillis() + 300;
                    this.d.notify();
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(Context context, String str) {
        synchronized (this.e) {
            synchronized (this.d) {
                this.a = context.getSharedPreferences(str, 0);
                for (Entry entry : this.a.getAll().entrySet()) {
                    if (entry.getValue() instanceof String) {
                        this.b.put(entry.getKey(), (String) entry.getValue());
                    }
                }
                this.g = true;
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0020 */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    public void add(java.lang.String r7, long r8) {
        /*
        r6 = this;
        r0 = r6.e;
        monitor-enter(r0);
        r1 = r6.g;	 Catch:{ all -> 0x003d }
        if (r1 == 0) goto L_0x003b;
    L_0x0007:
        r1 = 0;
        r3 = r6.has(r7);	 Catch:{ all -> 0x003d }
        r3 = r3.booleanValue();	 Catch:{ all -> 0x003d }
        if (r3 == 0) goto L_0x0034;
    L_0x0013:
        r3 = r6.b;	 Catch:{ all -> 0x003d }
        r3 = r3.get(r7);	 Catch:{ all -> 0x003d }
        r3 = (java.lang.String) r3;	 Catch:{ all -> 0x003d }
        r3 = java.lang.Long.parseLong(r3);	 Catch:{ NumberFormatException -> 0x0020 }
        r1 = r3;
    L_0x0020:
        r3 = r6.b;	 Catch:{ all -> 0x003d }
        r4 = r8 + r1;
        r8 = java.lang.Long.toString(r4);	 Catch:{ all -> 0x003d }
        r3.put(r7, r8);	 Catch:{ all -> 0x003d }
        r8 = r6.c;	 Catch:{ all -> 0x003d }
        r8.add(r7);	 Catch:{ all -> 0x003d }
        r6.a();	 Catch:{ all -> 0x003d }
        goto L_0x003b;
    L_0x0034:
        r8 = java.lang.Long.toString(r8);	 Catch:{ all -> 0x003d }
        r6.set(r7, r8);	 Catch:{ all -> 0x003d }
    L_0x003b:
        monitor-exit(r0);	 Catch:{ all -> 0x003d }
        return;
    L_0x003d:
        r7 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x003d }
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.utils.Storage.add(java.lang.String, long):void");
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0018 */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:7|8|9) */
    public void b() {
        /*
        r7 = this;
    L_0x0000:
        r0 = r7.d;
        monitor-enter(r0);
        r1 = r7.f;	 Catch:{ all -> 0x001a }
        r3 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x001a }
        r5 = r1 - r3;
        r1 = 0;
        r3 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1));
        if (r3 > 0) goto L_0x0013;
    L_0x0011:
        monitor-exit(r0);	 Catch:{ all -> 0x001a }
        return;
    L_0x0013:
        r1 = r7.d;	 Catch:{ InterruptedException -> 0x0018 }
        r1.wait(r5);	 Catch:{ InterruptedException -> 0x0018 }
    L_0x0018:
        monitor-exit(r0);	 Catch:{ all -> 0x001a }
        goto L_0x0000;
    L_0x001a:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x001a }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.utils.Storage.b():void");
    }

    /* Access modifiers changed, original: protected */
    public void c() {
        synchronized (this.e) {
            synchronized (this.d) {
                this.f = -1;
            }
            Editor edit = this.a.edit();
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (this.b.containsKey(str)) {
                    edit.putString(str, (String) this.b.get(str));
                } else {
                    edit.remove(str);
                }
            }
            edit.commit();
            this.c.clear();
        }
    }

    public void clear() {
        synchronized (this.e) {
            if (this.g) {
                this.c.addAll(this.b.keySet());
                this.b.clear();
                a();
            }
        }
    }

    public void close() {
        synchronized (this.e) {
            this.g = false;
            while (this.f >= 0) {
                try {
                    this.e.wait(100);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    /* JADX WARNING: Missing block: B:12:0x001e, code skipped:
            return "";
     */
    public java.lang.String get(java.lang.String r3) {
        /*
        r2 = this;
        r0 = r2.e;
        monitor-enter(r0);
        r1 = r2.g;	 Catch:{ all -> 0x001f }
        if (r1 == 0) goto L_0x001b;
    L_0x0007:
        r1 = r2.has(r3);	 Catch:{ all -> 0x001f }
        r1 = r1.booleanValue();	 Catch:{ all -> 0x001f }
        if (r1 == 0) goto L_0x001b;
    L_0x0011:
        r1 = r2.b;	 Catch:{ all -> 0x001f }
        r3 = r1.get(r3);	 Catch:{ all -> 0x001f }
        r3 = (java.lang.String) r3;	 Catch:{ all -> 0x001f }
        monitor-exit(r0);	 Catch:{ all -> 0x001f }
        return r3;
    L_0x001b:
        monitor-exit(r0);	 Catch:{ all -> 0x001f }
        r3 = "";
        return r3;
    L_0x001f:
        r3 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x001f }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.utils.Storage.get(java.lang.String):java.lang.String");
    }

    public Boolean has(String str) {
        synchronized (this.e) {
            if (this.g) {
                Boolean valueOf = Boolean.valueOf(this.b.containsKey(str));
                return valueOf;
            }
            return Boolean.valueOf(false);
        }
    }

    public void remove(String str) {
        synchronized (this.e) {
            if (this.g && has(str).booleanValue()) {
                this.b.remove(str);
                this.c.add(str);
                a();
            }
        }
    }

    public void set(String str, String str2) {
        synchronized (this.e) {
            if (this.g) {
                String str3 = (String) this.b.get(str);
                if (!this.b.containsKey(str) || (str3 != str2 && (str2 == null || str3 == null || !str3.equals(str2)))) {
                    this.b.put(str, str2);
                    this.c.add(str);
                    a();
                }
            }
        }
    }
}
