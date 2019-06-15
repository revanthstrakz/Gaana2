package com.exoplayer2.upstream.cache;

import com.constants.Constants;
import com.exoplayer2.CookieSpan;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import java.io.File;
import java.util.Iterator;

public class r {
    private final File a;
    private final AtomicFile b;
    private boolean c;
    private ReusableBufferedOutputStream d;
    private int e;

    public r(File file, int i) {
        this.a = file;
        this.e = i;
        this.b = new AtomicFile(new File(file, "track_cookie_content_index.exi"));
    }

    public void a() {
        if (!e()) {
            this.b.delete();
        }
    }

    public void b() {
        d();
        this.c = false;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:27:0x0060=Splitter:B:27:0x0060, B:23:0x0055=Splitter:B:23:0x0055} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0060 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0055 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:23:0x0055, B:27:0x0060] */
    private boolean d() {
        /*
        r6 = this;
        r0 = 0;
        r1 = 0;
        r2 = r6.b;	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
        r2 = r2.startWrite();	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
        r3 = r6.d;	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
        if (r3 != 0) goto L_0x0014;
    L_0x000c:
        r3 = new com.google.android.exoplayer2.util.ReusableBufferedOutputStream;	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
        r3.<init>(r2);	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
        r6.d = r3;	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
        goto L_0x0019;
    L_0x0014:
        r3 = r6.d;	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
        r3.reset(r2);	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
    L_0x0019:
        r2 = new java.io.DataOutputStream;	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
        r3 = r6.d;	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
        r2.<init>(r3);	 Catch:{ FileNotFoundException -> 0x0060, IOException -> 0x0055 }
        r3 = 1;
        r2.writeInt(r3);	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        r4 = com.constants.Constants.eg;	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        r4 = r4.size();	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        r2.writeInt(r4);	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        r4 = com.constants.Constants.eg;	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        r4 = r4.iterator();	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
    L_0x0033:
        r5 = r4.hasNext();	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        if (r5 == 0) goto L_0x0043;
    L_0x0039:
        r5 = r4.next();	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        r5 = (com.exoplayer2.CookieSpan) r5;	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        r5.a(r2);	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        goto L_0x0033;
    L_0x0043:
        r4 = r6.b;	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        r4.endWrite(r2);	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x004f, all -> 0x004c }
        com.google.android.exoplayer2.util.Util.closeQuietly(r1);
        return r3;
    L_0x004c:
        r0 = move-exception;
        r1 = r2;
        goto L_0x006b;
    L_0x004f:
        r1 = r2;
        goto L_0x0055;
    L_0x0051:
        r1 = r2;
        goto L_0x0060;
    L_0x0053:
        r0 = move-exception;
        goto L_0x006b;
    L_0x0055:
        r2 = java.lang.System.out;	 Catch:{ all -> 0x0053 }
        r3 = "Error initializing stream";
        r2.println(r3);	 Catch:{ all -> 0x0053 }
        com.google.android.exoplayer2.util.Util.closeQuietly(r1);
        return r0;
    L_0x0060:
        r2 = java.lang.System.out;	 Catch:{ all -> 0x0053 }
        r3 = "File not found";
        r2.println(r3);	 Catch:{ all -> 0x0053 }
        com.google.android.exoplayer2.util.Util.closeQuietly(r1);
        return r0;
    L_0x006b:
        com.google.android.exoplayer2.util.Util.closeQuietly(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.exoplayer2.upstream.cache.r.d():boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0052  */
    private boolean e() {
        /*
        r6 = this;
        r0 = 0;
        r1 = 0;
        r2 = new java.io.BufferedInputStream;	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0042 }
        r3 = r6.b;	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0042 }
        r3 = r3.openRead();	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0042 }
        r2.<init>(r3);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0042 }
        r3 = new java.io.DataInputStream;	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0042 }
        r3.<init>(r2);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0042 }
        r1 = r3.readInt();	 Catch:{ FileNotFoundException -> 0x003e, IOException -> 0x003a, all -> 0x0037 }
        r2 = 1;
        if (r1 == r2) goto L_0x001f;
    L_0x0019:
        if (r3 == 0) goto L_0x001e;
    L_0x001b:
        com.google.android.exoplayer2.util.Util.closeQuietly(r3);
    L_0x001e:
        return r0;
    L_0x001f:
        r1 = r3.readInt();	 Catch:{ FileNotFoundException -> 0x003e, IOException -> 0x003a, all -> 0x0037 }
        r4 = r0;
    L_0x0024:
        if (r4 >= r1) goto L_0x0031;
    L_0x0026:
        r5 = new com.exoplayer2.CookieSpan;	 Catch:{ FileNotFoundException -> 0x003e, IOException -> 0x003a, all -> 0x0037 }
        r5.<init>(r3);	 Catch:{ FileNotFoundException -> 0x003e, IOException -> 0x003a, all -> 0x0037 }
        r6.a(r5);	 Catch:{ FileNotFoundException -> 0x003e, IOException -> 0x003a, all -> 0x0037 }
        r4 = r4 + 1;
        goto L_0x0024;
    L_0x0031:
        if (r3 == 0) goto L_0x0036;
    L_0x0033:
        com.google.android.exoplayer2.util.Util.closeQuietly(r3);
    L_0x0036:
        return r2;
    L_0x0037:
        r0 = move-exception;
        r1 = r3;
        goto L_0x0050;
    L_0x003a:
        r1 = move-exception;
        r2 = r1;
        r1 = r3;
        goto L_0x0043;
    L_0x003e:
        r1 = r3;
        goto L_0x0056;
    L_0x0040:
        r0 = move-exception;
        goto L_0x0050;
    L_0x0042:
        r2 = move-exception;
    L_0x0043:
        r3 = "TrackCookieContentIndex";
        r4 = "Error reading cache content index file.";
        android.util.Log.e(r3, r4, r2);	 Catch:{ all -> 0x0040 }
        if (r1 == 0) goto L_0x004f;
    L_0x004c:
        com.google.android.exoplayer2.util.Util.closeQuietly(r1);
    L_0x004f:
        return r0;
    L_0x0050:
        if (r1 == 0) goto L_0x0055;
    L_0x0052:
        com.google.android.exoplayer2.util.Util.closeQuietly(r1);
    L_0x0055:
        throw r0;
    L_0x0056:
        if (r1 == 0) goto L_0x005b;
    L_0x0058:
        com.google.android.exoplayer2.util.Util.closeQuietly(r1);
    L_0x005b:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.exoplayer2.upstream.cache.r.e():boolean");
    }

    public void a(CookieSpan cookieSpan) {
        Constants.eg.add(cookieSpan);
        Constants.eh.put(cookieSpan.a(), cookieSpan.b());
        if (Constants.eg.size() > this.e) {
            c();
        }
    }

    public void b(CookieSpan cookieSpan) {
        Iterator it = Constants.eg.iterator();
        while (it.hasNext()) {
            if (((CookieSpan) it.next()).a().equalsIgnoreCase(cookieSpan.a())) {
                it.remove();
                a(cookieSpan);
                return;
            }
        }
        a(cookieSpan);
    }

    public void c() {
        Constants.eg.pollFirst();
    }
}
