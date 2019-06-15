package com.comscore.streaming.plugin;

class a implements Runnable {
    final /* synthetic */ StreamSensePlugin a;

    a(StreamSensePlugin streamSensePlugin) {
        this.a = streamSensePlugin;
    }

    /* JADX WARNING: Missing block: B:81:0x02ac, code skipped:
            if (r4 != false) goto L_0x02ae;
     */
    /* JADX WARNING: Missing block: B:82:0x02ae, code skipped:
            com.comscore.streaming.plugin.StreamSensePlugin.b(r10.a, true);
     */
    public void run() {
        /*
        r10 = this;
        r0 = r10.a;
        r0 = r0.t;
        if (r0 == 0) goto L_0x02fd;
    L_0x0008:
        r0 = r10.a;
        r0 = r0.u;
        if (r0 != 0) goto L_0x0012;
    L_0x0010:
        goto L_0x02fd;
    L_0x0012:
        r0 = r10.a;
        r0 = r0.w;
        r1 = 0;
        if (r0 == 0) goto L_0x0021;
    L_0x001b:
        r0 = r10.a;
        r0.w = r1;
        return;
    L_0x0021:
        r0 = r10.a;
        r0 = r0.u;
        r2 = r0.getPosition();
        r0 = r10.a;
        r0 = r0.getState();
        r4 = java.lang.System.currentTimeMillis();
        r6 = r10.a;
        r6 = r6.k;
        r6 = r6.isEmpty();
        r7 = 1;
        if (r6 != 0) goto L_0x0068;
    L_0x0042:
        r6 = r10.a;
        r8 = r6.a(r1);
        r6 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r6 == 0) goto L_0x004d;
    L_0x004c:
        goto L_0x0068;
    L_0x004d:
        r6 = r10.a;
        r6 = r6.l;
        r8 = r10.a;
        r8 = r8.l;
        r8 = r8.size();
        r8 = r8 - r7;
        r4 = java.lang.Long.valueOf(r4);
        r6.set(r8, r4);
        r4 = r1;
        goto L_0x0129;
    L_0x0068:
        r6 = r10.a;
        r6 = r6.k;
        r8 = java.lang.Math.abs(r2);
        r8 = java.lang.Long.valueOf(r8);
        r6.add(r8);
        r6 = r10.a;
        r6 = r6.l;
        r4 = java.lang.Long.valueOf(r4);
        r6.add(r4);
        r4 = r10.a;
        r4 = r4.k;
        r4 = r4.size();
        if (r4 <= r7) goto L_0x00e1;
    L_0x0092:
        r4 = r10.a;
        r4 = r4.a(r1);
        r6 = r10.a;
        r8 = r6.a(r7);
        r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r6 >= 0) goto L_0x00e1;
    L_0x00a2:
        r4 = r10.a;
        r4 = r4.a(r1);
        r6 = r10.a;
        r8 = r6.b(r1);
        r6 = r10.a;
        r6 = r6.k;
        r6.clear();
        r6 = r10.a;
        r6 = r6.l;
        r6.clear();
        r6 = r10.a;
        r6 = r6.k;
        r4 = java.lang.Long.valueOf(r4);
        r6.add(r4);
        r4 = r10.a;
        r4 = r4.l;
        r5 = java.lang.Long.valueOf(r8);
        r4.add(r5);
        r4 = r10.a;
        r4 = r4.p;
        goto L_0x00f5;
    L_0x00e1:
        r4 = r10.a;
        r4 = r4.k;
        r4 = r4.size();
        r5 = r10.a;
        r5 = r5.g;
        if (r4 >= r5) goto L_0x00f4;
    L_0x00f3:
        return;
    L_0x00f4:
        r4 = r1;
    L_0x00f5:
        r5 = r10.a;
        r5 = r5.k;
        r5 = r5.size();
        r6 = r10.a;
        r6 = r6.i;
        if (r5 <= r6) goto L_0x0119;
    L_0x0107:
        r5 = r10.a;
        r5 = r5.k;
        r5.remove(r1);
        r5 = r10.a;
        r5 = r5.l;
        r5.remove(r1);
    L_0x0119:
        r5 = r10.a;
        r5 = r5.p;
        if (r5 == 0) goto L_0x0129;
    L_0x0121:
        if (r4 != 0) goto L_0x0129;
    L_0x0123:
        r4 = r10.a;
        r4 = r4.f();
    L_0x0129:
        r5 = com.comscore.streaming.plugin.b.a;
        r6 = r10.a;
        r6 = r6.getState();
        r6 = r6.ordinal();
        r5 = r5[r6];
        r6 = 0;
        switch(r5) {
            case 1: goto L_0x0218;
            case 2: goto L_0x0218;
            case 3: goto L_0x0218;
            case 4: goto L_0x013d;
            default: goto L_0x013b;
        };
    L_0x013b:
        goto L_0x02b3;
    L_0x013d:
        r5 = r10.a;
        r5 = r5.p;
        if (r5 == 0) goto L_0x017a;
    L_0x0145:
        if (r4 == 0) goto L_0x017a;
    L_0x0147:
        r4 = r10.a;
        r4 = r4.o;
        r4 = r4.iterator();
    L_0x0151:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x016b;
    L_0x0157:
        r5 = r4.next();
        r5 = (com.comscore.streaming.plugin.StreamSensePluginListener) r5;
        r8 = r10.a;
        r8 = r8.getState();
        r9 = com.comscore.streaming.StreamSenseEventType.PAUSE;
        r5 = r5.onPreStateChange(r8, r9, r1);
        if (r5 != 0) goto L_0x0151;
    L_0x016b:
        r1 = r10.a;
        r4 = com.comscore.streaming.StreamSenseEventType.PAUSE;
        r5 = r10.a;
        r8 = r5.m;
        r1.notify(r4, r6, r8);
        goto L_0x02ae;
    L_0x017a:
        r4 = r10.a;
        r4 = r4.s;
        if (r4 == 0) goto L_0x01c6;
    L_0x0182:
        r4 = r10.a;
        r4 = r4.a(r2);
        if (r4 == 0) goto L_0x01c6;
    L_0x018a:
        r4 = r10.a;
        r4 = r4.o;
        r4 = r4.iterator();
    L_0x0194:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x01ae;
    L_0x019a:
        r5 = r4.next();
        r5 = (com.comscore.streaming.plugin.StreamSensePluginListener) r5;
        r7 = r10.a;
        r7 = r7.getState();
        r8 = com.comscore.streaming.StreamSenseEventType.END;
        r5 = r5.onPreStateChange(r7, r8, r1);
        if (r5 != 0) goto L_0x0194;
    L_0x01ae:
        r4 = r10.a;
        r5 = com.comscore.streaming.StreamSenseEventType.END;
        r7 = r10.a;
        r7 = r7.u;
        r7 = r7.getDuration();
        r4.notify(r5, r6, r7);
        r4 = r10.a;
        r4.n = r1;
        goto L_0x02b3;
    L_0x01c6:
        r4 = r10.a;
        r4 = r4.q;
        if (r4 == 0) goto L_0x02b3;
    L_0x01ce:
        r4 = r10.a;
        r4 = r4.m;
        r7 = r2 - r4;
        r4 = java.lang.Math.abs(r7);
        r7 = r10.a;
        r7 = r7.c;
        r7 = (long) r7;
        r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1));
        if (r9 > 0) goto L_0x02b3;
    L_0x01e5:
        r4 = r10.a;
        r4 = r4.o;
        r4 = r4.iterator();
    L_0x01ef:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x0209;
    L_0x01f5:
        r5 = r4.next();
        r5 = (com.comscore.streaming.plugin.StreamSensePluginListener) r5;
        r7 = r10.a;
        r7 = r7.getState();
        r8 = com.comscore.streaming.StreamSenseEventType.PAUSE;
        r5 = r5.onPreStateChange(r7, r8, r1);
        if (r5 != 0) goto L_0x01ef;
    L_0x0209:
        r1 = r10.a;
        r4 = com.comscore.streaming.StreamSenseEventType.PAUSE;
        r5 = r10.a;
        r7 = r5.m;
        r1.notify(r4, r6, r7);
        goto L_0x02b3;
    L_0x0218:
        r5 = r10.a;
        r5 = r5.r;
        if (r5 == 0) goto L_0x02a4;
    L_0x0220:
        r5 = r10.a;
        r8 = r5.m;
        r5 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r5 <= 0) goto L_0x02a4;
    L_0x022a:
        if (r4 != 0) goto L_0x02a4;
    L_0x022c:
        r5 = r10.a;
        r5 = r5.a(r2);
        if (r5 != 0) goto L_0x02a4;
    L_0x0234:
        r4 = r10.a;
        r4 = r4.o;
        r4 = r4.iterator();
    L_0x023e:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x025e;
    L_0x0244:
        r5 = r4.next();
        r5 = (com.comscore.streaming.plugin.StreamSensePluginListener) r5;
        r7 = r10.a;
        r7 = r7.getState();
        r8 = com.comscore.streaming.StreamSenseEventType.PLAY;
        r9 = r10.a;
        r9 = r9.n;
        r5 = r5.onPreStateChange(r7, r8, r9);
        if (r5 != 0) goto L_0x023e;
    L_0x025e:
        r4 = r10.a;
        r4 = r4.n;
        if (r4 == 0) goto L_0x0282;
    L_0x0266:
        r4 = r10.a;
        r4 = r4.v;
        if (r4 != 0) goto L_0x0282;
    L_0x026e:
        r4 = new java.util.HashMap;
        r4.<init>();
        r5 = "ns_st_ui";
        r6 = "seek";
        r4.put(r5, r6);
        r5 = r10.a;
        r6 = com.comscore.streaming.StreamSenseEventType.PLAY;
        r5.notify(r6, r4, r2);
        goto L_0x0299;
    L_0x0282:
        r4 = r10.a;
        r5 = com.comscore.streaming.StreamSenseEventType.PLAY;
        r7 = r10.a;
        r7 = r7.k;
        r7 = r7.get(r1);
        r7 = (java.lang.Long) r7;
        r7 = r7.longValue();
        r4.notify(r5, r6, r7);
    L_0x0299:
        r4 = r10.a;
        r4.n = r1;
        r4 = r10.a;
        r4.v = r1;
        goto L_0x02b3;
    L_0x02a4:
        r1 = r10.a;
        r1 = r1.p;
        if (r1 == 0) goto L_0x02b3;
    L_0x02ac:
        if (r4 == 0) goto L_0x02b3;
    L_0x02ae:
        r1 = r10.a;
        r1.n = r7;
    L_0x02b3:
        r1 = r10.a;
        r1 = r1.getState();
        if (r0 == r1) goto L_0x02f7;
    L_0x02bb:
        r0 = r10.a;
        r0 = r0.o;
        r0 = r0.iterator();
    L_0x02c5:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x02db;
    L_0x02cb:
        r1 = r0.next();
        r1 = (com.comscore.streaming.plugin.StreamSensePluginListener) r1;
        r4 = r10.a;
        r4 = r4.getState();
        r1.onPostStateChange(r4);
        goto L_0x02c5;
    L_0x02db:
        r0 = r10.a;
        r0 = r0.getState();
        r1 = com.comscore.streaming.StreamSenseState.PAUSED;
        if (r0 != r1) goto L_0x02f7;
    L_0x02e5:
        r0 = r10.a;
        r0 = r0.k;
        r0.clear();
        r0 = r10.a;
        r0 = r0.l;
        r0.clear();
    L_0x02f7:
        r0 = r10.a;
        r0.m = r2;
        return;
    L_0x02fd:
        r0 = r10.a;
        r0.c();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.streaming.plugin.a.run():void");
    }
}
