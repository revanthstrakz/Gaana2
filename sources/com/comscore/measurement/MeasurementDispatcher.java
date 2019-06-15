package com.comscore.measurement;

import com.comscore.analytics.Core;
import com.comscore.applications.AggregateMeasurement;
import com.comscore.metrics.Request;
import com.comscore.utils.CSLog;
import com.comscore.utils.Constants;
import com.comscore.utils.Date;
import com.comscore.utils.Storage;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MeasurementDispatcher {
    public static final String DAY_CHECK_COUNTER = "q_dcc";
    public static final String DAY_CHECK_OFFSET = "q_dcf";
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_SECOND = 1000;
    Core a;
    protected AtomicLong b = new AtomicLong(-1);
    protected AtomicInteger c = new AtomicInteger(0);
    protected AtomicLong d = new AtomicLong(-1);
    protected AtomicInteger e = new AtomicInteger(0);
    protected AtomicInteger f = new AtomicInteger(0);
    protected Object g = new Object();
    private AggregateMeasurement h = null;

    public MeasurementDispatcher(Core core) {
        this.a = core;
    }

    private void a(AggregateMeasurement aggregateMeasurement) {
        synchronized (this.g) {
            if (this.h == null) {
                this.h = aggregateMeasurement;
                this.h.formatLists();
            } else {
                this.h.aggregateLabels(aggregateMeasurement.getAggregateLabels());
            }
        }
    }

    /* JADX WARNING: Missing block: B:8:0x006f, code skipped:
            if ((r1 - r10.d.get()) > MILLIS_PER_DAY) goto L_0x0021;
     */
    private boolean a() {
        /*
        r10 = this;
        r0 = r10.a;
        r0 = r0.getStorage();
        r1 = com.comscore.utils.Date.unixTime();
        r3 = r10.b;
        r3 = r3.get();
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        r3 = 10;
        r4 = 0;
        if (r5 >= 0) goto L_0x004a;
    L_0x0017:
        r5 = r10.c;
        r5.set(r4);
        r5 = r10.b;
        r5.set(r1);
    L_0x0021:
        r5 = r10.e;
        r5.set(r4);
        r5 = r10.d;
        r5.set(r1);
        r1 = "q_dcc";
        r2 = r10.e;
        r2 = r2.get();
        r2 = java.lang.Integer.toString(r2, r3);
        r0.set(r1, r2);
        r1 = "q_dcf";
        r2 = r10.d;
        r5 = r2.get();
        r2 = java.lang.Long.toString(r5, r3);
        r0.set(r1, r2);
        goto L_0x0072;
    L_0x004a:
        r5 = r10.b;
        r5 = r5.get();
        r7 = r1 - r5;
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1));
        if (r9 <= 0) goto L_0x0062;
    L_0x0058:
        r5 = r10.c;
        r5.set(r4);
        r5 = r10.b;
        r5.set(r1);
    L_0x0062:
        r5 = r10.d;
        r5 = r5.get();
        r7 = r1 - r5;
        r5 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1));
        if (r9 <= 0) goto L_0x0072;
    L_0x0071:
        goto L_0x0021;
    L_0x0072:
        r1 = r10.c;
        r1 = r1.get();
        r2 = 20;
        if (r1 >= r2) goto L_0x00a1;
    L_0x007c:
        r1 = r10.e;
        r1 = r1.get();
        r2 = 6000; // 0x1770 float:8.408E-42 double:2.9644E-320;
        if (r1 >= r2) goto L_0x00a1;
    L_0x0086:
        r1 = r10.c;
        r1.incrementAndGet();
        r1 = r10.e;
        r1.getAndIncrement();
        r1 = "q_dcc";
        r2 = r10.e;
        r2 = r2.get();
        r2 = java.lang.Integer.toString(r2, r3);
        r0.set(r1, r2);
        r0 = 1;
        return r0;
    L_0x00a1:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.measurement.MeasurementDispatcher.a():boolean");
    }

    private void b(Measurement measurement) {
        if (this.a.isEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sendMeasurmement: ");
            stringBuilder.append(measurement.retrieveLabelsAsString(this.a.getMeasurementLabelOrder()));
            CSLog.d((Object) this, stringBuilder.toString());
            addAggregateData(measurement);
            if (!(measurement instanceof AggregateMeasurement)) {
                addEventCounter(measurement);
                a(measurement);
                new Request(this.a, measurement).send();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(Measurement measurement) {
        if (this.a.isEnabled()) {
            measurement.setLabel(new Label("c12", this.a.getVisitorId(), Boolean.valueOf(false)));
            if (this.a.getCrossPublisherId() != null) {
                measurement.setLabel(new Label("ns_ak", this.a.getCrossPublisherId(), Boolean.valueOf(false)));
            }
        }
    }

    /* JADX WARNING: Missing block: B:19:0x0039, code skipped:
            return;
     */
    public void addAggregateData(com.comscore.measurement.Measurement r4) {
        /*
        r3 = this;
        r0 = r3.a;
        r0 = r0.isEnabled();
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r0 = r3.g;
        monitor-enter(r0);
        r1 = r4 instanceof com.comscore.applications.AggregateMeasurement;	 Catch:{ all -> 0x003a }
        if (r1 == 0) goto L_0x0017;
    L_0x0010:
        r4 = (com.comscore.applications.AggregateMeasurement) r4;	 Catch:{ all -> 0x003a }
        r3.a(r4);	 Catch:{ all -> 0x003a }
        monitor-exit(r0);	 Catch:{ all -> 0x003a }
        return;
    L_0x0017:
        r1 = r3.h;	 Catch:{ all -> 0x003a }
        if (r1 == 0) goto L_0x0038;
    L_0x001b:
        r1 = r3.h;	 Catch:{ all -> 0x003a }
        r1 = r1.getAggregateLabels();	 Catch:{ all -> 0x003a }
        r1 = r1.iterator();	 Catch:{ all -> 0x003a }
    L_0x0025:
        r2 = r1.hasNext();	 Catch:{ all -> 0x003a }
        if (r2 == 0) goto L_0x0035;
    L_0x002b:
        r2 = r1.next();	 Catch:{ all -> 0x003a }
        r2 = (com.comscore.measurement.Label) r2;	 Catch:{ all -> 0x003a }
        r4.setLabel(r2);	 Catch:{ all -> 0x003a }
        goto L_0x0025;
    L_0x0035:
        r4 = 0;
        r3.h = r4;	 Catch:{ all -> 0x003a }
    L_0x0038:
        monitor-exit(r0);	 Catch:{ all -> 0x003a }
        return;
    L_0x003a:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x003a }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.measurement.MeasurementDispatcher.addAggregateData(com.comscore.measurement.Measurement):void");
    }

    public void addEventCounter(Measurement measurement) {
        if (this.a.isEnabled()) {
            this.f.getAndIncrement();
            measurement.setLabel(new Label("ns_ap_ec", String.valueOf(this.f), Boolean.valueOf(false)));
        }
    }

    public void loadEventData() {
        if (this.a.isEnabled()) {
            Storage storage = this.a.getStorage();
            if (storage.has(DAY_CHECK_COUNTER).booleanValue() && storage.has(DAY_CHECK_OFFSET).booleanValue()) {
                try {
                    int parseInt = Integer.parseInt(storage.get(DAY_CHECK_COUNTER), 10);
                    long parseLong = Long.parseLong(storage.get(DAY_CHECK_OFFSET), 10);
                    if (Date.unixTime() >= parseLong) {
                        this.e.set(parseInt);
                        this.d.set(parseLong);
                    }
                } catch (NumberFormatException e) {
                    if (Constants.DEBUG) {
                        CSLog.e((Object) this, "Unexpected error parsing storage data: ");
                        CSLog.printStackTrace(e);
                        throw e;
                    }
                }
            }
        }
    }

    public boolean sendMeasurmement(Measurement measurement, boolean z) {
        if (!this.a.isEnabled() || measurement == null) {
            return false;
        }
        if (a() || this.a.getStorage() == null) {
            return this.a.getTaskExecutor().execute(new a(this, measurement), z);
        }
        CSLog.d((Object) this, "Data not sent");
        return false;
    }
}
