package com.comscore.utils;

import android.content.Context;
import com.comscore.analytics.Core;
import com.comscore.applications.ApplicationMeasurement;
import com.comscore.applications.EventType;
import com.comscore.measurement.Measurement;
import com.til.colombia.android.internal.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.SSLException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class OfflineMeasurementsCache {
    protected final Core a;
    private int b;
    private int c;
    private int d;
    private long e;
    private long f;
    private String g;
    private final String h;
    private ArrayList<String> i;
    private String j;
    private int k;
    private long l;
    private long m;

    public OfflineMeasurementsCache(Core core) {
        this(core, Constants.CACHE_FILENAME);
    }

    protected OfflineMeasurementsCache(Core core, String str) {
        this.g = null;
        this.i = null;
        this.j = null;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.a = core;
        this.h = str;
        setCacheMaxMeasurements(2000);
        setCacheMaxBatchFiles(100);
        setCacheMaxPosts(10);
        setCacheWaitMinutes(30);
        setCacheMeasurementExpiry(31);
        e();
    }

    private void a(int i) {
        Storage storage = this.a.getStorage();
        if (storage.has(Constants.CACHE_DROPPED_MEASUREMENTS).booleanValue()) {
            i += Integer.valueOf(storage.get(Constants.CACHE_DROPPED_MEASUREMENTS)).intValue();
        }
        storage.set(Constants.CACHE_DROPPED_MEASUREMENTS, String.valueOf(i));
    }

    private void a(String str) {
        Storage storage = this.a.getStorage();
        if (storage.has(str).booleanValue()) {
            a(Integer.valueOf(storage.get(str)).intValue());
        }
    }

    private void a(String str, boolean z) {
        if (str != null) {
            if (z) {
                a(str);
            }
            FileUtils.deleteFile(this.a, str);
            this.i.remove(str);
        }
    }

    private boolean a(long j) {
        return ((((this.f * 24) * 60) * 60) * 1000) - (Date.unixTime() - j) <= 0;
    }

    private boolean a(String str, String str2) {
        HttpClient httpClient = Connectivity.getHttpClient();
        HttpPost httpPost = new HttpPost(str2);
        try {
            StringEntity stringEntity = new StringEntity(str, "UTF-8");
            stringEntity.setContentType("text/xml");
            httpPost.setHeader(e.c, System.getProperty("http.agent"));
            httpPost.setEntity(stringEntity);
            CSLog.d((Object) this, "Sending POST request");
            HttpResponse execute = httpClient.execute(httpPost);
            int statusCode = execute.getStatusLine().getStatusCode();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Response:");
            stringBuilder.append(statusCode);
            CSLog.d((Object) this, stringBuilder.toString());
            CSLog.d((Object) this, "Cache flushed");
            str = EntityUtils.toString(execute.getEntity());
            if (statusCode == 200 && Utils.isNotEmpty(str) && str.startsWith(Constants.RESPONSE_MASK)) {
                return true;
            }
        } catch (SSLException e) {
            CSLog.e((Object) this, e.getMessage());
            this.a.allowOfflineTransmission(TransmissionMode.DISABLED, true);
            clear();
        } catch (Exception e2) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Exception in flush:");
            stringBuilder2.append(e2.getLocalizedMessage());
            CSLog.e((Object) this, stringBuilder2.toString());
            CSLog.printStackTrace(e2);
        }
        return false;
    }

    private static String[] a(String[] strArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = strArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        i2 -= i;
        length = Math.min(i2, length - i);
        String[] strArr2 = new String[i2];
        System.arraycopy(strArr, i, strArr2, 0, length);
        return strArr2;
    }

    private void b(String str) {
        CSLog.d((Object) this, "Creating new cache batch file");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.h);
        stringBuilder.append(XMLBuilder.getLabelFromEvent(str, "ns_ts"));
        String stringBuilder2 = stringBuilder.toString();
        FileUtils.writeEvent(this.a, stringBuilder2, 0, str);
        if (this.i == null) {
            this.i = new ArrayList();
        }
        this.i.add(stringBuilder2);
        c();
    }

    private boolean b() {
        if (!d().booleanValue() || isEmpty() || this.a.getCustomerC2() == null) {
            return false;
        }
        if (this.k < this.d) {
            return true;
        }
        long unixTime = ((this.e * 1000) * 60) - (Date.unixTime() - this.m);
        if (unixTime <= 0) {
            this.k = 0;
            this.m = 0;
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Max flushes in a row (");
        stringBuilder.append(this.d);
        stringBuilder.append(") reached. Waiting ");
        stringBuilder.append((((double) unixTime) / 1000.0d) / 60.0d);
        stringBuilder.append(" minutes");
        CSLog.d((Object) this, stringBuilder.toString());
        return false;
    }

    private int c(String str) {
        Storage storage = this.a.getStorage();
        return str != null ? storage.has(str).booleanValue() ? Integer.valueOf(storage.get(str)).intValue() : FileUtils.readCachedEvents(this.a.getAppContext(), str).length : 0;
    }

    private void c() {
        this.l = 0;
        if (this.j != null) {
            this.j = null;
        }
    }

    private Boolean d() {
        Context appContext = this.a.getAppContext();
        return Connectivity.isEmulator() ? Boolean.valueOf(true) : !Permissions.check(appContext, "android.permission.ACCESS_NETWORK_STATE").booleanValue() ? Boolean.valueOf(true) : Connectivity.isConnectedWiFi(appContext) ? Boolean.valueOf(true) : Connectivity.isConnectedMobile(appContext) ? Boolean.valueOf(true) : Boolean.valueOf(false);
    }

    private String[] d(String str) {
        String[] readCachedEvents = FileUtils.readCachedEvents(this.a.getAppContext(), str);
        int i = 0;
        int i2 = 0;
        while (i < readCachedEvents.length) {
            try {
                long parseLong = Long.parseLong(XMLBuilder.getLabelFromEvent(readCachedEvents[i], "ns_ts"));
                i2 = a(parseLong) ^ 1;
                if (i2 != 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Valid timestamp found: ");
                    stringBuilder.append(parseLong);
                    CSLog.d((Object) this, stringBuilder.toString());
                    break;
                }
                i++;
            } catch (NumberFormatException unused) {
            }
        }
        if (i2 == 0) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("All events in the file ");
            stringBuilder2.append(str);
            stringBuilder2.append(" are expired");
            CSLog.d((Object) this, stringBuilder2.toString());
            a(str, true);
            return null;
        }
        a(i);
        return a(readCachedEvents, i, readCachedEvents.length);
    }

    private long e(String str) {
        return Long.valueOf(str.substring(this.h.length())).longValue();
    }

    private void e() {
        List f = f();
        for (int size = f.size() - 1; size >= 0; size--) {
            if (a(e((String) f.get(size)))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Deleting expired cache file ");
                stringBuilder.append((String) f.get(size));
                CSLog.d((Object) this, stringBuilder.toString());
                a((String) f.get(size), true);
            }
        }
    }

    private List<String> f() {
        if (this.i == null) {
            this.i = FileUtils.getFileList(this.a.getAppContext());
        }
        return this.i;
    }

    private String g() {
        return (this.i == null || this.i.size() <= 0) ? null : (String) this.i.get(0);
    }

    private String h() {
        return (this.i == null || this.i.size() <= 0) ? null : (String) this.i.get(this.i.size() - 1);
    }

    /* Access modifiers changed, original: protected */
    public String a() {
        StringBuilder stringBuilder;
        int i;
        if (this.g != null) {
            stringBuilder = new StringBuilder(this.g);
        } else {
            stringBuilder = new StringBuilder(this.a.isSecure() ? Constants.OFFLINE_RECEIVER_URL_SECURE : Constants.OFFLINE_RECEIVER_URL);
        }
        if (stringBuilder.indexOf("?") == -1) {
            stringBuilder.append("?");
            i = 0;
        } else {
            i = 1;
        }
        String customerC2 = this.a.getCustomerC2();
        if (!(customerC2 == null || customerC2.equals(""))) {
            if (i != 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append("c2=");
            stringBuilder.append(customerC2);
            i = 1;
        }
        customerC2 = Utils.md5(String.format("JetportGotAMaskOfThe%sS.D_K-", new Object[]{this.a.getPublisherSecret()}));
        if (!(customerC2 == null || customerC2.equals(""))) {
            if (i != 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append("s=");
            stringBuilder.append(customerC2);
        }
        return stringBuilder.toString().toLowerCase(new Locale("en", "US"));
    }

    public void clear() {
        if (this.a.isEnabled()) {
            List f = f();
            for (int size = f.size(); size > 0; size--) {
                a((String) f.get(size - 1), true);
            }
        }
    }

    /* JADX WARNING: Missing block: B:35:0x0106, code skipped:
            return r2;
     */
    public synchronized boolean flush() {
        /*
        r10 = this;
        monitor-enter(r10);
        r0 = r10.a;	 Catch:{ all -> 0x0107 }
        r0 = r0.isEnabled();	 Catch:{ all -> 0x0107 }
        r1 = 0;
        if (r0 != 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r10);
        return r1;
    L_0x000c:
        r0 = r10.a;	 Catch:{ all -> 0x0107 }
        r0 = r0.getStorage();	 Catch:{ all -> 0x0107 }
        r10.e();	 Catch:{ all -> 0x0107 }
        r2 = r10.e;	 Catch:{ all -> 0x0107 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 * r4;
        r4 = 60;
        r2 = r2 * r4;
        r4 = com.comscore.utils.Date.unixTime();	 Catch:{ all -> 0x0107 }
        r6 = r10.l;	 Catch:{ all -> 0x0107 }
        r8 = r4 - r6;
        r4 = r2 - r8;
        r2 = 0;
        r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r6 > 0) goto L_0x00e1;
    L_0x002d:
        r10.l = r2;	 Catch:{ all -> 0x0107 }
        r2 = r1;
    L_0x0030:
        r3 = r10.b();	 Catch:{ all -> 0x0107 }
        if (r3 == 0) goto L_0x0105;
    L_0x0036:
        r3 = 0;
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0107 }
        r4.<init>();	 Catch:{ all -> 0x0107 }
        r5 = "Cache is not empty, contains ";
        r4.append(r5);	 Catch:{ all -> 0x0107 }
        r5 = r10.i;	 Catch:{ all -> 0x0107 }
        r5 = r5.size();	 Catch:{ all -> 0x0107 }
        r4.append(r5);	 Catch:{ all -> 0x0107 }
        r5 = " files";
        r4.append(r5);	 Catch:{ all -> 0x0107 }
        r4 = r4.toString();	 Catch:{ all -> 0x0107 }
        com.comscore.utils.CSLog.d(r10, r4);	 Catch:{ all -> 0x0107 }
        r4 = r10.j;	 Catch:{ all -> 0x0107 }
        if (r4 != 0) goto L_0x0096;
    L_0x005a:
        r3 = r10.h();	 Catch:{ all -> 0x0107 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0107 }
        r4.<init>();	 Catch:{ all -> 0x0107 }
        r5 = "reading events from the file ";
        r4.append(r5);	 Catch:{ all -> 0x0107 }
        r4.append(r3);	 Catch:{ all -> 0x0107 }
        r4 = r4.toString();	 Catch:{ all -> 0x0107 }
        com.comscore.utils.CSLog.d(r10, r4);	 Catch:{ all -> 0x0107 }
        r4 = r10.d(r3);	 Catch:{ all -> 0x0107 }
        if (r4 == 0) goto L_0x0096;
    L_0x0078:
        r5 = r4.length;	 Catch:{ all -> 0x0107 }
        if (r5 <= 0) goto L_0x0096;
    L_0x007b:
        r5 = "CACHE_DROPPED_MEASUREMENTS";
        r5 = r0.has(r5);	 Catch:{ all -> 0x0107 }
        r5 = r5.booleanValue();	 Catch:{ all -> 0x0107 }
        if (r5 == 0) goto L_0x008e;
    L_0x0087:
        r5 = "CACHE_DROPPED_MEASUREMENTS";
        r5 = r0.get(r5);	 Catch:{ all -> 0x0107 }
        goto L_0x0090;
    L_0x008e:
        r5 = "0";
    L_0x0090:
        r4 = com.comscore.utils.XMLBuilder.generateXMLRequestString(r4, r5);	 Catch:{ all -> 0x0107 }
        r10.j = r4;	 Catch:{ all -> 0x0107 }
    L_0x0096:
        r4 = r10.j;	 Catch:{ all -> 0x0107 }
        if (r4 == 0) goto L_0x0030;
    L_0x009a:
        r4 = r10.j;	 Catch:{ all -> 0x0107 }
        r4 = r4.length();	 Catch:{ all -> 0x0107 }
        if (r4 <= 0) goto L_0x0030;
    L_0x00a2:
        r2 = r10.j;	 Catch:{ all -> 0x0107 }
        r4 = r10.a();	 Catch:{ all -> 0x0107 }
        r2 = r10.a(r2, r4);	 Catch:{ all -> 0x0107 }
        if (r2 == 0) goto L_0x00da;
    L_0x00ae:
        r4 = r10.k;	 Catch:{ all -> 0x0107 }
        r4 = r4 + 1;
        r10.k = r4;	 Catch:{ all -> 0x0107 }
        r10.a(r3, r1);	 Catch:{ all -> 0x0107 }
        r10.c();	 Catch:{ all -> 0x0107 }
        r3 = com.comscore.utils.Date.unixTime();	 Catch:{ all -> 0x0107 }
        r10.m = r3;	 Catch:{ all -> 0x0107 }
        r3 = "CACHE_DROPPED_MEASUREMENTS";
        r0.remove(r3);	 Catch:{ all -> 0x0107 }
        r3 = r10.a;	 Catch:{ all -> 0x0107 }
        r3 = r3.getStorage();	 Catch:{ all -> 0x0107 }
        r4 = "lastMeasurementProcessedTimestamp";
        r5 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0107 }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ all -> 0x0107 }
        r3.set(r4, r5);	 Catch:{ all -> 0x0107 }
        goto L_0x0030;
    L_0x00da:
        r0 = com.comscore.utils.Date.unixTime();	 Catch:{ all -> 0x0107 }
        r10.l = r0;	 Catch:{ all -> 0x0107 }
        goto L_0x0105;
    L_0x00e1:
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0107 }
        r0.<init>();	 Catch:{ all -> 0x0107 }
        r2 = "Waiting ";
        r0.append(r2);	 Catch:{ all -> 0x0107 }
        r2 = (double) r4;	 Catch:{ all -> 0x0107 }
        r4 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r2 = r2 / r4;
        r4 = 4633641066610819072; // 0x404e000000000000 float:0.0 double:60.0;
        r2 = r2 / r4;
        r0.append(r2);	 Catch:{ all -> 0x0107 }
        r2 = " minutes";
        r0.append(r2);	 Catch:{ all -> 0x0107 }
        r0 = r0.toString();	 Catch:{ all -> 0x0107 }
        com.comscore.utils.CSLog.d(r10, r0);	 Catch:{ all -> 0x0107 }
        r2 = r1;
    L_0x0105:
        monitor-exit(r10);
        return r2;
    L_0x0107:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.utils.OfflineMeasurementsCache.flush():boolean");
    }

    public int getCacheMaxBatchFiles() {
        return this.c;
    }

    public int getCacheMaxMeasurements() {
        return this.b;
    }

    public int getCacheMaxPosts() {
        return this.d;
    }

    public long getCacheMeasurementExpiry() {
        return this.f;
    }

    public long getCacheWaitMinutes() {
        return this.e;
    }

    public int getEventCount() {
        int c = c(h());
        List f = f();
        return f.size() > 0 ? c + ((f.size() - 1) * getCacheMaxBatchFiles()) : c;
    }

    public boolean isEmpty() {
        return getEventCount() == 0;
    }

    public void saveApplicationMeasurement(EventType eventType, HashMap<String, String> hashMap) {
        saveApplicationMeasurement(eventType, hashMap, false);
    }

    public void saveApplicationMeasurement(EventType eventType, HashMap<String, String> hashMap, boolean z) {
        if (this.a.isEnabled()) {
            ApplicationMeasurement newApplicationMeasurement = ApplicationMeasurement.newApplicationMeasurement(this.a, eventType, hashMap, null);
            this.a.getMeasurementDispatcher().addAggregateData(newApplicationMeasurement);
            this.a.getMeasurementDispatcher().addEventCounter(newApplicationMeasurement);
            saveEvent(newApplicationMeasurement, z);
        }
    }

    public void saveEvent(Measurement measurement) {
        saveEvent(measurement, false);
    }

    public void saveEvent(Measurement measurement, boolean z) {
        if (!this.a.isEnabled()) {
            return;
        }
        if (z) {
            this.a.getTaskExecutor().execute(new c(this, measurement), true);
        } else {
            saveEvent(measurement.retrieveLabelsAsString(this.a.getMeasurementLabelOrder()));
        }
    }

    /* JADX WARNING: Missing block: B:23:0x0097, code skipped:
            return;
     */
    public synchronized void saveEvent(java.lang.String r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.a;	 Catch:{ all -> 0x0098 }
        r0 = r0.isEnabled();	 Catch:{ all -> 0x0098 }
        if (r0 != 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r3);
        return;
    L_0x000b:
        r0 = r3.a;	 Catch:{ all -> 0x0098 }
        r0 = r0.getOfflineTransmissionMode();	 Catch:{ all -> 0x0098 }
        r1 = com.comscore.utils.TransmissionMode.DISABLED;	 Catch:{ all -> 0x0098 }
        if (r0 == r1) goto L_0x0096;
    L_0x0015:
        r0 = r3.a;	 Catch:{ all -> 0x0098 }
        r0 = r0.getCustomerC2();	 Catch:{ all -> 0x0098 }
        if (r0 == 0) goto L_0x0096;
    L_0x001d:
        r0 = "ns_ts";
        r0 = com.comscore.utils.XMLBuilder.getLabelFromEvent(r4, r0);	 Catch:{ all -> 0x0098 }
        r0 = com.comscore.utils.Utils.isNotEmpty(r0);	 Catch:{ all -> 0x0098 }
        if (r0 == 0) goto L_0x0096;
    L_0x0029:
        r0 = r3.h();	 Catch:{ all -> 0x0098 }
        if (r0 == 0) goto L_0x0093;
    L_0x002f:
        r1 = r3.c(r0);	 Catch:{ all -> 0x0098 }
        r2 = r3.getCacheMaxBatchFiles();	 Catch:{ all -> 0x0098 }
        if (r1 >= r2) goto L_0x0056;
    L_0x0039:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0098 }
        r1.<init>();	 Catch:{ all -> 0x0098 }
        r2 = "\n";
        r1.append(r2);	 Catch:{ all -> 0x0098 }
        r1.append(r4);	 Catch:{ all -> 0x0098 }
        r4 = r1.toString();	 Catch:{ all -> 0x0098 }
        r1 = r3.a;	 Catch:{ all -> 0x0098 }
        r2 = 32768; // 0x8000 float:4.5918E-41 double:1.61895E-319;
        com.comscore.utils.FileUtils.writeEvent(r1, r0, r2, r4);	 Catch:{ all -> 0x0098 }
        r3.c();	 Catch:{ all -> 0x0098 }
        goto L_0x0096;
    L_0x0056:
        r0 = "The newest cache batch file is full";
        com.comscore.utils.CSLog.d(r3, r0);	 Catch:{ all -> 0x0098 }
        r0 = r3.f();	 Catch:{ all -> 0x0098 }
        r0 = r0.size();	 Catch:{ all -> 0x0098 }
        r1 = r3.getCacheMaxMeasurements();	 Catch:{ all -> 0x0098 }
        r2 = r3.getCacheMaxBatchFiles();	 Catch:{ all -> 0x0098 }
        r1 = r1 / r2;
        if (r0 < r1) goto L_0x0093;
    L_0x006e:
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0098 }
        r0.<init>();	 Catch:{ all -> 0x0098 }
        r1 = "reached the cache max (";
        r0.append(r1);	 Catch:{ all -> 0x0098 }
        r1 = r3.getCacheMaxMeasurements();	 Catch:{ all -> 0x0098 }
        r0.append(r1);	 Catch:{ all -> 0x0098 }
        r1 = ") size";
        r0.append(r1);	 Catch:{ all -> 0x0098 }
        r0 = r0.toString();	 Catch:{ all -> 0x0098 }
        com.comscore.utils.CSLog.d(r3, r0);	 Catch:{ all -> 0x0098 }
        r0 = r3.g();	 Catch:{ all -> 0x0098 }
        r1 = 1;
        r3.a(r0, r1);	 Catch:{ all -> 0x0098 }
    L_0x0093:
        r3.b(r4);	 Catch:{ all -> 0x0098 }
    L_0x0096:
        monitor-exit(r3);
        return;
    L_0x0098:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.utils.OfflineMeasurementsCache.saveEvent(java.lang.String):void");
    }

    public void setCacheMaxBatchFiles(int i) {
        if (this.a.isEnabled() && i > 0) {
            this.c = i;
        }
    }

    public void setCacheMaxMeasurements(int i) {
        if (this.a.isEnabled()) {
            this.b = i;
        }
    }

    public void setCacheMaxPosts(int i) {
        this.d = i;
    }

    public void setCacheMeasurementExpiry(int i) {
        if (this.a.isEnabled()) {
            this.f = (long) i;
        }
    }

    public void setCacheWaitMinutes(int i) {
        if (this.a.isEnabled()) {
            this.e = (long) i;
        }
    }

    public void setUrl(String str) {
        if (this.a.isEnabled()) {
            this.g = str;
        }
    }
}
