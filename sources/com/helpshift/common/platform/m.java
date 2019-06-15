package com.helpshift.common.platform;

import android.content.Context;
import android.os.Environment;
import com.helpshift.a.a.a.b;
import com.helpshift.common.domain.g;
import com.helpshift.downloader.SupportDownloader;
import com.helpshift.downloader.a;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class m implements SupportDownloader {
    private static final TimeUnit a = TimeUnit.SECONDS;
    private final b b;
    private Context c;
    private Map<String, Set<a>> d = new HashMap();

    public m(Context context, o oVar) {
        this.c = context;
        this.b = new b(context, new q(oVar), new ThreadPoolExecutor(5, 5, 1, a, new LinkedBlockingQueue(), new g("sp-dwnld")));
    }

    private String a() {
        return com.helpshift.util.b.a(this.c, "android.permission.WRITE_EXTERNAL_STORAGE") ? Environment.DIRECTORY_DOWNLOADS : null;
    }

    /* JADX WARNING: Missing block: B:2:0x0014, code skipped:
            if (r0 == null) goto L_0x001e;
     */
    /* JADX WARNING: Missing block: B:7:0x001e, code skipped:
            r4 = true;
     */
    /* JADX WARNING: Missing block: B:8:0x0020, code skipped:
            r4 = false;
     */
    /* JADX WARNING: Missing block: B:9:0x0021, code skipped:
            r2.b.a(r3, new com.helpshift.a.a.a.a.a().a(true).c(true).b(r4).a(r0).a(), new com.helpshift.common.platform.m.AnonymousClass1(r2), new com.helpshift.common.platform.m.AnonymousClass2(r2));
     */
    /* JADX WARNING: Missing block: B:10:0x0049, code skipped:
            return;
     */
    public void a(java.lang.String r3, com.helpshift.downloader.SupportDownloader.StorageDirType r4, com.helpshift.downloader.a r5) {
        /*
        r2 = this;
        r2.a(r3, r5);
        r0 = r2.a();
        r1 = com.helpshift.common.platform.m.AnonymousClass3.a;
        r4 = r4.ordinal();
        r4 = r1[r4];
        r1 = 1;
        switch(r4) {
            case 1: goto L_0x001d;
            case 2: goto L_0x0017;
            case 3: goto L_0x0014;
            default: goto L_0x0013;
        };
    L_0x0013:
        goto L_0x0020;
    L_0x0014:
        if (r0 != 0) goto L_0x0020;
    L_0x0016:
        goto L_0x001e;
    L_0x0017:
        if (r0 != 0) goto L_0x0020;
    L_0x0019:
        r5.a(r3);
        return;
    L_0x001d:
        r0 = 0;
    L_0x001e:
        r4 = r1;
        goto L_0x0021;
    L_0x0020:
        r4 = 0;
    L_0x0021:
        r5 = new com.helpshift.a.a.a.a$a;
        r5.<init>();
        r5 = r5.a(r1);
        r5 = r5.c(r1);
        r4 = r5.b(r4);
        r4 = r4.a(r0);
        r4 = r4.a();
        r5 = r2.b;
        r0 = new com.helpshift.common.platform.m$1;
        r0.<init>();
        r1 = new com.helpshift.common.platform.m$2;
        r1.<init>();
        r5.a(r3, r4, r0, r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.platform.m.a(java.lang.String, com.helpshift.downloader.SupportDownloader$StorageDirType, com.helpshift.downloader.a):void");
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, String str2) {
        for (a a : c(str)) {
            a.a(str, str2);
        }
        b(str);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, int i) {
        for (a a : c(str)) {
            a.a(str, i);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        for (a a : c(str)) {
            a.a(str);
        }
        b(str);
    }

    private synchronized void a(String str, a aVar) {
        if (aVar != null) {
            Set set = (Set) this.d.get(str);
            if (set == null) {
                set = new HashSet();
            }
            set.add(aVar);
            this.d.put(str, set);
        }
    }

    private synchronized void b(String str) {
        this.d.remove(str);
    }

    private synchronized Set<a> c(String str) {
        Set<a> hashSet;
        Set set = (Set) this.d.get(str);
        if (set == null) {
            hashSet = new HashSet();
        } else {
            hashSet = new HashSet(set);
        }
        return hashSet;
    }
}
