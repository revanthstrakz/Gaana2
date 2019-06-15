package com.inmobi.a;

import android.content.Context;
import android.location.Criteria;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.inmobi.commons.core.configs.a;
import com.inmobi.commons.core.configs.b;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.utilities.b.h;
import com.inmobi.commons.core.utilities.f;
import com.inmobi.commons.core.utilities.uid.d;
import java.util.HashMap;

public class o implements c {
    private static final String b = "o";
    private static final Object c = new Object();
    private static volatile o d;
    public p a = new p();
    private h e;
    private g f;
    private boolean g = false;

    public static o a() {
        o oVar = d;
        if (oVar == null) {
            synchronized (c) {
                oVar = d;
                if (oVar == null) {
                    oVar = new o();
                    d = oVar;
                }
            }
        }
        return oVar;
    }

    private o() {
        b.a().a(this.a, (c) this);
        h.a().a(this.a.a.b());
        m.a();
        m.a(this.a.a.a());
        com.inmobi.commons.core.e.b.a().a("signals", this.a.c);
    }

    public final void a(a aVar) {
        this.a = (p) aVar;
        m.a();
        m.a(this.a.a.a());
        h.a().a(this.a.a.b());
        com.inmobi.commons.core.e.b.a().a("signals", this.a.c);
    }

    public final synchronized void b() {
        if (!this.g) {
            this.g = true;
            f();
            m a = m.a();
            try {
                if (m.a && m.c() && a.g()) {
                    if (m.a && m.c() && a.g() && a.b != null) {
                        Criteria criteria = new Criteria();
                        criteria.setBearingAccuracy(2);
                        criteria.setPowerRequirement(2);
                        criteria.setCostAllowed(false);
                        String bestProvider = a.b.getBestProvider(criteria, true);
                        if (bestProvider != null) {
                            a.b.requestSingleUpdate(bestProvider, a, a.c.getLooper());
                        }
                    }
                    if (!m.b() || !f.a("signals")) {
                        Context b = com.inmobi.commons.a.a.b();
                        try {
                            if (a.d == null) {
                                a.d = new Builder(b).addConnectionCallbacks(new ConnectionCallbacks() {
                                    public final void onConnected(@Nullable Bundle bundle) {
                                        m.e;
                                        m.h = true;
                                    }

                                    public final void onConnectionSuspended(int i) {
                                        m.h = false;
                                        m.e;
                                    }
                                }).addOnConnectionFailedListener(new OnConnectionFailedListener() {
                                    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                                        m.h = false;
                                    }
                                }).addApi(LocationServices.API).build();
                                a.d.connect();
                                return;
                            }
                            a.d.connect();
                        } catch (Exception e) {
                            StringBuilder stringBuilder = new StringBuilder("Error in connecting to GooglePlayServices API : (");
                            stringBuilder.append(e.getMessage());
                            stringBuilder.append(")");
                        }
                    }
                }
            } catch (Exception e2) {
                new StringBuilder("SDK encountered unexpected error in initializing location collection; ").append(e2.getMessage());
            }
        }
    }

    public final synchronized void c() {
        if (this.g) {
            this.g = false;
            n a = n.a();
            if (a().a.a.b()) {
                h.a().c = System.currentTimeMillis();
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("sessionId", h.a().a);
                    hashMap.put("totalNetworkTime", Long.valueOf(a.e));
                    hashMap.put("sessionDuration", Long.valueOf(SystemClock.elapsedRealtime() - a.f));
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("signals", "SDKSessionEnded", hashMap);
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                    stringBuilder.append(e.getMessage());
                    stringBuilder.append(")");
                }
            }
            if (this.e != null) {
                h hVar = this.e;
                hVar.a.b = true;
                hVar.a.sendEmptyMessageDelayed(2, (long) (a().a.a.c * 1000));
            }
            m a2 = m.a();
            if (m.a && m.c()) {
                if (a2.b != null) {
                    a2.b.removeUpdates(a2);
                }
                if (a2.d != null) {
                    a2.d.disconnect();
                }
            }
            a2.d = null;
        }
    }

    /* Access modifiers changed, original: final */
    public final d d() {
        return new d(this.a.p.a);
    }

    /* JADX WARNING: Missing block: B:22:0x008d, code skipped:
            return;
     */
    private synchronized void f() {
        /*
        r7 = this;
        monitor-enter(r7);
        r0 = r7.g;	 Catch:{ all -> 0x008e }
        if (r0 != 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r7);
        return;
    L_0x0007:
        r0 = r7.a;	 Catch:{ all -> 0x008e }
        r0 = r0.a;	 Catch:{ all -> 0x008e }
        r0 = r0.a;	 Catch:{ all -> 0x008e }
        if (r0 == 0) goto L_0x008c;
    L_0x000f:
        r0 = com.inmobi.a.n.a();	 Catch:{ all -> 0x008e }
        r1 = a();	 Catch:{ all -> 0x008e }
        r1 = r1.a;	 Catch:{ all -> 0x008e }
        r1 = r1.a;	 Catch:{ all -> 0x008e }
        r1 = r1.b();	 Catch:{ all -> 0x008e }
        if (r1 == 0) goto L_0x007c;
    L_0x0021:
        r1 = java.util.UUID.randomUUID();	 Catch:{ all -> 0x008e }
        r1 = r1.toString();	 Catch:{ all -> 0x008e }
        r2 = com.inmobi.commons.core.utilities.b.h.a();	 Catch:{ all -> 0x008e }
        r2.a = r1;	 Catch:{ all -> 0x008e }
        r2 = com.inmobi.commons.core.utilities.b.h.a();	 Catch:{ all -> 0x008e }
        r3 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x008e }
        r2.b = r3;	 Catch:{ all -> 0x008e }
        r2 = com.inmobi.commons.core.utilities.b.h.a();	 Catch:{ all -> 0x008e }
        r3 = 0;
        r2.c = r3;	 Catch:{ all -> 0x008e }
        r5 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x008e }
        r0.f = r5;	 Catch:{ all -> 0x008e }
        r0.a = r3;	 Catch:{ all -> 0x008e }
        r0.b = r3;	 Catch:{ all -> 0x008e }
        r0.c = r3;	 Catch:{ all -> 0x008e }
        r0.d = r3;	 Catch:{ all -> 0x008e }
        r0.e = r3;	 Catch:{ all -> 0x008e }
        r0.f = r3;	 Catch:{ all -> 0x008e }
        r0 = new java.util.HashMap;	 Catch:{ all -> 0x008e }
        r0.<init>();	 Catch:{ all -> 0x008e }
        r2 = "sessionId";
        r0.put(r2, r1);	 Catch:{ all -> 0x008e }
        com.inmobi.commons.core.e.b.a();	 Catch:{ Exception -> 0x0068 }
        r1 = "signals";
        r2 = "SDKSessionStarted";
        com.inmobi.commons.core.e.b.a(r1, r2, r0);	 Catch:{ Exception -> 0x0068 }
        goto L_0x007c;
    L_0x0068:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008e }
        r2 = "Error in submitting telemetry event : (";
        r1.<init>(r2);	 Catch:{ all -> 0x008e }
        r0 = r0.getMessage();	 Catch:{ all -> 0x008e }
        r1.append(r0);	 Catch:{ all -> 0x008e }
        r0 = ")";
        r1.append(r0);	 Catch:{ all -> 0x008e }
    L_0x007c:
        r0 = r7.e;	 Catch:{ all -> 0x008e }
        if (r0 != 0) goto L_0x0087;
    L_0x0080:
        r0 = new com.inmobi.a.h;	 Catch:{ all -> 0x008e }
        r0.<init>();	 Catch:{ all -> 0x008e }
        r7.e = r0;	 Catch:{ all -> 0x008e }
    L_0x0087:
        r0 = r7.e;	 Catch:{ all -> 0x008e }
        r0.a();	 Catch:{ all -> 0x008e }
    L_0x008c:
        monitor-exit(r7);
        return;
    L_0x008e:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.a.o.f():void");
    }

    public final void e() {
        if (this.g && this.a.b.a) {
            if (this.f == null) {
                this.f = new g();
            }
            this.f.a(this.a.b);
        }
    }
}
