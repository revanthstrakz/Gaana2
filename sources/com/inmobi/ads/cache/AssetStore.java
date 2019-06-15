package com.inmobi.ads.cache;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.inmobi.ads.bm;
import com.inmobi.ads.c.b;
import com.inmobi.ads.c.j;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.g;
import com.squareup.picasso.Callback;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public final class AssetStore implements c {
    public static final Object e = new Object();
    private static final String f = "AssetStore";
    private static AssetStore o;
    private static final Object p = new Object();
    public d a;
    public b b;
    public ExecutorService c;
    public AtomicBoolean d = new AtomicBoolean(false);
    private j g;
    private ExecutorService h;
    private a i;
    private HandlerThread j;
    private AtomicBoolean k = new AtomicBoolean(false);
    private ConcurrentHashMap<String, a> l;
    private g.b m;
    private g.b n;
    private List<b> q = new ArrayList();
    private final e r = new e() {
        public final void a(@NonNull d dVar, @NonNull String str, @NonNull a aVar) {
            AssetStore.f;
            StringBuilder stringBuilder = new StringBuilder("Asset fetch succeeded for URL ");
            stringBuilder.append(aVar.d);
            stringBuilder.append(" Updating location on disk (file://");
            stringBuilder.append(str);
            stringBuilder.append(")");
            a a = new com.inmobi.ads.cache.a.a().a(aVar.d, str, dVar, AssetStore.this.b.a, AssetStore.this.b.e).a();
            AssetStore.this.a;
            d.b(a);
            a.k = aVar.k;
            a.a = aVar.a;
            AssetStore.this.a(a, true);
            try {
                AssetStore.c(AssetStore.this);
            } catch (Exception e) {
                AssetStore.f;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }

        public final void a(@NonNull a aVar) {
            AssetStore.f;
            StringBuilder stringBuilder = new StringBuilder("Asset fetch failed for remote URL (");
            stringBuilder.append(aVar.d);
            stringBuilder.append(")");
            AssetStore.this.c(aVar.d);
            if (aVar.c <= 0) {
                AssetStore.f;
                AssetStore.this.a(aVar, false);
                AssetStore.this.a;
                d.c(aVar);
            } else {
                AssetStore.f;
                aVar.f = System.currentTimeMillis();
                AssetStore.this.a;
                d.b(aVar);
                if (!com.inmobi.commons.core.utilities.d.a()) {
                    AssetStore.this.a(aVar, false);
                }
            }
            try {
                AssetStore.c(AssetStore.this);
            } catch (Exception e) {
                AssetStore.f;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
    };

    /* renamed from: com.inmobi.ads.cache.AssetStore$4 */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ b a;

        public AnonymousClass4(b bVar) {
            this.a = bVar;
        }

        public final void run() {
            AssetStore.this.b(this.a);
            AssetStore.f;
            StringBuilder stringBuilder = new StringBuilder("Attempting to cache ");
            stringBuilder.append(this.a.b.size());
            stringBuilder.append("remote URLs ");
            for (bm bmVar : this.a.b) {
                AssetStore.b(AssetStore.this, bmVar.b);
            }
        }
    }

    private class PicassoCallback implements Callback {
        private CountDownLatch b;
        private String c;

        PicassoCallback(CountDownLatch countDownLatch, String str) {
            this.b = countDownLatch;
            this.c = str;
        }

        public void onSuccess() {
            AssetStore.this.a(this.c);
            this.b.countDown();
        }

        public void onError() {
            AssetStore.this.b(this.c);
            this.b.countDown();
        }
    }

    private static final class a extends Handler {
        private WeakReference<AssetStore> a;
        private final e b = new e() {
            public final void a(d dVar, String str, a aVar) {
                AssetStore assetStore = (AssetStore) a.this.a.get();
                if (assetStore != null) {
                    AssetStore.f;
                    StringBuilder stringBuilder = new StringBuilder("Asset fetch succeeded for URL ");
                    stringBuilder.append(aVar.d);
                    stringBuilder.append(" Updating location on disk (file://");
                    stringBuilder.append(str);
                    stringBuilder.append(")");
                    a a = new com.inmobi.ads.cache.a.a().a(aVar.d, str, dVar, assetStore.b.a, assetStore.b.e).a();
                    assetStore.a;
                    d.b(a);
                    a.k = aVar.k;
                    a.a = aVar.a;
                    assetStore.a(a, true);
                    a.this.a();
                    return;
                }
                AssetStore.f;
            }

            public final void a(a aVar) {
                AssetStore assetStore = (AssetStore) a.this.a.get();
                if (assetStore != null) {
                    AssetStore.f;
                    StringBuilder stringBuilder = new StringBuilder("Asset fetch failed for remote URL (");
                    stringBuilder.append(aVar.d);
                    stringBuilder.append(")");
                    assetStore.c(aVar.d);
                    if (aVar.c > 0) {
                        aVar.c--;
                        aVar.f = System.currentTimeMillis();
                        assetStore.a;
                        d.b(aVar);
                        a.this.b();
                        return;
                    }
                    assetStore.a(aVar, false);
                    a.this.a(aVar);
                    return;
                }
                AssetStore.f;
            }
        };

        a(@NonNull Looper looper, @NonNull AssetStore assetStore) {
            super(looper);
            this.a = new WeakReference(assetStore);
        }

        /* JADX WARNING: Removed duplicated region for block: B:47:0x0110 A:{Catch:{ Exception -> 0x0152 }} */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x0109 A:{Catch:{ Exception -> 0x0152 }} */
        public final void handleMessage(android.os.Message r9) {
            /*
            r8 = this;
            r0 = r9.what;	 Catch:{ Exception -> 0x0152 }
            switch(r0) {
                case 1: goto L_0x00c2;
                case 2: goto L_0x0022;
                case 3: goto L_0x001d;
                case 4: goto L_0x0006;
                default: goto L_0x0005;
            };	 Catch:{ Exception -> 0x0152 }
        L_0x0005:
            return;
        L_0x0006:
            r9 = r9.obj;	 Catch:{ Exception -> 0x0152 }
            r9 = (com.inmobi.ads.cache.a) r9;	 Catch:{ Exception -> 0x0152 }
            r0 = r8.a;	 Catch:{ Exception -> 0x0152 }
            r0 = r0.get();	 Catch:{ Exception -> 0x0152 }
            r0 = (com.inmobi.ads.cache.AssetStore) r0;	 Catch:{ Exception -> 0x0152 }
            if (r0 == 0) goto L_0x001a;
        L_0x0014:
            r0.a;	 Catch:{ Exception -> 0x0152 }
            com.inmobi.ads.cache.d.c(r9);	 Catch:{ Exception -> 0x0152 }
        L_0x001a:
            r8.b();	 Catch:{ Exception -> 0x0152 }
        L_0x001d:
            r8.b();	 Catch:{ Exception -> 0x0152 }
            goto L_0x0151;
        L_0x0022:
            r0 = r8.a;	 Catch:{ Exception -> 0x0152 }
            r0 = r0.get();	 Catch:{ Exception -> 0x0152 }
            if (r0 == 0) goto L_0x0151;
        L_0x002a:
            r0 = r8.a;	 Catch:{ Exception -> 0x0152 }
            r0 = r0.get();	 Catch:{ Exception -> 0x0152 }
            r0 = (com.inmobi.ads.cache.AssetStore) r0;	 Catch:{ Exception -> 0x0152 }
            r9 = r9.obj;	 Catch:{ Exception -> 0x0152 }
            r9 = (java.lang.String) r9;	 Catch:{ Exception -> 0x0152 }
            r0.a;	 Catch:{ Exception -> 0x0152 }
            r9 = com.inmobi.ads.cache.d.b(r9);	 Catch:{ Exception -> 0x0152 }
            if (r9 != 0) goto L_0x0043;
        L_0x003f:
            r8.b();	 Catch:{ Exception -> 0x0152 }
            return;
        L_0x0043:
            r1 = r9.a();	 Catch:{ Exception -> 0x0152 }
            r2 = 1;
            if (r1 != 0) goto L_0x00b8;
        L_0x004a:
            r1 = r0.b;	 Catch:{ Exception -> 0x0152 }
            r1 = r1.a;	 Catch:{ Exception -> 0x0152 }
            r3 = r9.c;	 Catch:{ Exception -> 0x0152 }
            r1 = r1 - r3;
            r1 = r1 + r2;
            r2 = r9.c;	 Catch:{ Exception -> 0x0152 }
            r3 = 0;
            if (r2 != 0) goto L_0x0064;
        L_0x0059:
            r1 = 11;
            r9.l = r1;	 Catch:{ Exception -> 0x0152 }
            r0.a(r9, r3);	 Catch:{ Exception -> 0x0152 }
            r8.a(r9);	 Catch:{ Exception -> 0x0152 }
            return;
        L_0x0064:
            r2 = com.inmobi.commons.core.utilities.d.a();	 Catch:{ Exception -> 0x0152 }
            if (r2 != 0) goto L_0x0071;
        L_0x006a:
            r0.a(r9, r3);	 Catch:{ Exception -> 0x0152 }
            r0.c();	 Catch:{ Exception -> 0x0152 }
            return;
        L_0x0071:
            r2 = r8.b;	 Catch:{ Exception -> 0x0152 }
            r0 = r0.a(r9, r2);	 Catch:{ Exception -> 0x0152 }
            if (r0 == 0) goto L_0x00a5;
        L_0x0079:
            com.inmobi.ads.cache.AssetStore.f;	 Catch:{ Exception -> 0x0152 }
            r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0152 }
            r2 = "Cache miss in handler; attempting to cache asset: ";
            r0.<init>(r2);	 Catch:{ Exception -> 0x0152 }
            r2 = r9.d;	 Catch:{ Exception -> 0x0152 }
            r0.append(r2);	 Catch:{ Exception -> 0x0152 }
            com.inmobi.ads.cache.AssetStore.f;	 Catch:{ Exception -> 0x0152 }
            r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0152 }
            r2 = "Download attempt # ";
            r0.<init>(r2);	 Catch:{ Exception -> 0x0152 }
            r0.append(r1);	 Catch:{ Exception -> 0x0152 }
            r1 = " in handler  to cache asset (";
            r0.append(r1);	 Catch:{ Exception -> 0x0152 }
            r9 = r9.d;	 Catch:{ Exception -> 0x0152 }
            r0.append(r9);	 Catch:{ Exception -> 0x0152 }
            r9 = ")";
            r0.append(r9);	 Catch:{ Exception -> 0x0152 }
            return;
        L_0x00a5:
            com.inmobi.ads.cache.AssetStore.f;	 Catch:{ Exception -> 0x0152 }
            r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0152 }
            r1 = "Cache miss in handler; but already attempting: ";
            r0.<init>(r1);	 Catch:{ Exception -> 0x0152 }
            r9 = r9.d;	 Catch:{ Exception -> 0x0152 }
            r0.append(r9);	 Catch:{ Exception -> 0x0152 }
            r8.b();	 Catch:{ Exception -> 0x0152 }
            return;
        L_0x00b8:
            com.inmobi.ads.cache.AssetStore.f;	 Catch:{ Exception -> 0x0152 }
            r8.a();	 Catch:{ Exception -> 0x0152 }
            r0.a(r9, r2);	 Catch:{ Exception -> 0x0152 }
            return;
        L_0x00c2:
            r9 = r8.a;	 Catch:{ Exception -> 0x0152 }
            r9 = r9.get();	 Catch:{ Exception -> 0x0152 }
            if (r9 == 0) goto L_0x0151;
        L_0x00ca:
            r9 = r8.a;	 Catch:{ Exception -> 0x0152 }
            r9 = r9.get();	 Catch:{ Exception -> 0x0152 }
            r9 = (com.inmobi.ads.cache.AssetStore) r9;	 Catch:{ Exception -> 0x0152 }
            r0 = r9.b;	 Catch:{ Exception -> 0x0152 }
            r1 = 0;
            if (r0 != 0) goto L_0x00e7;
        L_0x00d9:
            r0 = new com.inmobi.ads.c;	 Catch:{ Exception -> 0x0152 }
            r0.<init>();	 Catch:{ Exception -> 0x0152 }
            r2 = com.inmobi.commons.core.configs.b.a();	 Catch:{ Exception -> 0x0152 }
            r2.a(r0, r1);	 Catch:{ Exception -> 0x0152 }
            r0 = r0.n;	 Catch:{ Exception -> 0x0152 }
        L_0x00e7:
            r9.a;	 Catch:{ Exception -> 0x0152 }
            r2 = r0.b;	 Catch:{ Exception -> 0x0152 }
            r2 = com.inmobi.ads.cache.d.a(r2);	 Catch:{ Exception -> 0x0152 }
            r2 = r2.iterator();	 Catch:{ Exception -> 0x0152 }
        L_0x00f4:
            r3 = r2.hasNext();	 Catch:{ Exception -> 0x0152 }
            if (r3 == 0) goto L_0x0107;
        L_0x00fa:
            r3 = r2.next();	 Catch:{ Exception -> 0x0152 }
            r3 = (com.inmobi.ads.cache.a) r3;	 Catch:{ Exception -> 0x0152 }
            r4 = r3.a();	 Catch:{ Exception -> 0x0152 }
            if (r4 != 0) goto L_0x00f4;
        L_0x0106:
            r1 = r3;
        L_0x0107:
            if (r1 != 0) goto L_0x0110;
        L_0x0109:
            com.inmobi.ads.cache.AssetStore.f;	 Catch:{ Exception -> 0x0152 }
            r9.c();	 Catch:{ Exception -> 0x0152 }
            return;
        L_0x0110:
            com.inmobi.ads.cache.AssetStore.f;	 Catch:{ Exception -> 0x0152 }
            r9 = android.os.Message.obtain();	 Catch:{ Exception -> 0x0152 }
            r2 = 2;
            r9.what = r2;	 Catch:{ Exception -> 0x0152 }
            r2 = r1.d;	 Catch:{ Exception -> 0x0152 }
            r9.obj = r2;	 Catch:{ Exception -> 0x0152 }
            r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0152 }
            r4 = r1.f;	 Catch:{ Exception -> 0x0152 }
            r6 = r2 - r4;
            r1 = r0.b;	 Catch:{ Exception -> 0x013e }
            r1 = r1 * 1000;
            r1 = (long) r1;	 Catch:{ Exception -> 0x013e }
            r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1));
            if (r3 >= 0) goto L_0x013a;
        L_0x012f:
            r0 = r0.b;	 Catch:{ Exception -> 0x013e }
            r0 = r0 * 1000;
            r0 = (long) r0;	 Catch:{ Exception -> 0x013e }
            r2 = r0 - r6;
            r8.sendMessageDelayed(r9, r2);	 Catch:{ Exception -> 0x013e }
            return;
        L_0x013a:
            r8.sendMessage(r9);	 Catch:{ Exception -> 0x013e }
            return;
        L_0x013e:
            r9 = move-exception;
            com.inmobi.ads.cache.AssetStore.f;	 Catch:{ Exception -> 0x0152 }
            r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0152 }
            r1 = "Encountered unexpected error in Asset fetch handler";
            r0.<init>(r1);	 Catch:{ Exception -> 0x0152 }
            r9 = r9.getMessage();	 Catch:{ Exception -> 0x0152 }
            r0.append(r9);	 Catch:{ Exception -> 0x0152 }
            return;
        L_0x0151:
            return;
        L_0x0152:
            r9 = move-exception;
            com.inmobi.ads.cache.AssetStore.f;
            r0 = com.inmobi.commons.core.a.a.a();
            r1 = new com.inmobi.commons.core.e.a;
            r1.<init>(r9);
            r0.a(r1);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.cache.AssetStore$a.handleMessage(android.os.Message):void");
        }

        private void a(a aVar) {
            try {
                Message obtain = Message.obtain();
                obtain.what = 4;
                obtain.obj = aVar;
                sendMessage(obtain);
            } catch (Exception e) {
                AssetStore.f;
                new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e.getMessage());
            }
        }

        private void a() {
            try {
                sendEmptyMessage(3);
            } catch (Exception e) {
                AssetStore.f;
                new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e.getMessage());
            }
        }

        private void b() {
            try {
                sendEmptyMessage(1);
            } catch (Exception e) {
                AssetStore.f;
                new StringBuilder("Encountered unexpected error in Asset fetch handler").append(e.getMessage());
            }
        }
    }

    private AssetStore() {
        com.inmobi.commons.core.configs.a cVar = new com.inmobi.ads.c();
        com.inmobi.commons.core.configs.b.a().a(cVar, (c) this);
        this.b = cVar.n;
        this.g = cVar.m;
        this.a = d.a();
        this.c = Executors.newCachedThreadPool();
        this.h = Executors.newFixedThreadPool(1);
        this.j = new HandlerThread("assetFetcher");
        this.j.start();
        this.i = new a(this.j.getLooper(), this);
        this.m = new g.b() {
            public final void a(boolean z) {
                if (z) {
                    AssetStore.c(AssetStore.this);
                } else {
                    AssetStore.this.c();
                }
            }
        };
        if (VERSION.SDK_INT >= 23) {
            this.n = new g.b() {
                public final void a(boolean z) {
                    if (z) {
                        AssetStore.this.c();
                    } else {
                        AssetStore.c(AssetStore.this);
                    }
                }
            };
        }
        this.l = new ConcurrentHashMap(2, 0.9f, 2);
    }

    public static AssetStore a() {
        AssetStore assetStore = o;
        if (assetStore == null) {
            synchronized (p) {
                assetStore = o;
                if (assetStore == null) {
                    assetStore = new AssetStore();
                    o = assetStore;
                }
            }
        }
        return assetStore;
    }

    public final void a(com.inmobi.commons.core.configs.a aVar) {
        com.inmobi.ads.c cVar = (com.inmobi.ads.c) aVar;
        this.b = cVar.n;
        this.g = cVar.m;
    }

    private synchronized void a(String str) {
        for (int i = 0; i < this.q.size(); i++) {
            int i2;
            b bVar = (b) this.q.get(i);
            Set<bm> set = bVar.b;
            Set set2 = bVar.c;
            for (bm bmVar : set) {
                if (bmVar.b.equals(str)) {
                    i2 = 1;
                    break;
                }
            }
            i2 = 0;
            if (!(i2 == 0 || set2.contains(str))) {
                bVar.c.add(str);
                bVar.d++;
            }
        }
    }

    private synchronized void b(String str) {
        for (int i = 0; i < this.q.size(); i++) {
            int i2;
            b bVar = (b) this.q.get(i);
            for (bm bmVar : bVar.b) {
                if (bmVar.b.equals(str)) {
                    i2 = 1;
                    break;
                }
            }
            i2 = 0;
            if (i2 != 0) {
                bVar.e++;
            }
        }
    }

    private synchronized void b(a aVar) {
        for (int i = 0; i < this.q.size(); i++) {
            Object obj;
            b bVar = (b) this.q.get(i);
            for (bm bmVar : bVar.b) {
                if (bmVar.b.equals(aVar.d)) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (!(obj == null || bVar.a.contains(aVar))) {
                bVar.a.add(aVar);
            }
        }
    }

    private synchronized void b(b bVar) {
        if (!this.q.contains(bVar)) {
            this.q.add(bVar);
        }
    }

    private synchronized void a(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            this.q.remove(list.get(i));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:? A:{SYNTHETIC, Splitter:B:11:0x0063, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A:{SYNTHETIC, Splitter:B:11:0x0063, ExcHandler: FileNotFoundException (unused java.io.FileNotFoundException)} */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A:{SYNTHETIC, Splitter:B:11:0x0063, ExcHandler: MalformedURLException (unused java.net.MalformedURLException)} */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A:{SYNTHETIC, Splitter:B:38:0x011b, ExcHandler: ProtocolException (unused java.net.ProtocolException)} */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A:{SYNTHETIC, Splitter:B:38:0x011b, ExcHandler: IOException (unused java.io.IOException)} */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A:{SYNTHETIC, Splitter:B:11:0x0063, ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException)} */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A:{SYNTHETIC, Splitter:B:11:0x0063, ExcHandler: FileNotFoundException (unused java.io.FileNotFoundException)} */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A:{SYNTHETIC, Splitter:B:11:0x0063, ExcHandler: MalformedURLException (unused java.net.MalformedURLException)} */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A:{SYNTHETIC, Splitter:B:11:0x0063, ExcHandler: Exception (unused java.lang.Exception)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:39:?, code skipped:
            r8.l = 7;
            r8.c = 0;
     */
    /* JADX WARNING: Missing block: B:42:0x0123, code skipped:
            if (r14.exists() == false) goto L_0x0128;
     */
    /* JADX WARNING: Missing block: B:43:0x0125, code skipped:
            r14.delete();
     */
    /* JADX WARNING: Missing block: B:44:0x0128, code skipped:
            r5.disconnect();
            com.inmobi.commons.core.utilities.d.a(r6);
     */
    /* JADX WARNING: Missing block: B:45:0x012f, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:48:?, code skipped:
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(r0));
     */
    /* JADX WARNING: Missing block: B:52:0x0192, code skipped:
            r2 = 8;
     */
    /* JADX WARNING: Missing block: B:54:0x0195, code skipped:
            r2 = 8;
     */
    /* JADX WARNING: Missing block: B:56:0x0198, code skipped:
            r8.l = 0;
            r10.a.a(r8);
     */
    /* JADX WARNING: Missing block: B:64:0x01b5, code skipped:
            r8.l = 3;
            r10.a.a(r8);
     */
    /* JADX WARNING: Missing block: B:66:0x01bf, code skipped:
            r8.l = 4;
            r10.a.a(r8);
     */
    /* JADX WARNING: Missing block: B:68:0x01c9, code skipped:
            r8.l = 4;
            r10.a.a(r8);
     */
    private boolean a(com.inmobi.ads.cache.a r25, com.inmobi.ads.cache.e r26) {
        /*
        r24 = this;
        r1 = r24;
        r8 = r25;
        r2 = r1.l;
        r3 = r8.d;
        r2 = r2.putIfAbsent(r3, r8);
        r2 = (com.inmobi.ads.cache.a) r2;
        r9 = 0;
        if (r2 != 0) goto L_0x01de;
    L_0x0011:
        r10 = new com.inmobi.ads.cache.c;
        r2 = r26;
        r10.<init>(r2);
        r2 = r1.g;
        r2 = r2.c;
        r4 = r1.g;
        r4 = r4.e;
        r5 = new java.lang.StringBuilder;
        r6 = "Fetching asset (";
        r5.<init>(r6);
        r6 = r8.d;
        r5.append(r6);
        r6 = ")";
        r5.append(r6);
        r5 = com.inmobi.commons.core.utilities.d.a();
        r12 = 8;
        if (r5 != 0) goto L_0x0043;
    L_0x0039:
        r8.l = r12;
        r2 = r10.a;
        r2.a(r8);
    L_0x0040:
        r2 = 1;
        goto L_0x01dd;
    L_0x0043:
        r5 = r8.d;
        r6 = "";
        r5 = r5.equals(r6);
        if (r5 != 0) goto L_0x01d3;
    L_0x004d:
        r5 = r8.d;
        r5 = android.webkit.URLUtil.isValidUrl(r5);
        if (r5 != 0) goto L_0x0057;
    L_0x0055:
        goto L_0x01d3;
    L_0x0057:
        r5 = r4.size();
        r5 = new java.lang.String[r5];
        r4 = r4.toArray(r5);
        r4 = (java.lang.String[]) r4;
        r21 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r5 = new java.net.URL;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r6 = r8.d;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r5.<init>(r6);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r5 = r5.openConnection();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r5 = (java.net.HttpURLConnection) r5;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r6 = "GET";
        r5.setRequestMethod(r6);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r6 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r5.setConnectTimeout(r6);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r5.setReadTimeout(r6);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r6 = r5.getResponseCode();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r7 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r6 >= r7) goto L_0x00bb;
    L_0x008a:
        r6 = r5.getContentType();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r7 = r4.length;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r15 = r9;
    L_0x0090:
        if (r15 >= r7) goto L_0x00ad;
    L_0x0092:
        r11 = r4[r15];	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        if (r6 == 0) goto L_0x00aa;
    L_0x0096:
        r14 = java.util.Locale.ENGLISH;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r11 = r11.toLowerCase(r14);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r14 = java.util.Locale.ENGLISH;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r14 = r6.toLowerCase(r14);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r11 = r11.equals(r14);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        if (r11 == 0) goto L_0x00aa;
    L_0x00a8:
        r4 = 1;
        goto L_0x00ae;
    L_0x00aa:
        r15 = r15 + 1;
        goto L_0x0090;
    L_0x00ad:
        r4 = r9;
    L_0x00ae:
        if (r4 != 0) goto L_0x00bb;
    L_0x00b0:
        r2 = 6;
        r8.l = r2;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r8.c = r9;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r2 = r10.a;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r2.a(r8);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        goto L_0x0040;
    L_0x00bb:
        r4 = r5.getContentLength();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r6 = (long) r4;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r14 = 0;
        r4 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1));
        r11 = 7;
        if (r4 < 0) goto L_0x00e8;
    L_0x00c7:
        r4 = new java.lang.StringBuilder;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r14 = "ContentSize: ";
        r4.<init>(r14);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r4.append(r6);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r14 = " max size: ";
        r4.append(r14);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r4.append(r2);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r4 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r4 <= 0) goto L_0x00e8;
    L_0x00dd:
        r8.l = r11;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r8.c = r9;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r2 = r10.a;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r2.a(r8);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        goto L_0x0040;
    L_0x00e8:
        r5.connect();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r4 = r8.d;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r14 = com.inmobi.commons.a.a.a(r4);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r4 = r14.exists();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        if (r4 == 0) goto L_0x00fa;
    L_0x00f7:
        r14.delete();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
    L_0x00fa:
        r4 = r5.getInputStream();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r6 = new java.io.BufferedOutputStream;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r7 = new java.io.FileOutputStream;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r7.<init>(r14);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r6.<init>(r7);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r7 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r7 = new byte[r7];	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        r17 = 0;
    L_0x010e:
        r15 = r4.read(r7);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x01ab, IOException -> 0x01a1, Exception -> 0x0198 }
        if (r15 <= 0) goto L_0x0159;
    L_0x0114:
        r12 = (long) r15;
        r19 = r17 + r12;
        r12 = (r19 > r2 ? 1 : (r19 == r2 ? 0 : -1));
        if (r12 <= 0) goto L_0x0151;
    L_0x011b:
        r8.l = r11;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r8.c = r9;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r2 = r14.exists();	 Catch:{ Exception -> 0x012f, SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192 }
        if (r2 == 0) goto L_0x0128;
    L_0x0125:
        r14.delete();	 Catch:{ Exception -> 0x012f, SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192 }
    L_0x0128:
        r5.disconnect();	 Catch:{ Exception -> 0x012f, SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192 }
        com.inmobi.commons.core.utilities.d.a(r6);	 Catch:{ Exception -> 0x012f, SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192 }
        goto L_0x013d;
    L_0x012f:
        r0 = move-exception;
        r2 = r0;
        r3 = com.inmobi.commons.core.a.a.a();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r4 = new com.inmobi.commons.core.e.a;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r4.<init>(r2);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r3.a(r4);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
    L_0x013d:
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r15 = r21;
        r17 = r19;
        r19 = r2;
        com.inmobi.ads.cache.c.a(r15, r17, r19);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r2 = r10.a;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r2.a(r8);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        goto L_0x0040;
    L_0x0151:
        r6.write(r7, r9, r15);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r17 = r19;
        r12 = 8;
        goto L_0x010e;
    L_0x0159:
        r6.flush();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r5.disconnect();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        com.inmobi.commons.core.utilities.d.a(r6);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r11 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r15 = r21;
        r19 = r11;
        com.inmobi.ads.cache.c.a(r15, r17, r19);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r13 = new com.inmobi.commons.core.network.d;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r13.<init>();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r2 = r5.getHeaderFields();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r13.d = r2;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r2 = r8;
        r3 = r14;
        r4 = r21;
        r6 = r11;
        r2 = com.inmobi.ads.cache.c.a(r2, r3, r4, r6);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r8.k = r2;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r2 = r11 - r21;
        r8.a = r2;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r2 = r10.a;	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r3 = r14.getAbsolutePath();	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        r2.a(r13, r3, r8);	 Catch:{ SocketTimeoutException -> 0x01c9, FileNotFoundException -> 0x01bf, MalformedURLException -> 0x01b5, ProtocolException -> 0x0195, IOException -> 0x0192, Exception -> 0x0198 }
        goto L_0x0040;
    L_0x0192:
        r2 = 8;
        goto L_0x01a2;
    L_0x0195:
        r2 = 8;
        goto L_0x01ac;
    L_0x0198:
        r8.l = r9;
        r2 = r10.a;
        r2.a(r8);
        goto L_0x0040;
    L_0x01a1:
        r2 = r12;
    L_0x01a2:
        r8.l = r2;
        r2 = r10.a;
        r2.a(r8);
        goto L_0x0040;
    L_0x01ab:
        r2 = r12;
    L_0x01ac:
        r8.l = r2;
        r2 = r10.a;
        r2.a(r8);
        goto L_0x0040;
    L_0x01b5:
        r2 = 3;
        r8.l = r2;
        r2 = r10.a;
        r2.a(r8);
        goto L_0x0040;
    L_0x01bf:
        r2 = 4;
        r8.l = r2;
        r2 = r10.a;
        r2.a(r8);
        goto L_0x0040;
    L_0x01c9:
        r2 = 4;
        r8.l = r2;
        r2 = r10.a;
        r2.a(r8);
        goto L_0x0040;
    L_0x01d3:
        r2 = 3;
        r8.l = r2;
        r2 = r10.a;
        r2.a(r8);
        goto L_0x0040;
    L_0x01dd:
        return r2;
    L_0x01de:
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.cache.AssetStore.a(com.inmobi.ads.cache.a, com.inmobi.ads.cache.e):boolean");
    }

    private void c(String str) {
        this.l.remove(str);
    }

    private synchronized void a(@NonNull a aVar, boolean z) {
        b(aVar);
        c(aVar.d);
        if (z) {
            a(aVar.d);
            e();
            return;
        }
        b(aVar.d);
        f();
    }

    private synchronized void e() {
        List arrayList = new ArrayList();
        for (int i = 0; i < this.q.size(); i++) {
            b bVar = (b) this.q.get(i);
            if (bVar.d == bVar.b.size()) {
                try {
                    f a = bVar.a();
                    if (a != null) {
                        a.b(bVar);
                    }
                    arrayList.add(bVar);
                } catch (Exception e) {
                    new StringBuilder("Encountered unexpected error in onAssetFetchSucceeded handler: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        }
        a(arrayList);
    }

    private synchronized void f() {
        List arrayList = new ArrayList();
        for (int i = 0; i < this.q.size(); i++) {
            b bVar = (b) this.q.get(i);
            if (bVar.e > 0) {
                try {
                    f a = bVar.a();
                    if (a != null) {
                        a.a(bVar);
                    }
                    arrayList.add(bVar);
                } catch (Exception e) {
                    new StringBuilder("Encountered unexpected error in onAssetFetchFailed handler: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        }
        a(arrayList);
    }

    /* JADX WARNING: Missing block: B:33:0x0090, code skipped:
            return;
     */
    public final void b() {
        /*
        r7 = this;
        r0 = r7.d;
        r1 = 0;
        r0.set(r1);
        r0 = com.inmobi.commons.core.utilities.d.a();
        if (r0 != 0) goto L_0x0013;
    L_0x000c:
        r7.g();
        r7.h();
        return;
    L_0x0013:
        r0 = e;
        monitor-enter(r0);
        r2 = r7.k;	 Catch:{ all -> 0x0091 }
        r3 = 1;
        r1 = r2.compareAndSet(r1, r3);	 Catch:{ all -> 0x0091 }
        if (r1 == 0) goto L_0x008f;
    L_0x001f:
        r1 = r7.j;	 Catch:{ all -> 0x0091 }
        if (r1 != 0) goto L_0x0031;
    L_0x0023:
        r1 = new android.os.HandlerThread;	 Catch:{ all -> 0x0091 }
        r2 = "assetFetcher";
        r1.<init>(r2);	 Catch:{ all -> 0x0091 }
        r7.j = r1;	 Catch:{ all -> 0x0091 }
        r1 = r7.j;	 Catch:{ all -> 0x0091 }
        r1.start();	 Catch:{ all -> 0x0091 }
    L_0x0031:
        r1 = r7.i;	 Catch:{ all -> 0x0091 }
        if (r1 != 0) goto L_0x0042;
    L_0x0035:
        r1 = new com.inmobi.ads.cache.AssetStore$a;	 Catch:{ all -> 0x0091 }
        r2 = r7.j;	 Catch:{ all -> 0x0091 }
        r2 = r2.getLooper();	 Catch:{ all -> 0x0091 }
        r1.<init>(r2, r7);	 Catch:{ all -> 0x0091 }
        r7.i = r1;	 Catch:{ all -> 0x0091 }
    L_0x0042:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0091 }
        r1.<init>();	 Catch:{ all -> 0x0091 }
        r2 = r7.b;	 Catch:{ all -> 0x0091 }
        r2 = r2.b;	 Catch:{ all -> 0x0091 }
        r2 = com.inmobi.ads.cache.d.a(r2);	 Catch:{ all -> 0x0091 }
        r4 = r2.isEmpty();	 Catch:{ all -> 0x0091 }
        if (r4 == 0) goto L_0x005a;
    L_0x0055:
        r7.c();	 Catch:{ all -> 0x0091 }
        monitor-exit(r0);	 Catch:{ all -> 0x0091 }
        return;
    L_0x005a:
        r2 = r2.iterator();	 Catch:{ all -> 0x0091 }
    L_0x005e:
        r4 = r2.hasNext();	 Catch:{ all -> 0x0091 }
        if (r4 == 0) goto L_0x007a;
    L_0x0064:
        r4 = r2.next();	 Catch:{ all -> 0x0091 }
        r4 = (com.inmobi.ads.cache.a) r4;	 Catch:{ all -> 0x0091 }
        r5 = r1.indexOf(r4);	 Catch:{ all -> 0x0091 }
        r6 = -1;
        if (r5 != r6) goto L_0x005e;
    L_0x0071:
        r5 = r4.a();	 Catch:{ all -> 0x0091 }
        if (r5 != 0) goto L_0x005e;
    L_0x0077:
        r1.add(r4);	 Catch:{ all -> 0x0091 }
    L_0x007a:
        r1 = r1.isEmpty();	 Catch:{ all -> 0x0091 }
        if (r1 == 0) goto L_0x0084;
    L_0x0080:
        r7.c();	 Catch:{ all -> 0x0091 }
        goto L_0x008f;
    L_0x0084:
        r7.g();	 Catch:{ all -> 0x0091 }
        r7.h();	 Catch:{ all -> 0x0091 }
        r1 = r7.i;	 Catch:{ all -> 0x0091 }
        r1.sendEmptyMessage(r3);	 Catch:{ all -> 0x0091 }
    L_0x008f:
        monitor-exit(r0);	 Catch:{ all -> 0x0091 }
        return;
    L_0x0091:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0091 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.cache.AssetStore.b():void");
    }

    public static void a(a aVar) {
        d.c(aVar);
        File file = new File(aVar.e);
        if (file.exists()) {
            file.delete();
        }
    }

    public final void a(final b bVar) {
        this.c.execute(new Runnable() {
            public final void run() {
                AssetStore.this.b(bVar);
                AssetStore.f;
                StringBuilder stringBuilder = new StringBuilder("Attempting to cache ");
                stringBuilder.append(bVar.b.size());
                stringBuilder.append("remote URLs ");
                List arrayList = new ArrayList();
                ArrayList<String> arrayList2 = new ArrayList();
                for (bm bmVar : bVar.b) {
                    if (bmVar.b.trim().length() <= 0 || bmVar.a != 2) {
                        arrayList2.add(bmVar.b);
                    } else {
                        arrayList.add(bmVar.b);
                    }
                }
                AssetStore.a(AssetStore.this, arrayList);
                AssetStore.this.e();
                AssetStore.this.f();
                for (String b : arrayList2) {
                    AssetStore.b(AssetStore.this, b);
                }
            }
        });
    }

    private void c(a aVar) {
        File file = new File(aVar.e);
        long min = Math.min(System.currentTimeMillis() + (aVar.h - aVar.f), System.currentTimeMillis() + (1000 * this.b.e));
        com.inmobi.ads.cache.a.a aVar2 = new com.inmobi.ads.cache.a.a();
        String str = aVar.d;
        String str2 = aVar.e;
        int i = this.b.a;
        long j = aVar.i;
        aVar2.c = str;
        aVar2.d = str2;
        aVar2.b = i;
        aVar2.g = min;
        aVar2.h = j;
        a a = aVar2.a();
        a.f = System.currentTimeMillis();
        d.b(a);
        a.k = c.a(aVar, file, aVar.f, aVar.f);
        a.j = true;
        a(a, true);
    }

    @TargetApi(23)
    private void g() {
        g.a();
        g.b bVar = this.m;
        if (VERSION.SDK_INT < 28) {
            g.a(bVar, com.til.colombia.android.internal.d.a);
        } else {
            g.a(bVar, "SYSTEM_CONNECTIVITY_CHANGE");
        }
        if (VERSION.SDK_INT >= 23) {
            g.a();
            g.a(this.n, "android.os.action.DEVICE_IDLE_MODE_CHANGED");
        }
    }

    @TargetApi(23)
    private void h() {
        g.a().a(this.m);
        if (VERSION.SDK_INT >= 23) {
            g.a().a("android.os.action.DEVICE_IDLE_MODE_CHANGED", this.n);
        }
    }

    public final void c() {
        synchronized (e) {
            this.k.set(false);
            this.l.clear();
            if (this.j != null) {
                this.j.getLooper().quit();
                this.j.interrupt();
                this.j = null;
                this.i = null;
            }
        }
    }

    static /* synthetic */ void b(AssetStore assetStore, final String str) {
        a a = d.a(str);
        if (a == null || !a.a()) {
            a = new com.inmobi.ads.cache.a.a().a(str, assetStore.b.a, assetStore.b.e).a();
            if (d.a(str) == null) {
                assetStore.a.a(a);
            }
            assetStore.h.execute(new Runnable() {
                public final void run() {
                    AssetStore.this.a;
                    a a = d.a(str);
                    if (a != null) {
                        if (a.a()) {
                            AssetStore.this.c(a);
                        } else if (AssetStore.this.a(a, AssetStore.this.r)) {
                            AssetStore.f;
                            new StringBuilder("Cache miss; attempting to cache asset: ").append(str);
                        } else {
                            AssetStore.f;
                            new StringBuilder("Cache miss; but already attempting: ").append(str);
                        }
                    }
                }
            });
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("Cache hit; file exists location on disk (");
        stringBuilder.append(a.e);
        stringBuilder.append(")");
        assetStore.c(a);
    }
}
