package com.helpshift.common.platform;

import android.content.Context;
import com.helpshift.conversation.activeconversation.message.j;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class a implements com.helpshift.conversation.a.a {
    private com.helpshift.common.a.a a;

    a(Context context) {
        this.a = com.helpshift.common.a.a.a(context);
    }

    public void a() {
        this.a.a();
    }

    public synchronized com.helpshift.conversation.activeconversation.a a(String str) {
        return this.a.a(str);
    }

    public synchronized com.helpshift.conversation.activeconversation.a a(long j) {
        com.helpshift.conversation.activeconversation.a a = this.a.a(Long.valueOf(j));
        if (a == null) {
            return null;
        }
        a.b(this.a.c(j));
        return a;
    }

    public synchronized List<com.helpshift.conversation.activeconversation.a> b(long j) {
        return this.a.a(j);
    }

    public synchronized void a(com.helpshift.conversation.activeconversation.a aVar) {
        b(aVar);
        b(aVar.g);
    }

    /* JADX WARNING: Missing block: B:24:0x0052, code skipped:
            return;
     */
    public synchronized void b(com.helpshift.conversation.activeconversation.a r6) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r6.a;	 Catch:{ all -> 0x0053 }
        r1 = r6.b;	 Catch:{ all -> 0x0053 }
        if (r0 != 0) goto L_0x000b;
    L_0x0007:
        if (r1 != 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r5);
        return;
    L_0x000b:
        r2 = -1;
        if (r0 != 0) goto L_0x0036;
    L_0x000f:
        if (r1 == 0) goto L_0x0036;
    L_0x0011:
        r0 = r5.a;	 Catch:{ all -> 0x0053 }
        r0 = r0.a(r1);	 Catch:{ all -> 0x0053 }
        if (r0 != 0) goto L_0x0027;
    L_0x0019:
        r0 = r5.a;	 Catch:{ all -> 0x0053 }
        r0 = r0.a(r6);	 Catch:{ all -> 0x0053 }
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 == 0) goto L_0x0051;
    L_0x0023:
        r6.a(r0);	 Catch:{ all -> 0x0053 }
        goto L_0x0051;
    L_0x0027:
        r0 = r0.a;	 Catch:{ all -> 0x0053 }
        r0 = r0.longValue();	 Catch:{ all -> 0x0053 }
        r6.a(r0);	 Catch:{ all -> 0x0053 }
        r0 = r5.a;	 Catch:{ all -> 0x0053 }
        r0.b(r6);	 Catch:{ all -> 0x0053 }
        goto L_0x0051;
    L_0x0036:
        r1 = r5.a;	 Catch:{ all -> 0x0053 }
        r0 = r1.a(r0);	 Catch:{ all -> 0x0053 }
        if (r0 != 0) goto L_0x004c;
    L_0x003e:
        r0 = r5.a;	 Catch:{ all -> 0x0053 }
        r0 = r0.a(r6);	 Catch:{ all -> 0x0053 }
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 == 0) goto L_0x0051;
    L_0x0048:
        r6.a(r0);	 Catch:{ all -> 0x0053 }
        goto L_0x0051;
    L_0x004c:
        r0 = r5.a;	 Catch:{ all -> 0x0053 }
        r0.b(r6);	 Catch:{ all -> 0x0053 }
    L_0x0051:
        monitor-exit(r5);
        return;
    L_0x0053:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.platform.a.b(com.helpshift.conversation.activeconversation.a):void");
    }

    public synchronized void a(List<com.helpshift.conversation.activeconversation.a> list) {
        if (list.size() != 0) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            for (com.helpshift.conversation.activeconversation.a aVar : list) {
                Long l = aVar.a;
                String str = aVar.b;
                if (l != null || str != null) {
                    if (l == null && str != null) {
                        com.helpshift.conversation.activeconversation.a a = this.a.a(str);
                        if (a == null) {
                            arrayList.add(aVar);
                        } else {
                            aVar.a(a.a.longValue());
                            arrayList2.add(aVar);
                        }
                    } else if (this.a.a(l) == null) {
                        arrayList.add(aVar);
                    } else {
                        arrayList2.add(aVar);
                    }
                }
            }
            List a2 = this.a.a(arrayList);
            HashSet hashSet = new HashSet();
            for (int i = 0; i < arrayList.size(); i++) {
                long longValue = ((Long) a2.get(i)).longValue();
                com.helpshift.conversation.activeconversation.a aVar2 = (com.helpshift.conversation.activeconversation.a) arrayList.get(i);
                if (longValue == -1) {
                    hashSet.add(aVar2);
                } else {
                    aVar2.a(longValue);
                }
            }
            this.a.b(arrayList2);
            arrayList = new ArrayList();
            for (com.helpshift.conversation.activeconversation.a aVar3 : list) {
                if (!hashSet.contains(aVar3)) {
                    arrayList.addAll(aVar3.g);
                }
            }
            b(arrayList);
        }
    }

    public synchronized List<j> c(long j) {
        return this.a.c(j);
    }

    public synchronized void a(j jVar) {
        Long l = jVar.n;
        String str = jVar.i;
        long a;
        if (l == null && str == null) {
            a = this.a.a(jVar);
            if (a != -1) {
                jVar.n = Long.valueOf(a);
            }
        } else if (l == null && str != null) {
            j b = this.a.b(str);
            if (b == null) {
                a = this.a.a(jVar);
                if (a != -1) {
                    jVar.n = Long.valueOf(a);
                }
            } else {
                jVar.n = b.n;
                this.a.b(jVar);
            }
        } else if (this.a.b(l) == null) {
            a = this.a.a(jVar);
            if (a != -1) {
                jVar.n = Long.valueOf(a);
            }
        } else {
            this.a.b(jVar);
        }
    }

    public synchronized void b(List<j> list) {
        if (list.size() != 0) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            for (j jVar : list) {
                Long l = jVar.n;
                String str = jVar.i;
                if (l == null && str == null) {
                    arrayList.add(jVar);
                } else if (l == null && str != null) {
                    j b = this.a.b(str);
                    if (b == null) {
                        arrayList.add(jVar);
                    } else {
                        jVar.n = b.n;
                        arrayList2.add(jVar);
                    }
                } else if (this.a.b(l) == null) {
                    arrayList.add(jVar);
                } else {
                    arrayList2.add(jVar);
                }
            }
            List c = this.a.c(arrayList);
            for (int i = 0; i < arrayList.size(); i++) {
                long longValue = ((Long) c.get(i)).longValue();
                if (longValue != -1) {
                    ((j) arrayList.get(i)).n = Long.valueOf(longValue);
                }
            }
            this.a.d(arrayList2);
        }
    }
}
