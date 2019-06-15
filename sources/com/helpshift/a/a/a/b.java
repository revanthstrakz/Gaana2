package com.helpshift.a.a.a;

import android.content.Context;
import android.text.TextUtils;
import com.helpshift.a.a.a.a.a;
import com.helpshift.a.a.a.a.c;
import com.helpshift.util.l;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class b {
    ConcurrentHashMap<String, ConcurrentLinkedQueue<com.helpshift.a.a.a.a.b>> a = new ConcurrentHashMap();
    ConcurrentHashMap<String, ConcurrentLinkedQueue<c>> b = new ConcurrentHashMap();
    private ThreadPoolExecutor c;
    private a d;
    private Context e;

    public b(Context context, a aVar, ThreadPoolExecutor threadPoolExecutor) {
        this.e = context;
        this.d = aVar;
        this.c = threadPoolExecutor;
    }

    public void a(String str, final a aVar, com.helpshift.a.a.a.a.b bVar, c cVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Scheduling download in executor : ");
        stringBuilder.append(str);
        l.a("Helpshift_DownloadMngr", stringBuilder.toString());
        if (aVar.a && !TextUtils.isEmpty(a(str))) {
            bVar.a(true, str, a(str));
        } else if (this.a.get(str) == null || this.b.get(str) == null) {
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            if (bVar != null) {
                concurrentLinkedQueue.add(bVar);
            }
            this.a.put(str, concurrentLinkedQueue);
            ConcurrentLinkedQueue concurrentLinkedQueue2 = new ConcurrentLinkedQueue();
            if (cVar != null) {
                concurrentLinkedQueue2.add(cVar);
            }
            this.b.put(str, concurrentLinkedQueue2);
            this.c.execute(new c(this.e, this.d, str, aVar, new com.helpshift.a.a.a.a.b() {
                public void a(boolean z, String str, Object obj) {
                    if (z && aVar.b) {
                        b.this.a(str, obj.toString());
                    }
                    ConcurrentLinkedQueue concurrentLinkedQueue = (ConcurrentLinkedQueue) b.this.a.get(str);
                    if (concurrentLinkedQueue != null) {
                        Iterator it = concurrentLinkedQueue.iterator();
                        while (it.hasNext()) {
                            com.helpshift.a.a.a.a.b bVar = (com.helpshift.a.a.a.a.b) it.next();
                            if (bVar != null) {
                                bVar.a(z, str, obj);
                            }
                        }
                        b.this.a.remove(str);
                        b.this.b.remove(str);
                    }
                }
            }, new c() {
                public void a(String str, int i) {
                    ConcurrentLinkedQueue concurrentLinkedQueue = (ConcurrentLinkedQueue) b.this.b.get(str);
                    if (concurrentLinkedQueue != null) {
                        Iterator it = concurrentLinkedQueue.iterator();
                        while (it.hasNext()) {
                            c cVar = (c) it.next();
                            if (cVar != null) {
                                cVar.a(str, i);
                            }
                        }
                    }
                }
            }));
        } else {
            if (bVar != null) {
                ((ConcurrentLinkedQueue) this.a.get(str)).add(bVar);
            }
            if (cVar != null) {
                ((ConcurrentLinkedQueue) this.b.get(str)).add(cVar);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, String str2) {
        HashMap hashMap = (HashMap) this.d.a("hs-cached-downloads");
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        hashMap.put(str, str2);
        this.d.a("hs-cached-downloads", hashMap);
    }

    private String a(String str) {
        HashMap hashMap = (HashMap) this.d.a("hs-cached-downloads");
        String str2 = null;
        if (hashMap != null) {
            str = (String) hashMap.get(str);
            if (TextUtils.isEmpty(str)) {
                str2 = str;
            } else {
                File file = new File(str);
                return (file.exists() && file.canRead()) ? str : str2;
            }
        }
    }
}
